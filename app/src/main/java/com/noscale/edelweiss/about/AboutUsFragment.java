package com.noscale.edelweiss.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_about_us,
                container,
                false
        );

        return view;
    }


    @Override
    public void setPresenter(AboutUsContract.Presenter presenter) {

    }

    @Override
    public void showProgressView(boolean isShow) {

    }
}
