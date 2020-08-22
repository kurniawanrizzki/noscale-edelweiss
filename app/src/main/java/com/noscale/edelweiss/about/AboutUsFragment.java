package com.noscale.edelweiss.about;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class AboutUsFragment extends BaseFragment implements AboutUsContract.View {

    public static AboutUsFragment newInstance () {
        return new AboutUsFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(AboutUsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_about_us;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void append(String content) {
        showProgressView(false);

        TextView tvContent = getView().findViewById(R.id.tv_about_content);
        tvContent.setText(content);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message);
    }
}
