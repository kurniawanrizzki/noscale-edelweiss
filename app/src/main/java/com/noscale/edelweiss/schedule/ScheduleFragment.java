package com.noscale.edelweiss.schedule;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Schedule;
import com.noscale.edelweiss.data.User;

import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class ScheduleFragment extends BaseFragment implements ScheduleContract.View {

    private SimpleRecyclerAdapter<Schedule> mAdapter;

    public static ScheduleFragment newInstance () {
        return new ScheduleFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.rv_fragment_list);
        mEmptyView = view.findViewById(R.id.inc_fragment_empty);

        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);
        tvTitle.setText(getString(R.string.schedule_title_txt));

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.widget_fragment_with_title;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return false;
    }

    @Override
    public void setPresenter(ScheduleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPage(List<Schedule> schedules) {
        showProgressView(false);

        mAdapter = new SimpleRecyclerAdapter<>(schedules, R.layout.item_schedule, new SimpleRecyclerAdapter.OnViewHolder<Schedule>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Schedule item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_schedule_name);
                TextView tvDate = holder.itemView.findViewById(R.id.tv_schedule_date);
                TextView tvStatus = holder.itemView.findViewById(R.id.tv_schedule_status);

                tvName.setText(item.getName());
                tvDate.setText(item.getDateTime());
                tvStatus.setText(item.getStatus().toString());
            }
        });

        RecyclerView rvSchedule = getView().findViewById(R.id.rv_fragment_list);
        rvSchedule.setAdapter(mAdapter);
    }

    @Override
    public void showEmptyPage() {
        showProgressView(false);

        mMainView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        showEmptyPage();
        showMessage(getString(R.string.error_title_txt), message);
    }
}
