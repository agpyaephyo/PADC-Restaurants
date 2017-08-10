package xyz.aungpyaephyo.padc.restaurants.views.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.delegates.RestaurantViewHolderDelegate;
import xyz.aungpyaephyo.padc.restaurants.views.holders.base.BaseViewHolder;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {

    @BindView(R.id.iv_restaurant)
    ImageView ivRestaurant;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_tags)
    TextView tvTags;

    @BindView(R.id.tv_most_popular)
    TextView tvMostPopular;

    @BindView(R.id.tv_travel_time_car)
    TextView tvTravelTimeCar;

    @BindView(R.id.tv_travel_time_bus)
    TextView tvTravelTimeBus;

    private RestaurantViewHolderDelegate mDelegate;

    private RestaurantVO mData;

    public RestaurantViewHolder(View itemView, RestaurantViewHolderDelegate delegate) {
        super(itemView);
        mDelegate = delegate;
    }

    @Override
    public void bind(RestaurantVO data) {
        mData = data;
        tvTitle.setText(data.getTitle());
        Glide.with(ivRestaurant.getContext())
                .load(data.getImage())
                .placeholder(R.mipmap.padc_restaurant_icon_two_192)
                .error(R.mipmap.padc_restaurant_icon_two_192)
                .into(ivRestaurant);
        tvTags.setText(RestaurantVO.getTagsForDisplay(data));
        tvMostPopular.setText(data.getMostPopular());
    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapRestaurantItem(mData);
    }
}
