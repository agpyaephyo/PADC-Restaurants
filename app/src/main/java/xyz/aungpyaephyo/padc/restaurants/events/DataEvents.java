package xyz.aungpyaephyo.padc.restaurants.events;

import android.content.Context;

import java.util.List;

import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;

/**
 * Created by aung on 7/13/17.
 */

public class DataEvents {

    public static class EmptyResponseBodyEvent {

    }

    public static class RequestErrorEvent {
        private String message;

        public RequestErrorEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class RestaurantListLoadedEvent {
        private Context context;
        private List<RestaurantVO> restaurantList;

        public RestaurantListLoadedEvent(Context context, List<RestaurantVO> restaurantList) {
            this.context = context;
            this.restaurantList = restaurantList;
        }

        public List<RestaurantVO> getRestaurantList() {
            return restaurantList;
        }

        public Context getContext() {
            return context;
        }
    }
}
