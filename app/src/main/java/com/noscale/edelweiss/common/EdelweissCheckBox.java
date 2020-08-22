package com.noscale.edelweiss.common;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 21/08/20.
 */
public class EdelweissCheckBox extends AppCompatCheckBox {
    public EdelweissCheckBox(@NonNull Context context) {
        super(context);
        init();
    }

    public EdelweissCheckBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EdelweissCheckBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {
        setTextAppearance(getContext(), R.style.AppTheme_CheckBox);
    }
}
