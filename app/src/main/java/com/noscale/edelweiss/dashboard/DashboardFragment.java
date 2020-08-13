package com.noscale.edelweiss.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.ModuleCommon;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Module;

import java.util.ArrayList;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    private SimpleRecyclerAdapter<Module> mAdapter;

    public static DashboardFragment newInstance () {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_dashboard,
                container,
                false
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new SimpleRecyclerAdapter<>(ModuleCommon.getModules(), R.layout.item_module, new SimpleRecyclerAdapter.OnViewHolder<Module>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Module item) {
                AppCompatImageView ivIcon = holder.itemView.findViewById(R.id.iv_module_icon);
                TextView tvTitle = holder.itemView.findViewById(R.id.tv_module_title);

                ivIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), item.getIcon()));
                tvTitle.setText(item.getTitle());

                holder.itemView.setOnClickListener(item.getListener());
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView rvModule = view.findViewById(R.id.rv_dashboard_modules);

        rvModule.setLayoutManager(layoutManager);
        rvModule.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressView(boolean isShow) {
        UICommon.showProgressView(mMainView, mProgressView, isShow);
    }
}
