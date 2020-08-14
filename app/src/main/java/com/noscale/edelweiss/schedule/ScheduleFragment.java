package com.noscale.edelweiss.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.UICommon;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Schedule;

import java.util.ArrayList;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class ScheduleFragment extends BaseFragment implements ScheduleContract.View {

    private SimpleRecyclerAdapter<Schedule> mAdapter;

    public static ScheduleFragment newInstance () {
        return new ScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_schedule,
                container,
                false
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new SimpleRecyclerAdapter<>(new ArrayList<Schedule>(), R.layout.item_schedule, new SimpleRecyclerAdapter.OnViewHolder<Schedule>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Schedule item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_schedule_name);
                TextView tvDate = holder.itemView.findViewById(R.id.tv_schedule_date);
                TextView tvStatus = holder.itemView.findViewById(R.id.tv_schedule_status);

                tvName.setText(item.getName());
                tvDate.setText(item.getDate());
                tvStatus.setText(item.getStatus().toString());
            }
        });

        RecyclerView rvSchedule = view.findViewById(R.id.rv_schedule_list);
        rvSchedule.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(ScheduleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressView(boolean isShow) {
        UICommon.showProgressView(mMainView, mProgressView, isShow);
    }
}
