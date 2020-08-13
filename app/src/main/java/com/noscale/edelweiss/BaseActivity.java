package com.noscale.edelweiss;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected BaseFragment mFragment;

    protected BasePresenter mPresenter;

    protected static final String SHOULD_LOAD_FROM_REPOSITORY_KEY = "SHOULD_LOAD_FROM_REPOSITORY_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init(savedInstanceState);
        draw();
    }

    protected abstract void init (Bundle savedInstanceState);

    private void draw () {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_activity_container, mFragment).commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(SHOULD_LOAD_FROM_REPOSITORY_KEY, mPresenter.isDataMissing());
        super.onSaveInstanceState(outState);
    }

    protected boolean shouldLoadDataFromRepository (Bundle savedInstanceState) {
        return (null != savedInstanceState) && savedInstanceState.containsKey(SHOULD_LOAD_FROM_REPOSITORY_KEY) ?
                savedInstanceState.getBoolean(SHOULD_LOAD_FROM_REPOSITORY_KEY) : true;
    }
}
