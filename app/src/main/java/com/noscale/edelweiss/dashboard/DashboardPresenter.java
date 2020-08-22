package com.noscale.edelweiss.dashboard;

import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.common.ModuleCommon;
import com.noscale.edelweiss.data.Module;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View mView;

    public DashboardPresenter (DashboardContract.View view) {
        view.setPresenter(this);

        this.mView = view;
    }

    @Override
    public void start() {
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public List<Module> getAvailableModules(BaseActivity activity) {
        return ModuleCommon.getModules(activity);
    }
}
