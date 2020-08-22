package com.noscale.edelweiss.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 20/08/20.
 */
public class EdelweissButton extends AppCompatButton {
    public EdelweissButton(@NonNull Context context) {
        super(context);
        init();
    }

    public EdelweissButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EdelweissButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        int y = (int) getResources().getDimension(R.dimen._5sdp);
        UICommon.applyMarginToView(this, y);
    }

    private void init () {
        setTextAppearance(getContext(), R.style.AppTheme_Button);
        setBackgroundResource(R.drawable.edelweiss_button_background_ripple);
        setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
    }
}
