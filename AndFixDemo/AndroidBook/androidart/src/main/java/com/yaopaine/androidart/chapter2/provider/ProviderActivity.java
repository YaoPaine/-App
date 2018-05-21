package com.yaopaine.androidart.chapter2.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "Android 开发艺术探索");
        getContentResolver().insert(BookProvider.BOOK_CONTENT_URI, values);

        Cursor cursor = getContentResolver().query(BookProvider.BOOK_CONTENT_URI, new String[]{"_id", "name"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.e("TAG", "_id: " + (cursor.getInt(0)) + "\t" + (cursor.getString(1)));
            }
            cursor.close();
        }

        Cursor userCursor = getContentResolver().query(BookProvider.USER_CONTENT_URI, new String[]{"_id", "name", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                Log.e("TAG", "_id: " + (userCursor.getInt(0)) + "\t" + (userCursor.getString(1)) + "\t" + (userCursor.getInt(2) == 1));
            }
            userCursor.close();
        }

    }
}
