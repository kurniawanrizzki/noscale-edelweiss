package com.noscale.edelweiss.payment.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Booking;
import com.noscale.edelweiss.data.PaymentHistory;
import com.noscale.edelweiss.data.User;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 22/08/20.
 */
public class PaymentListFragment extends BaseFragment implements PaymentListContract.View {

    public static PaymentListFragment newInstance () {
        return new PaymentListFragment();
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
    public void append(List<PaymentHistory> histories) {
        showProgressView(false);
        showEmptyView(histories.isEmpty());

        SimpleRecyclerAdapter<PaymentHistory> historyAdapter = new SimpleRecyclerAdapter<>(
                histories,
                R.layout.item_payment_history,
                (holder, item) -> {
                    View v = holder.itemView;

                    TextView tvName = v.findViewById(R.id.tv_history_name);
                    TextView tvEmail = v.findViewById(R.id.tv_history_email);
                    ImageView ivArrow = v.findViewById(R.id.iv_history_arrow);
                    RecyclerView rvList = v.findViewById(R.id.rv_history_booking);

                    tvName.setText(item.getFirstName()+" "+item.getLastName());
                    tvEmail.setText(item.getEmail());

                    ivArrow.setOnClickListener((view) -> {
                        ImageView arrow = (ImageView) view;

                        if (rvList.getVisibility() == View.GONE) {
                            arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_up));
                            rvList.setVisibility(View.VISIBLE);
                            return;
                        }

                        arrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_down));
                        rvList.setVisibility(View.GONE);
                    });

                    SimpleRecyclerAdapter<Booking> bookingAdapter = new SimpleRecyclerAdapter<>(
                            item.getBookingList(),
                            R.layout.item_booking,
                            (h,i) -> {
                                TextView tvNumber = h.itemView.findViewById(R.id.tv_booking_number);
                                TextView tvDate = h.itemView.findViewById(R.id.tv_booking_date);
                                TextView tvCategory = h.itemView.findViewById(R.id.tv_booking_category);
                                TextView tvTotal = h.itemView.findViewById(R.id.tv_booking_total);
                                TextView tvAddress = h.itemView.findViewById(R.id.tv_booking_address);

                                tvNumber.setText(i.getBookingNumber());
                                tvDate.setText(i.getDateTime());
                                tvCategory.setText(i.getCategoryName());
                                tvTotal.setText(i.getWeddingPackage());
                                tvAddress.setText(i.getAddress());
                            }
                    );

                    rvList.setAdapter(bookingAdapter);
                }
        );

        RecyclerView rvList = getView().findViewById(R.id.rv_fragment_list);
        rvList.setAdapter(historyAdapter);
    }

    @Override
    public void showErrorMessage(String message, Runnable runnable) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            runnable.run();
        }, (dialogInterface, i) -> getActivity().finish());
    }

    @Override
    public void setPresenter(PaymentListContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
