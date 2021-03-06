package com.noscale.edelweiss.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Module;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    private SimpleRecyclerAdapter<Module> mAdapter;

    public static DashboardFragment newInstance () {
        return new DashboardFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Module> availableModules = ((DashboardContract.Presenter) mPresenter).getAvailableModules((BaseActivity) getActivity());

        mAdapter = new SimpleRecyclerAdapter<>(availableModules, R.layout.item_module, (holder, item) -> {
            AppCompatImageView ivIcon = holder.itemView.findViewById(R.id.iv_module_icon);
            TextView tvTitle = holder.itemView.findViewById(R.id.tv_module_title);

            ivIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), item.getIcon()));
            tvTitle.setText(item.getTitle());

            holder.itemView.setOnClickListener(item.getListener());
        });

        RecyclerView rvModule = view.findViewById(R.id.rv_dashboard_modules);
        rvModule.setAdapter(mAdapter);
    }

    @Override
    protected int getResLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        return true;
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
