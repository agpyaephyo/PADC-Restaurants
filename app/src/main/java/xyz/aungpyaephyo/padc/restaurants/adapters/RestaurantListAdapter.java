package xyz.aungpyaephyo.padc.restaurants.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.delegates.RestaurantViewHolderDelegate;
import xyz.aungpyaephyo.padc.restaurants.views.holders.RestaurantViewHolder;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantListAdapter extends BaseRecyclerAdapter<RestaurantViewHolder, RestaurantVO> {

    RestaurantViewHolderDelegate mRestaurantViewHolderDelegate;

    public RestaurantListAdapter(Context context, RestaurantViewHolderDelegate restaurantViewHolderDelegate) {
        super(context);
        mRestaurantViewHolderDelegate = restaurantViewHolderDelegate;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_restaurants, parent, false);
        return new RestaurantViewHolder(itemView, mRestaurantViewHolderDelegate);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
