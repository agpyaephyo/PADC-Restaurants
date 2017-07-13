package xyz.aungpyaephyo.padc.restaurants.events;

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
        private List<RestaurantVO> restaurantList;

        public RestaurantListLoadedEvent(List<RestaurantVO> restaurantList) {
            this.restaurantList = restaurantList;
        }

        public List<RestaurantVO> getRestaurantList() {
            return restaurantList;
        }
    }
}
