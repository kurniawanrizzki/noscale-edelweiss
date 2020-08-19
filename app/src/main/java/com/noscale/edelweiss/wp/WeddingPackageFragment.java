package com.noscale.edelweiss.wp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.wp.creation.WeddingPackageCreationActivity;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackageFragment extends BaseFragment implements WeddingPackageContract.View {

    private SimpleRecyclerAdapter<WeddingPackage> mAdapter;

    public static WeddingPackageFragment newInstance () {
        return new WeddingPackageFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.rv_fragment_list);
        mEmptyView = view.findViewById(R.id.inc_fragment_empty);

        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);
        FloatingActionButton bCreation = view.findViewById(R.id.fab_fragment_create);

        tvTitle.setText(getString(R.string.package_txt));

        bCreation.setOnClickListener((v) -> {
            Intent i = new Intent(getContext(), WeddingPackageCreationActivity.class);
            startActivity(i);
        });

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.widget_fragment_with_title;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        User.Type type = AppConfiguration.getInstance(getContext()).getAuthenticatedUserType();
        return type == User.Type.ADMIN;
    }

    @Override
    public void setPresenter(WeddingPackageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void appendView(List<WeddingPackage> wps) {
        showProgressView(false);

        if (wps.isEmpty()) {
            mMainView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
            return;
        }

        mAdapter = new SimpleRecyclerAdapter<>(wps, R.layout.item_wedding_package, new SimpleRecyclerAdapter.OnViewHolder<WeddingPackage>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, WeddingPackage item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_wp_name);
                TextView tvPrice = holder.itemView.findViewById(R.id.tv_wp_price);

                tvName.setText(item.getName());
                tvPrice.setText(item.getPrice());
            }
        });

        RecyclerView rvWeddingPackage = getView().findViewById(R.id.rv_fragment_list);
        rvWeddingPackage.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                showProgressView(true);
                ((WeddingPackageContract.Presenter) mPresenter).getPackages();
            }
        });
    }
}
