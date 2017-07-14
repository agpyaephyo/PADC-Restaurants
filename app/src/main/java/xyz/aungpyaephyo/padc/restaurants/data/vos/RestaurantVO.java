package xyz.aungpyaephyo.padc.restaurants.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract.RestaurantEntry;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract.RestaurantTagEntry;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("addr-short")
    private String shortAddress;

    @SerializedName("total-reating-count")
    private int totalRatingCount;

    @SerializedName("average-rating-value")
    private double averageRatingValue;

    @SerializedName("is-ad")
    private boolean isAd;

    @SerializedName("is-new")
    private boolean isNew;

    @SerializedName("lead-time-in-min")
    private int leadTimeInMin;

    @SerializedName("tags")
    private List<String> tagList;

    @SerializedName("most-popular")
    private String mostPopular;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getShortAddress() {
        return shortAddress;
    }

    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    public double getAverageRatingValue() {
        return averageRatingValue;
    }

    public boolean isAd() {
        return isAd;
    }

    public boolean isNew() {
        return isNew;
    }

    public int getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public String getMostPopular() {
        return mostPopular;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantEntry.COLUMN_IMAGE, image);
        cv.put(RestaurantEntry.COLUMN_SHORT_ADDR, shortAddress);
        cv.put(RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCount);
        cv.put(RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE, averageRatingValue);
        cv.put(RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantEntry.COLUMN_IS_NEW, isNew);
        cv.put(RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, leadTimeInMin);
        cv.put(RestaurantEntry.COLUMN_MOST_POPULAR, mostPopular);
        return cv;
    }

    public List<ContentValues> parseRestaurantTagToContentValues() {
        List<ContentValues> contentValues = new ArrayList<>();
        for (String tag : tagList) {
            ContentValues cv = new ContentValues();
            cv.put(RestaurantTagEntry.COLUMN_RESTAURANT_TITLE, title);
            cv.put(RestaurantTagEntry.COLUMN_TAG, tag);
            contentValues.add(cv);
        }
        return contentValues;
    }

    public static RestaurantVO parseFromCursor(Cursor data) {
        RestaurantVO restaurant = new RestaurantVO();
        restaurant.title = data.getString(data.getColumnIndex(
                RestaurantEntry.COLUMN_TITLE));
        restaurant.image = data.getString(data.getColumnIndex(
                RestaurantEntry.COLUMN_IMAGE));
        restaurant.shortAddress = data.getString(data.getColumnIndex(
                RestaurantEntry.COLUMN_SHORT_ADDR));
        restaurant.totalRatingCount = data.getInt(data.getColumnIndex(
                RestaurantEntry.COLUMN_TOTAL_RATING_COUNT));
        restaurant.averageRatingValue = data.getInt(data.getColumnIndex(
                RestaurantEntry.COLUMN_AVERAGE_RATING_VALUE));
        restaurant.isAd = data.getInt(data.getColumnIndex(
                RestaurantEntry.COLUMN_IS_AD)) == 1;
        restaurant.isNew = data.getInt(data.getColumnIndex(
                RestaurantEntry.COLUMN_IS_NEW)) == 1;
        restaurant.leadTimeInMin = data.getInt(data.getColumnIndex(
                RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN));
        restaurant.mostPopular = data.getString(data.getColumnIndex(
                RestaurantEntry.COLUMN_MOST_POPULAR));

        return restaurant;
    }

    public static List<String> parseRestaurantTagsFromCursor(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<String> tagList = new ArrayList<>();
            do {
                tagList.add(data.getString(data.getColumnIndex(RestaurantTagEntry.COLUMN_TAG)));
            } while (data.moveToNext());
            data.close();
            return tagList;
        }

        return new ArrayList<>();
    }

    public static String getTagsForDisplay(RestaurantVO data) {
        String tagsForDisplay = "";
        int index = 0;
        for (String tag : data.getTagList()) {
            if (index < data.getTagList().size() - 1) {
                tagsForDisplay += tag + ", ";
            } else {
                tagsForDisplay += tag;
            }

            index++;
        }
        return tagsForDisplay;
    }
}
