package com.noscale.edelweiss.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class EdelweissConstraintLayout extends ConstraintLayout {

    private ConstraintLayout mMainView;

    private View mProgressView;

    private View mEmptyView;

    public EdelweissConstraintLayout(Context context) {
        super(context);
        init(context);
    }

    public EdelweissConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EdelweissConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init (Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_edelweiss_constraint_layout, this);

        mMainView = findViewById(R.id.cl_layout_container);
        mProgressView = findViewById(R.id.inc_layout_progress);
        mEmptyView = findViewById(R.id.inc_layout_empty);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if ((null == mMainView) || (null == mProgressView)) {
            super.addView(child, index, params);
            return;
        }

        mMainView.addView(child, index, params);
    }

    public void showProgressView (boolean isShow) {
        if (isShow) {
            mProgressView.setVisibility(VISIBLE);
            mMainView.setVisibility(GONE);

            return;
        }

        mProgressView.setVisibility(GONE);
        mMainView.setVisibility(VISIBLE);
    }

    public void showEmptyView (boolean isShow) {
        if (isShow) {
            mProgressView.setVisibility(GONE);
            mMainView.setVisibility(GONE);
            mEmptyView.setVisibility(VISIBLE);

            return;
        }

        mProgressView.setVisibility(GONE);
        mMainView.setVisibility(VISIBLE);
        mEmptyView.setVisibility(GONE);
    }
}
