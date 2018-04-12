//
// Created by yaopaine on 4/12/18.
//

//#include <jni.h>
#include "com_yaopaine_andfix_jni_HelloJniUtils.h"

JNIEXPORT jint JNICALL Java_com_yaopaine_andfix_jni_HelloJniUtils_add
        (JNIEnv *env, jclass jclazz, jint x, jint y) {
    return x + y;
}

JNIEXPORT jdouble JNICALL Java_com_yaopaine_andfix_jni_HelloJniUtils_div
        (JNIEnv *env, jclass jclazz, jdouble x, jdouble y) {
    if (x == 0.0) {
        return 0;
    }
    return x / y;
}