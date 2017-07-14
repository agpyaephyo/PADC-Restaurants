package xyz.aungpyaephyo.padc.restaurants.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract.RestaurantEntry;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract.RestaurantTagEntry;

/**
 * Created by aung on 7/14/17.
 */

public class RestaurantsDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "restaurants.db";

    private static final String SQL_CREATE_RESTAURANTS_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
            RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_SHORT_ADDR + " TEXT NULL, " +
            RestaurantEntry.COLUMN_TOTAL_RATING_COUNT + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE + " REAL NOT NULL, " +
            RestaurantEntry.COLUMN_IS_AD + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_IS_NEW + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_MOST_POPULAR + " TEXT NOT NULL, " +

            " UNIQUE (" + RestaurantEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_RESTAURANT_TAG_TABLE = "CREATE TABLE " + RestaurantTagEntry.TABLE_NAME + " (" +
            RestaurantTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " TEXT NOT NULL, " +
            RestaurantTagEntry.COLUMN_TAG + " TEXT NOT NULL, " +

            " UNIQUE (" + RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + ", " +
            RestaurantTagEntry.COLUMN_TAG + ") ON CONFLICT IGNORE" +
            " );";

    public RestaurantsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESTAURANTS_TABLE);
        db.execSQL(SQL_CREATE_RESTAURANT_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantTagEntry.TABLE_NAME);

        onCreate(db);
    }
}
