package xyz.aungpyaephyo.padc.restaurants.data.models;

import xyz.aungpyaephyo.padc.restaurants.data.models.base.BaseModel;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantsModel extends BaseModel {

    private static RestaurantsModel objInstance;

    private RestaurantsModel() {
        super();
    }

    public static RestaurantsModel getInstance() {
        if (objInstance == null) {
            objInstance = new RestaurantsModel();
        }

        return objInstance;
    }

    public void loadRestaurants() {
        mDataAgent.loadRestaurants();
    }
}
