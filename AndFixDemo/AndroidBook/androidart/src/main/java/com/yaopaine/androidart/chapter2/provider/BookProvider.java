package com.yaopaine.androidart.chapter2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
public class BookProvider extends ContentProvider {

    private String TAG = "BookProvider";

    public static final String AUTHORITY = "com.yaopaine.androidart.chapter2.book.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMacher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMacher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMacher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMacher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }

        return tableName;
    }

    @Override
    public boolean onCreate() {
        Log.e(TAG, "onCreate: " + (Thread.currentThread().getName()));
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.delete(DbOpenHelper.BOOK_TABLE_NAME, null, null);
        mDb.delete(DbOpenHelper.USER_TABLE_NAME, null, null);
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("name", "何瑶");
        contentValues1.put("sex", "1");
        mDb.insert(DbOpenHelper.USER_TABLE_NAME, null, contentValues1);

        mDb.execSQL("insert into book values(3,'Android')");
        mDb.execSQL("insert into book values(4,'IOS')");
        mDb.execSQL("insert into book values(5,'Java')");

        mDb.execSQL("insert into user values(2,'邓晓会',0)");
        mDb.execSQL("insert into user values(6,'马化腾',1)");

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.e(TAG, "query: " + (Thread.currentThread()));

        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("uri 有毛病");
        }
        return mDb.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.e(TAG, "getType: " + (Thread.currentThread().getName()));
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.e(TAG, "insert: " + (Thread.currentThread().getName()));
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("uri 有毛病");
        }

        mDb.insert(tableName, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e(TAG, "delete: " + (Thread.currentThread().getName()));
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("uri 有毛病");
        }
        int count = mDb.delete(tableName, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e(TAG, "update: " + (Thread.currentThread().getName()));
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("uri 有毛病");
        }
        int row = mDb.update(tableName, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
