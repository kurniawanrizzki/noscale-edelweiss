package com.noscale.edelweiss.schedule;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.itextpdf.text.Paragraph;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.PDFCommon;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tvActionTool = getActivity().findViewById(R.id.tv_action_bar_tool);
        tvActionTool.setText(R.string.export_txt);
        tvActionTool.setVisibility(View.VISIBLE);
        tvActionTool.setOnClickListener((v) -> PDFCommon.createPdf(getContext(), new PDFCommon.PDFContainerListener() {
            @Override
            public Paragraph getContent() {
                Paragraph p1 = new Paragraph(((SchedulePresenter) mPresenter).getScheduleContent());
                p1.setAlignment(Paragraph.ALIGN_LEFT);
                return p1;
            }

            @Override
            public void showError(String message) {
                showErrorMessage(message);
            }
        }));
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
    public void setPresenter(ScheduleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPage(List<Schedule> schedules) {
        showProgressView(false);
        showEmptyView(schedules.isEmpty());

        mAdapter = new SimpleRecyclerAdapter<>(schedules, R.layout.item_schedule, new SimpleRecyclerAdapter.OnViewHolder<Schedule>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Schedule item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_schedule_name);
                TextView tvDate = holder.itemView.findViewById(R.id.tv_schedule_date);
                TextView tvStatus = holder.itemView.findViewById(R.id.tv_schedule_status);
                ImageView ivTools = holder.itemView.findViewById(R.id.iv_schedule_more);

                PopupMenu popup = new PopupMenu(getContext(), ivTools);
                popup.getMenuInflater().inflate(R.menu.schedule_status_menu, popup.getMenu());

                if (AppConfiguration.getInstance(getContext()).getAuthenticatedUserType().equals(User.Type.ADMIN)) {
                    ivTools.setVisibility(View.VISIBLE);
                    popup.setOnMenuItemClickListener(menuItem -> {
                        switch (menuItem.getItemId()) {
                            case R.id.status_canceled : {
                                showProgressView(true);
                                ((ScheduleContract.Presenter) mPresenter).update(item.getId(), Status.CANCELED.name());
                                return false;
                            }
                            default:
                                showProgressView(true);
                                ((ScheduleContract.Presenter) mPresenter).update(item.getId(), Status.PENDING.name());
                                return false;
                        }
                    });
                }

                tvName.setText(item.getName());
                tvDate.setText(item.getDateTime());
                tvStatus.setText(item.getStatus().toString());
                ivTools.setOnClickListener((v) -> popup.show());
            }
        });

        RecyclerView rvSchedule = getView().findViewById(R.id.rv_fragment_list);
        rvSchedule.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            ((ScheduleContract.Presenter) mPresenter).fetch();
        }, (dialogInterface, i) -> getActivity().finish());
    }
}
