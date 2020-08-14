package com.noscale.edelweiss;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class BaseFragment extends Fragment {

    protected View mMainView;

    protected View mProgressView;

    protected BasePresenter mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_fragment_container);
        mProgressView = view.findViewById(R.id.ll_progress_container);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (null != mPresenter) {
            mPresenter.start();
        }
    }
}
