package com.noscale.edelweiss.common.widget.typography;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.android.material.textview.MaterialTextView;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 20/08/20.
 */
public abstract class EdelweissTextView extends MaterialTextView {
    public EdelweissTextView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public EdelweissTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EdelweissTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init (AttributeSet attrs) {
        int color = R.color.colorBlack;
        
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.EdelweissTextView);
        int colorStyle = R.styleable.EdelweissTextView_textColor;

        if (ta.hasValue(colorStyle)) {
            color = ta.getResourceId(colorStyle, 0);
        }

        setTextAppearance(getContext(), getAppliedStyle());
        setTextColor(ContextCompat.getColor(getContext(), color));
    }

    protected abstract int getAppliedStyle ();
}
