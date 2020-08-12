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
        LayoutInflater.from(context).inflate(R.layout.widget_fragment, this);

        mMainView = findViewById(R.id.cl_fragment_container);
        mProgressView = findViewById(R.id.ll_progress_container);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if ((null == mMainView) || (null == mProgressView)) {
            super.addView(child, index, params);
            return;
        }

        mMainView.addView(child, index, params);
    }
}
