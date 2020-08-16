package com.noscale.edelweiss.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.payment.complete.CompletePaymentActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentFragment extends BaseFragment implements PaymentContract.View {

    public static PaymentFragment newInstance () {
        return new PaymentFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.b_payment_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CompletePaymentActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    public void setPresenter(PaymentContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
