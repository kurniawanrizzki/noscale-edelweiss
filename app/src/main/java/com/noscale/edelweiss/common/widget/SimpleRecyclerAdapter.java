package com.noscale.edelweiss.common.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<SimpleRecyclerAdapter.SimpleViewHolder> {

    protected List<T> mMainData;

    private int mLayoutRes;

    private OnViewHolder<T> mListener;

    public SimpleRecyclerAdapter (List<T> mainData, int layoutRes, OnViewHolder<T> listener) {
        this.mMainData = mainData;
        this.mLayoutRes = layoutRes;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                mLayoutRes,
                parent,
                false
        );

        return new SimpleViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mMainData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        T item = mMainData.get(position);
        mListener.onBindView(holder, item);
    }

    public void addItemAt (int index, T item) {
        mMainData.add(index, item);
    }

    public void addItem (T item) {
        mMainData.add(item);
    }

    public List<T> getMainData () {
        return mMainData;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnViewHolder<T> {
        void onBindView(SimpleViewHolder holder, T item);
    }
}
