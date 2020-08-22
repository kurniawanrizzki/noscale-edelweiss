package com.noscale.edelweiss.wp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.wp.creation.WeddingPackageCreationActivity;
import com.noscale.edelweiss.wp.details.WeddingPackageDetailActivity;
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
    protected int getResLayout() {
        return R.layout.layout_fragment_list;
    }

    @Override
    protected View.OnClickListener getFabClickedListener() {
        return (v) -> goToPackageCreation();
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
    public void goToDetail(WeddingPackage wp) {
        Intent i = new Intent(getContext(), WeddingPackageDetailActivity.class);
        i.putExtra(WeddingPackageDetailActivity.DETAIL_ARG, wp);
        startActivityForResult(i, WeddingPackageDetailActivity.DETAIL_REQUEST_CODE);
    }

    @Override
    public void goToPackageCreation() {
        Intent i = new Intent(getContext(), WeddingPackageCreationActivity.class);
        startActivityForResult(i, WeddingPackageCreationActivity.WEDDING_PACKAGE_CREATION_REQUEST_CODE);
    }

    @Override
    public void delete(WeddingPackage item) {
        showMessage(getString(R.string.warning_title_txt), getString(R.string.deletion_txt), (dialogInterface, i) -> {
            showProgressView(true);
            ((WeddingPackageContract.Presenter) mPresenter).delete(item.getId());
        }, (dialogInterface, i) -> {

        });
    }

    @Override
    public void appendView(List<WeddingPackage> wps) {
        showProgressView(false);
        showEmptyView(wps.isEmpty());

        mAdapter = new SimpleRecyclerAdapter<>(wps, R.layout.item_wedding_package, new SimpleRecyclerAdapter.OnViewHolder<WeddingPackage>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, WeddingPackage item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_wp_name);
                TextView tvPrice = holder.itemView.findViewById(R.id.tv_wp_price);
                TextView tvDetail = holder.itemView.findViewById(R.id.tv_wp_detail);
                TextView tvDeletion = holder.itemView.findViewById(R.id.tv_wp_deletion);

                tvName.setText(item.getName());
                tvPrice.setText(item.getPrice());

                tvDeletion.setOnClickListener((v) -> delete(item));
                tvDetail.setOnClickListener((v) -> goToDetail(item));
            }
        });

        RecyclerView rvWeddingPackage = getView().findViewById(R.id.rv_fragment_list);
        rvWeddingPackage.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            ((WeddingPackageContract.Presenter) mPresenter).getPackages();
        }, ((dialogInterface, i) -> getActivity().finish()
        ));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == WeddingPackageCreationActivity.WEDDING_PACKAGE_CREATION_REQUEST_CODE) || requestCode == WeddingPackageDetailActivity.DETAIL_REQUEST_CODE) {
            if (resultCode == WeddingPackageCreationActivity.RESULT_OK) {
                showProgressView(true);
                ((WeddingPackageContract.Presenter) mPresenter).getPackages();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
