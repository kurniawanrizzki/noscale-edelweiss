package com.noscale.edelweiss.gallery;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.gallery.list.ListFragment;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class GalleryFragment extends BaseFragment implements GalleryContract.View {

    private DrawerLayout mDrawerLayout;

    public static GalleryFragment newInstance () {
        return new GalleryFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDrawerLayout = view.findViewById(R.id.dl_gallery_container);
        mMainView = view.findViewById(R.id.fr_gallery_container);
        mProgressView = view.findViewById(R.id.inc_gallery_progress);

        mDrawerLayout.openDrawer(GravityCompat.END);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fr_gallery_container, ListFragment.newInstance()).commit();
    }

    @Override
    public void onResume() {
        showProgressView(true);
        super.onResume();
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_gallery;
    }

    @Override
    public void setPresenter(GalleryContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
