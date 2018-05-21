// ISecurity.aidl
package com.yaopaine.androidart;

// Declare any non-default types here with import statements

interface ISecurity {
    String encry(in String text);

    String decode(in String text);

}
