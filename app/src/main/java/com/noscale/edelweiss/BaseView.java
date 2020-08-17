package com.noscale.edelweiss;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter (T presenter);
}
