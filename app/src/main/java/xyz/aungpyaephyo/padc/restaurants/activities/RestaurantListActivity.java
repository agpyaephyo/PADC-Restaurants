package xyz.aungpyaephyo.padc.restaurants.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.RestaurantsApp;
import xyz.aungpyaephyo.padc.restaurants.activities.base.BaseActivity;
import xyz.aungpyaephyo.padc.restaurants.adapters.RestaurantListAdapter;
import xyz.aungpyaephyo.padc.restaurants.components.mmfont.MMToolbar;
import xyz.aungpyaephyo.padc.restaurants.components.rvset.SmartRecyclerView;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.delegates.RestaurantViewHolderDelegate;
import xyz.aungpyaephyo.padc.restaurants.mvp.presenters.RestaurantListPresenter;
import xyz.aungpyaephyo.padc.restaurants.mvp.views.RestaurantListView;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract;
import xyz.aungpyaephyo.padc.restaurants.utils.RestaurantsConstants;
import xyz.aungpyaephyo.padc.restaurants.views.pods.EmptyViewPod;

public class RestaurantListActivity extends BaseActivity
        implements RestaurantListView,
        RestaurantViewHolderDelegate,
        LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_restaurants)
    SmartRecyclerView rvRestaurants;

    @BindView(R.id.vp_empty_restaurant_list)
    EmptyViewPod vpEmptyRestaurantList;

    @BindView(R.id.toolbar)
    MMToolbar toolbar;

    private RestaurantListAdapter mRestaurantListAdapter;

    @Inject
    RestaurantListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        ((RestaurantsApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this, this);

        toolbar.setTitle(getString(R.string.restaurants_list));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Search will come later", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRestaurantListAdapter = new RestaurantListAdapter(getApplicationContext(), this);

        vpEmptyRestaurantList.setEmptyLabel(getString(R.string.msg_empty_restaurant));
        vpEmptyRestaurantList.setEmptyImage(R.drawable.empty_restaurant_list);

        rvRestaurants.setEmptyView(vpEmptyRestaurantList);
        rvRestaurants.setAdapter(mRestaurantListAdapter);

        getSupportLoaderManager()
                .initLoader(RestaurantsConstants.RESTAURANT_LIST_LOADER,
                        null, this);

        mPresenter.setView(this);

        helloRxJava("the", "real", "PADC", ":", "Android", "Developer", "Course");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                RestaurantsContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<RestaurantVO> restaurantList = new ArrayList<>();
            do {
                RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);

                Cursor restaurantTagsCursor = getContentResolver().query(RestaurantsContract.RestaurantTagEntry.CONTENT_URI,
                        null,
                        RestaurantsContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " = ?",
                        new String[]{restaurant.getTitle()},
                        null);

                restaurant.setTagList(RestaurantVO.parseRestaurantTagsFromCursor(restaurantTagsCursor));
                restaurantList.add(restaurant);
            } while (data.moveToNext());

            mPresenter.onRestaurantListLoaded(restaurantList);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayRestaurantList(List<RestaurantVO> restaurantList) {
        Log.d(RestaurantsApp.TAG, "Displaying Restaurants : " + restaurantList.size());
        mRestaurantListAdapter.setNewData(restaurantList);
    }

    @Override
    public void onTapRestaurantItem(RestaurantVO restaurant) {
        Intent intent = RestaurantDetailsActivity.newIntent(getApplicationContext(), restaurant);
        startActivity(intent);
    }

    private void helloRxJava(String... names) {
        Observable<String> nameObservable = Observable.fromArray(names);
        nameObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String name) {
                Log.d(RestaurantsApp.TAG, "Rx : \"" + name + "\"" + " has " + name.length() + " characters.");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
