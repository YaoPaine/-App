package com.yao.hotfix;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/3/18
 */
public class HotFix {

    private static class Memory {

        static byte peekByte(long address) {
            //@FastNative
            //    public static native byte peekByte(long address);
            Object peekByte = ReflectUtils.invokeStaticMethod("libcore.io.Memory", "peekByte", new Class[]{long.class}, new Object[]{address});

            return (byte) (peekByte == null ? -1 : peekByte);
        }

        static void pokeByte(long address, byte value) {
            ReflectUtils.invokeStaticMethod(
                    "libcore.io.Memory", "pokeByte",
                    new Class[]{long.class, byte.class}, new Object[]{address, value}
            );
        }

        static void memcpy(long dst, long src, long length) {
            for (long i = 0; i < length; i++) {
                pokeByte(dst, peekByte(src));
                dst++;
                src++;
            }
        }
    }

    private static class Unsafe {
        static final String UNSAFE_CLASS_NAME = "sun.misc.Unsafe";
        static Object UNSAFE_CLASS_INSTANCE =
                ReflectUtils.getStaticFieldObj(UNSAFE_CLASS_NAME, "THE_ONE");

        static long getObjectAddress(Object obj) {
            Object[] args = {obj};

            Integer baseOffset = (Integer) ReflectUtils.invokeMethod(
                    UNSAFE_CLASS_NAME, UNSAFE_CLASS_INSTANCE, "arrayBaseOffset",
                    new Class[]{Class.class}, new Object[]{Object[].class}
            );

            long result = ((Number) ReflectUtils.invokeMethod(
                    UNSAFE_CLASS_NAME, UNSAFE_CLASS_INSTANCE, "getInt",
                    new Class[]{Object.class, long.class}, new Object[]{args, baseOffset.longValue()}
            )).longValue();

            return result;
        }
    }

    private static class MethodDecoder {
        static long sMethodSize = -1;

        public static void ruler1() {
        }

        public static void ruler2() {
        }

        static long getMethodAddress(Method method) {

            Object mirrorMethod =
                    ReflectUtils.getFieldObj(
                            Method.class.getSuperclass().getName(), method, "artMethod"
                    );
            if (mirrorMethod.getClass().equals(Long.class)) {
                return (Long) mirrorMethod;
            }

            return Unsafe.getObjectAddress(mirrorMethod);
        }

