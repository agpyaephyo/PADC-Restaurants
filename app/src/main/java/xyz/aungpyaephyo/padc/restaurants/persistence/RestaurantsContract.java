package xyz.aungpyaephyo.padc.restaurants.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import xyz.aungpyaephyo.padc.restaurants.RestaurantsApp;

/**
 * Created by aung on 7/14/17.
 */

public class RestaurantsContract {

    public static final String CONTENT_AUTHORITY = RestaurantsApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RESTAURANTS = "restaurants";
    public static final String PATH_RESTAURANT_TAGS = "restaurant_tags";

    public static final class RestaurantEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String TABLE_NAME = PATH_RESTAURANTS;

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_SHORT_ADDR = "short_addr";
        public static final String COLUMN_TOTAL_RATING_COUNT = "total_rating_count";
        public static final String COLUMN_AVERAGE_RATING_VALUE = "average_rating_value";
        public static final String COLUMN_IS_AD = "is_ad";
        public static final String COLUMN_IS_NEW = "is_new";
        public static final String COLUMN_LEAD_TIME_IN_MIN = "lead_time_in_min";
        public static final String COLUMN_MOST_POPULAR = "most_popular";

        public static Uri buildRestaurantUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class RestaurantTagEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT_TAGS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_TAGS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_TAGS;

        public static final String TABLE_NAME = PATH_RESTAURANT_TAGS;

        public static final String COLUMN_RESTAURANT_ID = "restaurant_id";
        public static final String COLUMN_TAG = "tag";

        public static Uri buildRestaurantTagUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
