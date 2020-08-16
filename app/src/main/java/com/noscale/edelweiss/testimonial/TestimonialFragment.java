package com.noscale.edelweiss.testimonial;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Testimonial;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class TestimonialFragment extends BaseFragment implements TestimonialContract.View {

    private SimpleRecyclerAdapter<Testimonial> mAdapter;

    public static TestimonialFragment newInstance () {
        return new TestimonialFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainView = view.findViewById(R.id.rv_fragment_list);
        mEmptyView = view.findViewById(R.id.inc_fragment_empty);

        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);
        tvTitle.setText(getString(R.string.testimonial_txt));

        showProgressView(true);
    }

    @Override
    protected int getResLayout() {
        return R.layout.widget_fragment_with_title;
    }

    @Override
    public void setPresenter(TestimonialContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPage(List<Testimonial> testimonials) {
        showProgressView(false);

        mAdapter = new SimpleRecyclerAdapter<>(testimonials, R.layout.item_testimonial, new SimpleRecyclerAdapter.OnViewHolder<Testimonial>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Testimonial item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_wp_detail);
                TextView tvContent = holder.itemView.findViewById(R.id.tv_wp_name);

                tvName.setText(item.getName());
                tvContent.setText(item.getContent());
            }
        });

        RecyclerView rvTestimonial = getView().findViewById(R.id.rv_fragment_list);
        rvTestimonial.setAdapter(mAdapter);
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
