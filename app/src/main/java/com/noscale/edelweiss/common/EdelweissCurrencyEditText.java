package com.noscale.edelweiss.common;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 21/08/20.
 */
public class EdelweissCurrencyEditText extends EdelweissEditText {
    private final String IDR = "Rp ";

    public EdelweissCurrencyEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public EdelweissCurrencyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EdelweissCurrencyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {
        addTextChangedListener(getTextWatcher());
        setText("0");
    }

    private TextWatcher getTextWatcher () {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) return;

                removeTextChangedListener(this);

                String s = charSequence.toString();
                Double value = getCurrencyDoubleValue(s);

                NumberFormat f = NumberFormat.getCurrencyInstance();
                f.setMaximumFractionDigits(0);
                f.setCurrency(Currency.getInstance(Locale.getDefault()));

                String result = f.format(value);
                result = result.replace("$", IDR);

                setText(result);
                setSelection(result.length());

                addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        if (null != getText()) {
            int length = 2;

            if ((selStart <= length) && (getText().length() > 1)) {
                try {
                    setSelection(length + 1);
                } catch (IndexOutOfBoundsException e) {
                    setSelection(length);
                    e.printStackTrace();
                }

                return;
            }
        }
        super.onSelectionChanged(selStart, selEnd);
    }

    private Double getCurrencyDoubleValue (String currencyString) {
        try {
            currencyString = currencyString
                    .replaceAll("[^\\d.-]", "");
            return Double.parseDouble(currencyString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return 0D;
    }

    public Double getDoubleValue () {
        return getCurrencyDoubleValue(getText().toString());
    }
}
