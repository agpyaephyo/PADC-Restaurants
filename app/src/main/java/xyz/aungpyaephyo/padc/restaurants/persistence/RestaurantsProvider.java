package xyz.aungpyaephyo.padc.restaurants.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import xyz.aungpyaephyo.padc.restaurants.RestaurantsApp;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract.RestaurantEntry;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract.RestaurantTagEntry;

/**
 * Created by aung on 7/14/17.
 */

public class RestaurantsProvider extends ContentProvider {

    public static final int RESTAURANTS = 100;
    public static final int RESTAURANT_TAGS = 200;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private RestaurantsDBHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new RestaurantsDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        Cursor queryCursor = mDBHelper.getReadableDatabase().query(tableName,
                projection,
                selection,
                selectionArgs,
                null, //group_by
                null, //having
                sortOrder);

        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANTS:
                return RestaurantEntry.DIR_TYPE;
            case RESTAURANT_TAGS:
                return RestaurantTagEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        Uri contentUri = getContentUri(uri);
        Uri insertedUri = ContentUris.withAppendedId(contentUri, _id);

        if (getContext() != null) {
            Log.d(RestaurantsApp.TAG, "Cursor Issue - [Insert] Uri to notify : " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        Context context = getContext();
        if (context != null && insertedCount > 0) {
            Log.d(RestaurantsApp.TAG, "Cursor Issue - [Bulk Insert] Uri to notify : " + uri);
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(RestaurantsContract.CONTENT_AUTHORITY, RestaurantsContract.PATH_RESTAURANTS, RESTAURANTS);
        uriMatcher.addURI(RestaurantsContract.CONTENT_AUTHORITY, RestaurantsContract.PATH_RESTAURANT_TAGS, RESTAURANT_TAGS);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANTS:
                return RestaurantEntry.TABLE_NAME;
            case RESTAURANT_TAGS:
                return RestaurantTagEntry.TABLE_NAME;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    private Uri getContentUri(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANTS:
                return RestaurantEntry.CONTENT_URI;
            case RESTAURANT_TAGS:
                return RestaurantTagEntry.CONTENT_URI;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
