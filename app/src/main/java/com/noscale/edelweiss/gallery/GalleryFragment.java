package com.noscale.edelweiss.gallery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Gallery;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.gallery.creation.GalleryCreationActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 18/08/20.
 */
public class GalleryFragment extends BaseFragment  implements GalleryContract.View {

    private SimpleRecyclerAdapter<Gallery> mAdapter;

    public static GalleryFragment newInstance () {
        return new GalleryFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.rv_fragment_list);
        mEmptyView = view.findViewById(R.id.inc_fragment_empty);

        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);
        FloatingActionButton fabCreation = view.findViewById(R.id.fab_fragment_create);
        tvTitle.setText(getString(R.string.gallery_txt));
        fabCreation.setOnClickListener((v) -> {
            Intent i = new Intent(getContext(), GalleryCreationActivity.class);
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
    public void setPresenter(GalleryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void append(List<Gallery> galleries) {
        showProgressView(false);

        mAdapter = new SimpleRecyclerAdapter<>(galleries, R.layout.item_gallery, new SimpleRecyclerAdapter.OnViewHolder<Gallery>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Gallery item) {
                View view = holder.itemView;

                File f = new File(item.getImageUrl());
                AppCompatImageView ivGallery = view.findViewById(R.id.iv_gallery_item);

                if (f.exists()) {
                    Picasso.get().load(f).into(ivGallery);
                }
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        RecyclerView rvList = getView().findViewById(R.id.rv_fragment_list);
        rvList.setLayoutManager(layoutManager);

        rvList.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                showProgressView(true);
                ((GalleryContract.Presenter) mPresenter).fetch();
            }
        });
    }
}
