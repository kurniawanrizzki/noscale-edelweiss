package com.noscale.edelweiss.common;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.about.AboutUsActivity;
import com.noscale.edelweiss.data.Module;
import com.noscale.edelweiss.schedule.ScheduleActivity;
import com.noscale.edelweiss.testimonial.TestimonialActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class ModuleCommon {

    public static List<Module> getModules (final Context context) {
        List<Module> modules = new ArrayList<>();

        modules.add(new Module(R.string.gallery_txt, R.drawable.ic_gallery, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        modules.add(new Module(R.string.package_txt, R.drawable.ic_package, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        modules.add(new Module(R.string.booking_txt, R.drawable.ic_book, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        modules.add(new Module(R.string.schedule_txt, R.drawable.ic_schedule, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ScheduleActivity.class);
                context.startActivity(i);
            }
        }));

        modules.add(new Module(R.string.testimonial_txt, R.drawable.ic_testimonial, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, TestimonialActivity.class);
                context.startActivity(i);
            }
        }));

        modules.add(new Module(R.string.payment_txt, R.drawable.ic_payment, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        modules.add(new Module(R.string.about_us_txt, R.drawable.ic_info, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AboutUsActivity.class);
                context.startActivity(i);
            }
        }));

        return modules;
    }

}
