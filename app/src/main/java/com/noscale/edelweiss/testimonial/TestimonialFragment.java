package com.noscale.edelweiss.testimonial;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.noscale.edelweiss.BaseFragment;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.common.widget.SimpleRecyclerAdapter;
import com.noscale.edelweiss.data.Testimonial;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.testimonial.creation.TestimonialCreateActivity;
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
    protected int getResLayout() {
        return R.layout.layout_fragment_list;
    }

    @Override
    public void setPresenter(TestimonialContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void goToTestimonialCreation() {
        Intent i = new Intent(getContext(), TestimonialCreateActivity.class);
        startActivity(i);
    }

    @Override
    protected View.OnClickListener getFabClickedListener() {
        return (v) -> goToTestimonialCreation();
    }

    @Override
    public void showPage(List<Testimonial> testimonials) {
        showProgressView(false);
        showEmptyView(testimonials.isEmpty());

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
    public void showErrorMessage(String message) {
        showMessage(getString(R.string.error_title_txt), message, (dialogInterface, i) -> {
            showProgressView(true);
            ((TestimonialContract.Presenter) mPresenter).fetch();
        }, (dialogInterface, i) -> getActivity().finish());
    }

    @Override
    protected boolean isAccessTypeAccepted() {
        User.Type type = AppConfiguration.getInstance(getContext()).getAuthenticatedUserType();
        return type == User.Type.DEFAULT;
    }
}
