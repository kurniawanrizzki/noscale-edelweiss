package com.noscale.edelweiss.dashboard;

import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.data.Module;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public interface DashboardContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        List<Module> getAvailableModules (BaseActivity activity);
    }
}
