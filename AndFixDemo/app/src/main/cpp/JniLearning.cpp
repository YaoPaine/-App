//
// Created by yaopaine on 4/12/18.
//

#include "com_yaopaine_andfix_jni_HelloJniUtils.h"

extern "C" {
JNIEXPORT jboolean JNICALL Java_com_yaopaine_andfix_jni_HelloJniUtils_isNative
        (JNIEnv *env, jclass jclazz) {
    return static_cast<jboolean>(true);
}

JNIEXPORT jstring JNICALL Java_com_yaopaine_andfix_jni_HelloJniUtils_getMessage
        (JNIEnv *env, jclass jclazz) {
    return env->NewStringUTF("Hello World");
}
}