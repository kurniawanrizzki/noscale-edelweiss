package com.noscale.edelweiss;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.widget.EdelweissConstraintLayout;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public abstract class BaseFragment extends Fragment {

    private EdelweissConstraintLayout mView;

    protected FloatingActionButton mFab;

    protected View mMainView;

    protected View mProgressView;

    protected BasePresenter mPresenter;

    protected boolean isProgressShown;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                getResLayout(),
                container,
                false
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getView() instanceof EdelweissConstraintLayout) {
            mView = (EdelweissConstraintLayout) getView();
        } else {
            mView = view.findViewById(R.id.cl_fragment_container);
        }

        mFab = view.findViewById(R.id.fab_fragment_create);
        if (null != mFab) mFab.setOnClickListener(getFabClickedListener());

        setViewByAccessType(mFab);

        initialLoad();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (null != mPresenter) {
            mPresenter.start();
        }
    }

    protected abstract int getResLayout ();

    protected View.OnClickListener getFabClickedListener () {
        return null;
    };

    protected void showProgressView(boolean isShow) {
        isProgressShown = isShow;

        showFab(!isShow);
        mView.showProgressView(isShow);
    }

    protected void showEmptyView (boolean isShow) {
        showFab(isShow);
        mView.showEmptyView(isShow);
    }

    private void showFab (boolean isShow) {
        if (null == mFab) return;

        if (isShow && isAccessTypeAccepted()) {
            mFab.setVisibility(View.VISIBLE);
            return;
        }

        if (!isProgressShown && isAccessTypeAccepted()) {
            showFab(true);
            return;
        }

        mFab.setVisibility(View.GONE);
    }

    private void initialLoad () {
        if ((null != mPresenter) && mPresenter.isDataMissing()) {
            showProgressView(true);
        }
    }

    protected void showMessage(String title, String message) {
        showMessage(title, message, null);
    }

    protected void showMessage(String title, String message, DialogInterface.OnClickListener listener) {
        showProgressView(false);
        showMessage(title, message, listener, null);
    }

    protected void showMessage(String title, String message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        showProgressView(false);
        UICommon.showDialog(getContext(), title, message, positiveListener, negativeListener);
    }

    protected void setViewByAccessType (View view) {
        if (null == view) return;

        if (isAccessTypeAccepted()) {
            view.setVisibility(View.VISIBLE);
            return;
        }

        view.setVisibility(View.GONE);
    }

    protected abstract boolean isAccessTypeAccepted ();
}
