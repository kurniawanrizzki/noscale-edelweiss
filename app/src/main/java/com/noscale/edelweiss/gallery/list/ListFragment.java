package com.noscale.edelweiss.gallery.list;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class ListFragment extends BaseFragment {

    public static ListFragment newInstance () {
        return new ListFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getResLayout() {
        return R.layout.widget_fragment_with_title;
    }
}
