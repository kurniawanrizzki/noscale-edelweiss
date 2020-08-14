package com.noscale.edelweiss.data;

import android.view.View;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class Module {

    private int title;

    private int icon;

    private View.OnClickListener listener;

    public Module (int title, int icon, View.OnClickListener listener) {
        this.title = title;
        this.icon = icon;
        this.listener = listener;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
