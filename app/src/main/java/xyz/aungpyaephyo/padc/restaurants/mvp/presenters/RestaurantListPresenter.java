package xyz.aungpyaephyo.padc.restaurants.mvp.presenters;

import java.util.List;

import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.mvp.presenters.base.BasePresenter;
import xyz.aungpyaephyo.padc.restaurants.mvp.views.RestaurantListView;

/**
 * Created by aung on 7/14/17.
 */

public class RestaurantListPresenter extends BasePresenter {

    private RestaurantListView mView;

    public RestaurantListPresenter(RestaurantListView view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void onRestaurantListLoaded(List<RestaurantVO> restaurantList) {
        //Can do all the pre-processing before showing the data.
        mView.displayRestaurantList(restaurantList);
    }
}
