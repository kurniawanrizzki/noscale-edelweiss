package com.noscale.edelweiss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.noscale.edelweiss.common.UICommon;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public abstract class BaseFragment extends Fragment {

    protected View mMainView;

    protected View mProgressView;

    protected BasePresenter mPresenter;

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

        mMainView = view.findViewById(R.id.cl_fragment_container);
        mProgressView = view.findViewById(R.id.inc_fragment_progress);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (null != mPresenter) {
            mPresenter.start();
        }
    }

    protected abstract int getResLayout ();

    protected void showProgressView(boolean isShow) {
        UICommon.showProgressView(mMainView, mProgressView, isShow);
    }

    protected void showMessage(String title, String message) {
        showProgressView(false);
        UICommon.showDialog(getContext(), title, message);
    }
}
