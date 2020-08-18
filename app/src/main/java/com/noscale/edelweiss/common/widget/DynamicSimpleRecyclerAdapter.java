package com.noscale.edelweiss.common.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 19/08/20.
 */
public class DynamicSimpleRecyclerAdapter<T> extends SimpleRecyclerAdapter<T> {

    private static final int DEFAULT_VIEW_TYPE = 0;

    private static final int VIEW_TYPE = 1;

    private int mDefaultResLayout;

    private OnViewHolder<T> mDefaultListener;

    public DynamicSimpleRecyclerAdapter(List<T> mainData, int defaultResLayout, int layoutRes, OnViewHolder<T> defaultListener, OnViewHolder<T> listener) {
        super(mainData, layoutRes, listener);

        this.mDefaultResLayout = defaultResLayout;
        this.mDefaultListener = defaultListener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DEFAULT_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    mDefaultResLayout,
                    parent,
                    false
            );
            return new SimpleViewHolder(view);
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        T item = mMainData.get(position);

        if (null == item) {
            mDefaultListener.onBindView(holder, item);
            return;
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        T item = mMainData.get(position);
        return (null == item) ? DEFAULT_VIEW_TYPE : VIEW_TYPE;
    }
}
