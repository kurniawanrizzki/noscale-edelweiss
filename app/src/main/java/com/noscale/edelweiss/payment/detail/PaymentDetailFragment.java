package com.noscale.edelweiss.payment.detail;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Payment;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentDetailFragment extends BaseFragment implements PaymentDetailContract.View {

    public static PaymentDetailFragment newInstance (List<Payment> data, String bookingNumber) {
        PaymentDetailFragment fragment = new PaymentDetailFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(PaymentDetailActivity.PAYMENT_LIST_ARG, (ArrayList<? extends Parcelable>) data);
        args.putString(PaymentDetailActivity.PAYMENT_BOOKING_NUMBER_ARG, bookingNumber);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Payment> data = new ArrayList<>();

        String bookingNumber = "-";

        if (getArguments().containsKey(PaymentDetailActivity.PAYMENT_LIST_ARG)) {
            data = getArguments().getParcelableArrayList(PaymentDetailActivity.PAYMENT_LIST_ARG);
        }

        if (getArguments().containsKey(PaymentDetailActivity.PAYMENT_BOOKING_NUMBER_ARG)) {
            bookingNumber = getArguments().getString(PaymentDetailActivity.PAYMENT_BOOKING_NUMBER_ARG);
        }

        if (data.isEmpty()) {
            showEmptyView(true);
            return;
        }

        String finalBookingNumber = bookingNumber;
        SimpleRecyclerAdapter<Payment> adapter = new SimpleRecyclerAdapter<>(
                data,
                R.layout.item_payment,
                (holder, item) -> {
                    View v = holder.itemView;

                    AppCompatImageView ivImage = v.findViewById(R.id.iv_payment_image);
                    TextView tvTitle = v.findViewById(R.id.tv_payment_booking_number);
                    TextView tvType = v.findViewById(R.id.tv_payment_type);
                    TextView tvDate = v.findViewById(R.id.tv_payment_date);
                    TextView tvAmount = v.findViewById(R.id.tv_payment_booking_amount);

                    tvTitle.setText(finalBookingNumber);
                    tvType.setText(item.getPaymentTypeName());
                    tvDate.setText(item.getPaymentDate());
                    tvAmount.setText(String.valueOf(item.getPaymentTotal()));

                    File f = new File(item.getPaymentReceipt());
                    if (!f.exists()) return;

                    Picasso.get().load(f).into(ivImage);
                }
        );

        RecyclerView rvList = view.findViewById(R.id.rv_fragment_list);
        rvList.setAdapter(adapter);

        showProgressView(false);
    }

    @Override
    protected int getResLayout() {
        return R.layout.layout_fragment_list;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return false;
    }

    @Override
    public void setPresenter(PaymentDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
