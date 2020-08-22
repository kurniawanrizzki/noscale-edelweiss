package com.noscale.edelweiss.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Category;
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

    private PopupMenu mFilterMenu;

    public static GalleryFragment newInstance () {
        return new GalleryFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tvActionTool = getActivity().findViewById(R.id.tv_action_bar_tool);
        tvActionTool.setText(R.string.filter_txt);
        tvActionTool.setVisibility(View.VISIBLE);
        tvActionTool.setOnClickListener((v) -> mFilterMenu.show());

        mFilterMenu = new PopupMenu(getContext(), tvActionTool);
        mFilterMenu.getMenu().add("All");

        mFilterMenu.setOnMenuItemClickListener((menuItem) -> {
            showProgressView(true);

            String title = menuItem.getTitle().toString();
            ((GalleryContract.Presenter) mPresenter).filter(title);
            return false;
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.layout_fragment_list;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        User.Type type = AppConfiguration.getInstance(getContext()).getAuthenticatedUserType();
        return type == User.Type.ADMIN;
    }

    @Override
    protected View.OnClickListener getFabClickedListener() {
        return (v) -> goToGalleryCreation();
    }

    @Override
    public void setPresenter(GalleryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void goToGalleryCreation() {
        Intent i = new Intent(getContext(), GalleryCreationActivity.class);
        startActivity(i);
    }

    @Override
    public void addToFilter(List<Category> categories) {
        for (Category c : categories) {
            MenuItem item = mFilterMenu.getMenu().findItem(c.getId());
            if (null != item) continue;

            mFilterMenu.getMenu().add(1, c.getId(), c.getId(), c.getName());
        }
    }

    @Override
    public void append(List<Gallery> galleries) {
        showProgressView(false);
        showEmptyView(galleries.isEmpty());

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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GalleryCreationActivity.GALLERY_CREATION_REQUEST_CODE) {
            if (resultCode == GalleryCreationActivity.RESULT_OK) {
                showProgressView(true);
                ((GalleryContract.Presenter) mPresenter).fetch();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            ((GalleryContract.Presenter) mPresenter).fetch();
        }, (dialogInterface, i) -> getActivity().finish());
    }
}