        static long getArtMethodSize() {
            if (sMethodSize > 0) {
                return sMethodSize;
            }

            try {
                Method m1 = MethodDecoder.class.getDeclaredMethod("ruler1");
                Method m2 = MethodDecoder.class.getDeclaredMethod("ruler2");

                sMethodSize = getMethodAddress(m2) - getMethodAddress(m1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return sMethodSize;
        }
    }

    private static Map<Pair<String, String>, Method> sBackups = new ConcurrentHashMap<>();

    public static void hook(Method origin, Method replace) {
        // 1. backup
        Method backUpMethod = backUp(origin, replace);
        sBackups.put(
                Pair.create(replace.getDeclaringClass().getName(), replace.getName()),
                backUpMethod
        );

        // 2. replace method
        long addressOrigin = MethodDecoder.getMethodAddress(origin);
        long addressReplace = MethodDecoder.getMethodAddress(replace);
        Memory.memcpy(
                addressOrigin,
                addressReplace,
                MethodDecoder.getArtMethodSize());
    }

    @Nullable
    public static Object callOrigin(Object receiver, Object... params) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement currentStack = stackTrace[4];
        Method method = sBackups.get(
                Pair.create(currentStack.getClassName(), currentStack.getMethodName()));

        try {
            return method.invoke(receiver, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Method backUp(Method origin, Method replace) {
        try {
            if (Build.VERSION.SDK_INT < 23) {
                Class<?> artMethodClass = Class.forName("java.lang.reflect.ArtMethod");
                Field accessFlagsField = artMethodClass.getDeclaredField("accessFlags");
                accessFlagsField.setAccessible(true);

                Constructor<?> artMethodConstructor = artMethodClass.getDeclaredConstructor();
                artMethodConstructor.setAccessible(true);
                Object newArtMethod = artMethodConstructor.newInstance();

                Constructor<Method> methodConstructor =
                        Method.class.getDeclaredConstructor(artMethodClass);
                Method newMethod = methodConstructor.newInstance(newArtMethod);
                newMethod.setAccessible(true);

                Memory.memcpy(MethodDecoder.getMethodAddress(newMethod),
                        MethodDecoder.getMethodAddress(origin),
                        MethodDecoder.getArtMethodSize());

                Integer accessFlags = (Integer) accessFlagsField.get(newArtMethod);
                accessFlags &= ~Modifier.PUBLIC;
                accessFlags |= Modifier.PRIVATE;
                accessFlagsField.set(newArtMethod, accessFlags);

                return newMethod;
            } else {
                // AbstractMethod
                Class<?> abstractMethodClass = Method.class.getSuperclass();
                Field accessFlagsField = abstractMethodClass.getDeclaredField("accessFlags");
                Field artMethodField = abstractMethodClass.getDeclaredField("artMethod");
                accessFlagsField.setAccessible(true);
                artMethodField.setAccessible(true);

                // make the construct accessible, we can not just use `setAccessible`
                Constructor<Method> methodConstructor = Method.class.getDeclaredConstructor();
                Field override = AccessibleObject.class.getDeclaredField(
                        Build.VERSION.SDK_INT == Build.VERSION_CODES.M ? "flag" : "override");
                override.setAccessible(true);
                override.set(methodConstructor, true);

                // clone the origin method
                Method newMethod = methodConstructor.newInstance();
                newMethod.setAccessible(true);
                for (Field field : abstractMethodClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(newMethod, field.get(origin));
                }

                // allocate new artMethod struct, we can not use memory managed by JVM
                int artMethodSize = (int) MethodDecoder.getArtMethodSize();
                ByteBuffer artMethod = ByteBuffer.allocateDirect(artMethodSize);
                Long artMethodAddress;
                int ACC_FLAG_OFFSET;
                if (Build.VERSION.SDK_INT < 24) {
                    // Below Android N, the jdk implementation is not openjdk
                    artMethodAddress =
                            (Long) ReflectUtils.getFieldObj(
                                    Buffer.class.getName(), artMethod, "effectiveDirectAddress"
                            );

                    // http://androidxref.com/6.0.0_r1/xref/art/runtime/art_method.h
                    // GCRoot * 3, sizeof(GCRoot) = sizeof(mirror::CompressedReference) = sizeof(mirror::ObjectReference) = sizeof(uint32_t) = 4
                    ACC_FLAG_OFFSET = 12;
                } else {
                    artMethodAddress =
                            (Long) ReflectUtils.invokeMethod(
                                    artMethod.getClass().getName(), artMethod, "address", null, null
                            );

//                            (Long) Reflection.call(artMethod.getClass(), null, "address", artMethod, null, null);

                    // http://androidxref.com/7.0.0_r1/xref/art/runtime/art_method.h
                    // sizeof(GCRoot) = 4
                    ACC_FLAG_OFFSET = 4;
                }
                Memory.memcpy(
                        artMethodAddress, MethodDecoder.getMethodAddress(origin), artMethodSize);

                byte[] newMethodBytes = new byte[artMethodSize];
                artMethod.get(newMethodBytes);
                // replace the artMethod of our new method
                artMethodField.set(newMethod, artMethodAddress);

                // modify the access flag of the new method
                Integer accessFlags = (Integer) accessFlagsField.get(origin);
                int privateAccFlag = accessFlags & ~Modifier.PUBLIC | Modifier.PRIVATE;
                accessFlagsField.set(newMethod, privateAccFlag);

                // 1. try big endian
                artMethod.order(ByteOrder.BIG_ENDIAN);
                int nativeAccFlags = artMethod.getInt(ACC_FLAG_OFFSET);
                if (nativeAccFlags == accessFlags) {
                    // hit!
                    artMethod.putInt(ACC_FLAG_OFFSET, privateAccFlag);
                } else {
                    // 2. try little endian
                    artMethod.order(ByteOrder.LITTLE_ENDIAN);
                    nativeAccFlags = artMethod.getInt(ACC_FLAG_OFFSET);
                    if (nativeAccFlags == accessFlags) {
                        artMethod.putInt(ACC_FLAG_OFFSET, privateAccFlag);
                    } else {
                        // the offset is error!
                        throw new RuntimeException("native set access flags error!");
                    }
                }

                return newMethod;

            }
        } catch (Exception e) {
            return null;
        }
    }
}
