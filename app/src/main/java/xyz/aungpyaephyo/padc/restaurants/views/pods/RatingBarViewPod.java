package xyz.aungpyaephyo.padc.restaurants.views.pods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.components.ratingloveanim.LoveAnimView;

/**
 * Created by aung on 7/13/17.
 */

public class RatingBarViewPod extends LinearLayout {

    @BindView(R.id.iv_love_one)
    LoveAnimView ivLoveOne;

    @BindView(R.id.iv_love_two)
    LoveAnimView ivLoveTwo;

    @BindView(R.id.iv_love_three)
    LoveAnimView ivLoveThree;

    @BindView(R.id.iv_love_four)
    LoveAnimView ivLoveFour;

    @BindView(R.id.iv_love_five)
    LoveAnimView ivLoveFive;

    private int mAppRating;

    public RatingBarViewPod(Context context) {
        super(context);
    }

    public RatingBarViewPod(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingBarViewPod(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.iv_love_one)
    public void onTapLoveOne(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }

        mAppRating = 1;
    }

    @OnClick(R.id.iv_love_two)
    public void onTapLoveTwo(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        ivLoveTwo.onClick(ivLoveTwo);

        if (ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }
        mAppRating = 2;
    }

    @OnClick(R.id.iv_love_three)
    public void onTapLoveThree(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        ivLoveThree.onClick(ivLoveThree);

        if (ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFive);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }
        mAppRating = 3;
    }

    @OnClick(R.id.iv_love_four)
    public void onTapLoveFour(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (!ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        ivLoveFour.onClick(ivLoveFour);

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }
        mAppRating = 4;
    }

    @OnClick(R.id.iv_love_five)
    public void onTapLoveFive(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (!ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (!ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        ivLoveFive.onClick(ivLoveFive);
        mAppRating = 5;
    }
}
