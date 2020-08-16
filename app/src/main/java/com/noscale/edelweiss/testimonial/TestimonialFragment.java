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
import java.util.ArrayList;

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

        mAdapter = new SimpleRecyclerAdapter<>(new ArrayList<Testimonial>(), R.layout.item_testimonial, new SimpleRecyclerAdapter.OnViewHolder<Testimonial>() {
            @Override
            public void onBindView(SimpleRecyclerAdapter.SimpleViewHolder holder, Testimonial item) {
                TextView tvName = holder.itemView.findViewById(R.id.tv_wp_detail);
                TextView tvContent = holder.itemView.findViewById(R.id.tv_wp_name);

                tvName.setText(item.getName());
                tvContent.setText(item.getContent());
            }
        });

        RecyclerView rvTestimonial = view.findViewById(R.id.rv_fragment_list);
        TextView tvTitle = view.findViewById(R.id.tv_fragment_title);

        rvTestimonial.setAdapter(mAdapter);
        tvTitle.setText(getString(R.string.testimonial_txt));
    }

    @Override
    protected int getResLayout() {
        return R.layout.widget_fragment_with_title;
    }

    @Override
    public void setPresenter(TestimonialContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
