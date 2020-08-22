package com.noscale.edelweiss.common;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 21/08/20.
 */
public class EdelweissSpinner extends AppCompatSpinner {
    public EdelweissSpinner(@NonNull Context context) {
        super(context);
    }

    public EdelweissSpinner(@NonNull Context context, int mode) {
        super(context, mode);
    }

    public EdelweissSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        int y = (int) getResources().getDimension(R.dimen._4sdp);
        UICommon.applyMarginToView(this, y);
    }
}
