
package com.yao.hotfix;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/3/18
 */
public class HotFixManager {
    private HotFixManager() {
    }

    private static HotFixManager instance = new HotFixManager();

    public static HotFixManager getInstance() {
        return instance;
    }

    private static final String SD_CARD_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String PATCH_PATH = "sdk_patch";

    private Context appContext;
    private DexClassLoader patchClassLoader;
    private DexFile[] dexFiles;

    public void init(Context context) {
        this.appContext = context.getApplicationContext();

        try {

            File patchDir =
                    FileUtil.getDir(SD_CARD_PATH + File.separator + PATCH_PATH);
            String dexPath =
                    patchDir.getAbsolutePath() + File.separator + "patch.jar";
            File optDir =
                    FileUtil.getDir(appContext.getCacheDir().getAbsolutePath() +
                            File.separator + "patch/opt"
                    );
            File soDir =
                    FileUtil.getDir(appContext.getCacheDir().getAbsolutePath() +
                            File.separator + "patch/so"
                    );

            patchClassLoader = new DexClassLoader(
                    dexPath,
                    optDir.getAbsolutePath(),
                    soDir.getAbsolutePath(),
                    appContext.getClassLoader());

            //reflect the dexFile for traverse all of the class in the patch
            Object dexPathList = ReflectUtils.getFieldObj(
                    "dalvik.system.BaseDexClassLoader", patchClassLoader, "pathList");
            Object dexElements = ReflectUtils.getFieldObj(
                    "dalvik.system.DexPathList", dexPathList, "dexElements");

            int length = Array.getLength(dexElements);
            dexFiles = new DexFile[length];
            for (int i = 0; i < length; i++) {
                Object element = Array.get(dexElements, i);

                dexFiles[i] = (DexFile) ReflectUtils.getFieldObj(
                        "dalvik.system.DexPathList$Element", element, "dexFile"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fix() {
        try {
            //traverse the dexFile and hook all of the methods
            for (DexFile dexFile : dexFiles) {

                Enumeration<String> entries = dexFile.entries();
                Class<?> clazz;
                while (entries.hasMoreElements()) {
                    String entry = entries.nextElement();

                    clazz = dexFile.loadClass(entry, patchClassLoader);
                    if (clazz != null) {
                        Method[] methods = clazz.getDeclaredMethods();
                        for (Method replace : methods) {
                            MethodReplace mr =
                                    replace.getAnnotation(MethodReplace.class);
                            if (mr == null) {
                                continue;
                            }

                            Method origin =
                                    Class.forName(mr.className()).getDeclaredMethod(mr.methodName(),
                                            replace.getParameterTypes());

                            HotFix.hook(origin, replace);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callOrigin(Object receiver, Object[] params) {
        HotFix.callOrigin(receiver, params);
    }
}
