package com.noscale.edelweiss.common.widget.typography;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textview.MaterialTextView;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 20/08/20.
 */
public abstract class EdelweissTextView extends MaterialTextView {
    public EdelweissTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public EdelweissTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EdelweissTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {
        setTextAppearance(getContext(), getAppliedStyle());
    }

    protected abstract int getAppliedStyle ();
}
