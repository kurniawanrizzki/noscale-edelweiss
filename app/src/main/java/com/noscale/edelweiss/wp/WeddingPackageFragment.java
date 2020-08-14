package com.noscale.edelweiss.wp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.WeddingPackage;
import java.util.ArrayList;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackageFragment extends BaseFragment implements WeddingPackageContract.View {

    private SimpleRecyclerAdapter<WeddingPackage> mAdapter;

    public static WeddingPackageFragment newInstance () {
        return new WeddingPackageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.widget_fragment_with_title,
                container,
                false
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new SimpleRecyclerAdapter<>(new ArrayList<WeddingPackage>(), R.layout.item_wedding_package, new SimpleRecyclerAdapter.OnViewHolder<WeddingPackage>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, WeddingPackage item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_wp_name);
                TextView tvPrice = holder.itemView.findViewById(R.id.tv_wp_price);

                tvName.setText(item.getName());
                tvPrice.setText(item.getPrice());
            }
        });

        RecyclerView rvWeddingPackage = view.findViewById(R.id.rv_fragment_list);
        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);

        rvWeddingPackage.setAdapter(mAdapter);
        tvTitle.setText(getString(R.string.package_txt));
    }

    @Override
    public void setPresenter(WeddingPackageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showProgressView(boolean isShow) {
        UICommon.showProgressView(mMainView, mProgressView, isShow);
    }
}
