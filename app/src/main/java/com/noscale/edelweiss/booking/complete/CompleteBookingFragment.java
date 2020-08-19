package com.noscale.edelweiss.booking.complete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.dashboard.DashboardActivity;
import com.noscale.edelweiss.payment.PaymentActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompleteBookingFragment extends BaseFragment implements BaseView {

    public static CompleteBookingFragment newInstance () {
        return new CompleteBookingFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvPayment = view.findViewById(R.id.tv_booking_go_to_payment);
        Button bLater = view.findViewById(R.id.b_booking_later);

        tvPayment.setOnClickListener((v) -> {
            Intent i = new Intent(getContext(), PaymentActivity.class);
            startActivity(i);
        });

        bLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), DashboardActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_complete_booking;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }
}
