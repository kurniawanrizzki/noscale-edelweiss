package com.noscale.edelweiss.common.widget.typography;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 20/08/20.
 */
public class EdelweissHeadline6TextView extends EdelweissTextView {
    public EdelweissHeadline6TextView(@NonNull Context context) {
        super(context);
    }

    public EdelweissHeadline6TextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EdelweissHeadline6TextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getAppliedStyle() {
        return R.style.AppTheme_TextAppearance_Headline6;
    }
}
