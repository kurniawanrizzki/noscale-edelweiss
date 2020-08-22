package com.noscale.edelweiss.wp.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.WeddingPackage;
import com.noscale.edelweiss.data.WeddingPackageDetail;
import com.noscale.edelweiss.wp.creation.WeddingPackageCreationActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class WeddingPackageDetailFragment extends BaseFragment implements WeddingPackageDetailContract.View {

    private WeddingPackage mWeddingPackage;

    public static WeddingPackageDetailFragment newInstance (WeddingPackage wp) {
        WeddingPackageDetailFragment fragment = new WeddingPackageDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable(WeddingPackageDetailActivity.DETAIL_ARG, wp);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tvActionTool = getActivity().findViewById(R.id.tv_action_bar_tool);
        tvActionTool.setText(R.string.edit_txt);
        tvActionTool.setVisibility(View.VISIBLE);

        tvActionTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), WeddingPackageCreationActivity.class);
                i.setAction(WeddingPackageCreationActivity.ACTION_EDIT);
                i.putExtra(WeddingPackageDetailActivity.DETAIL_ARG, mWeddingPackage);
                startActivityForResult(i, WeddingPackageCreationActivity.WEDDING_PACKAGE_CREATION_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments().containsKey(WeddingPackageDetailActivity.DETAIL_ARG)) {
            mWeddingPackage = getArguments().getParcelable(WeddingPackageDetailActivity.DETAIL_ARG);
        }

        TextView tvName = view.findViewById(R.id.tv_package_name);
        TextView tvPrice = view.findViewById(R.id.tv_package_price);
        TextView tvTotal = view.findViewById(R.id.tv_package_total);
        TextView tvBuffet = view.findViewById(R.id.tv_package_buffet);
        RecyclerView rvDetail = view.findViewById(R.id.rv_package_detail);
        RecyclerView rvBonus = view.findViewById(R.id.rv_package_bonus);

        tvName.setText(mWeddingPackage.getName());
        tvPrice.setText(mWeddingPackage.getPrice());
        tvTotal.setText(mWeddingPackage.getTotalBuffet());
        tvBuffet.setText(mWeddingPackage.getBuffetName());

        SimpleRecyclerAdapter<WeddingPackageDetail> detailAdapter = new SimpleRecyclerAdapter<>(
                mWeddingPackage.getDetailPackages(),
                R.layout.item_form_wp,
                (holder, item) -> {
                    View v = holder.itemView;

                    SimpleRecyclerAdapter<String> childAdapter = new SimpleRecyclerAdapter<>(
                            item.getDetails(), R.layout.item_edelweiss_spinner, (cHolder, cItem) -> {
                        View cView = cHolder.itemView;

                        TextView tvText = cView.findViewById(R.id.text1);
                        tvText.setText(cItem);
                    });

                    TextView tvTitle = v.findViewById(R.id.tv_form_wp_main);
                    RecyclerView rvList = v.findViewById(R.id.rv_form_wp_child);

                    tvTitle.setText(item.getName());
                    rvList.setAdapter(childAdapter);
                }
        );

        SimpleRecyclerAdapter<String> bonusAdapter = new SimpleRecyclerAdapter<>(
                mWeddingPackage.getBonus(),
                R.layout.item_edelweiss_spinner,
                (holder, item) -> {
                    View v = holder.itemView;

                    TextView tvContent = v.findViewById(R.id.text1);
                    tvContent.setText(item);
                }
        );

        rvDetail.setAdapter(detailAdapter);
        rvBonus.setAdapter(bonusAdapter);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_wp_detail;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(WeddingPackageDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
