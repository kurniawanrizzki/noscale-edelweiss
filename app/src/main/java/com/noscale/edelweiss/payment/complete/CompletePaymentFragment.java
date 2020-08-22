package com.noscale.edelweiss.payment.complete;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.BasePresenter;
import com.noscale.edelweiss.BaseView;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.payment.PaymentActivity;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class CompletePaymentFragment extends BaseFragment implements BaseView {

    public static CompletePaymentFragment newInstance () {
        return new CompletePaymentFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tv_payment_booking_click).setOnClickListener((v) -> {
            openWhatsAppContact("081282395560");

            getActivity().setResult(PaymentActivity.RESULT_OK);
            getActivity().finish();
        });
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_complete_payment;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    private void openWhatsAppContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");

        startActivity(Intent.createChooser(i, ""));
    }
}
