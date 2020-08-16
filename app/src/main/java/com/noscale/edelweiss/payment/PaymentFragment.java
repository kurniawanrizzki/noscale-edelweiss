package com.noscale.edelweiss.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.data.source.PaymentType;
import com.noscale.edelweiss.payment.complete.CompletePaymentActivity;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 16/08/20.
 */
public class PaymentFragment extends BaseFragment implements PaymentContract.View {

    private ArrayAdapter<PaymentType> mAdapter;

    public static PaymentFragment newInstance () {
        return new PaymentFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.cl_payment_container);

        view.findViewById(R.id.b_payment_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CompletePaymentActivity.class);
                startActivity(i);
            }
        });

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    public void setPresenter(PaymentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void appendData(List<PaymentType> types) {
        showProgressView(false);

        mAdapter = new ArrayAdapter<PaymentType>(getContext(), android.R.layout.simple_spinner_dropdown_item, types) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                PaymentType type = types.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getPaymentName());

                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                PaymentType type = types.get(position);

                TextView tvTitle = view.findViewById(android.R.id.text1);
                tvTitle.setText(type.getPaymentName());

                return view;
            }
        };

        AppCompatSpinner spinner = getView().findViewById(R.id.sp_payment_options);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PaymentType type = types.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message);
    }
}
