package xyz.aungpyaephyo.padc.restaurants.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.adapters.MealInRestaurantAdapter;
import xyz.aungpyaephyo.padc.restaurants.components.rvset.SmartRecyclerView;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract;

public class RestaurantDetailsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String IE_RESTAURANT_TITLE = "IE_RESTAURANT_TITLE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_meals_in_restaurant)
    SmartRecyclerView rvMealsInRestaurant;

    @BindView(R.id.iv_restaurant)
    ImageView ivRestaurant;

    private MealInRestaurantAdapter mMealInRestaurantAdapter;

    private String mRestaurantTitle;

    public static Intent newIntent(Context context, RestaurantVO restaurant) {
        Intent intent = new Intent(context, RestaurantDetailsActivity.class);
        intent.putExtra(IE_RESTAURANT_TITLE, restaurant.getTitle());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        ButterKnife.bind(this, this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mRestaurantTitle = bundle.getString(IE_RESTAURANT_TITLE);
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mRestaurantTitle);
        }

        mMealInRestaurantAdapter = new MealInRestaurantAdapter(getApplicationContext());
        rvMealsInRestaurant.setAdapter(mMealInRestaurantAdapter);
        rvMealsInRestaurant.setupGridLayoutManager(1, true);

        /*
        getSupportLoaderManager()
                .initLoader(RestaurantsConstants.RESTAURANT_DETAILS_LOADER,
                        null, this);
                        */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                RestaurantsContract.RestaurantEntry.CONTENT_URI,
                null,
                RestaurantsContract.RestaurantEntry.COLUMN_TITLE + " = ?",
                new String[]{mRestaurantTitle},
                null
        );
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);

            Cursor restaurantTagsCursor = getContentResolver().query(RestaurantsContract.RestaurantTagEntry.CONTENT_URI,
                    null,
                    RestaurantsContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " = ?",
                    new String[]{restaurant.getTitle()},
                    null);

            restaurant.setTagList(RestaurantVO.parseRestaurantTagsFromCursor(restaurantTagsCursor));

            bindData(restaurant);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }

    @OnClick(R.id.fab)
    public void onTapFAB(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void bindData(RestaurantVO restaurant) {
        Glide.with(ivRestaurant.getContext())
                .load(restaurant.getImage())
                .placeholder(R.drawable.placeholder_empty_data)
                .error(R.drawable.placeholder_empty_data)
                .into(ivRestaurant);
    }
}
