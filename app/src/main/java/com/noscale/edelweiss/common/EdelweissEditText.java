package com.noscale.edelweiss.common;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import com.noscale.edelweiss.R;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 20/08/20.
 */
public class EdelweissEditText extends AppCompatEditText {
    public EdelweissEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public EdelweissEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EdelweissEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        int y = (int) getResources().getDimension(R.dimen._4sdp);
        UICommon.applyMarginToView(this, y);
    }

    private void init () {
        setTextAppearance(getContext(), R.style.AppTheme_EditText);
    }
}
