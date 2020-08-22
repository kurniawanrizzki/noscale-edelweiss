package com.noscale.edelweiss.common;

import android.content.Intent;
import com.noscale.edelweiss.BaseActivity;
import com.noscale.edelweiss.R;
import com.noscale.edelweiss.about.AboutUsActivity;
import com.noscale.edelweiss.booking.BookingActivity;
import com.noscale.edelweiss.common.configuration.AppConfiguration;
import com.noscale.edelweiss.data.Module;
import com.noscale.edelweiss.data.User;
import com.noscale.edelweiss.gallery.GalleryActivity;
import com.noscale.edelweiss.login.LoginActivity;
import com.noscale.edelweiss.payment.PaymentActivity;
import com.noscale.edelweiss.payment.list.PaymentListActivity;
import com.noscale.edelweiss.schedule.ScheduleActivity;
import com.noscale.edelweiss.testimonial.TestimonialActivity;
import com.noscale.edelweiss.wp.WeddingPackageActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 13/08/20.
 */
public class ModuleCommon {

    public static List<Module> getModules (final BaseActivity activity) {
        List<Module> modules = new ArrayList<>();

        User.Type type = AppConfiguration.getInstance(activity).getAuthenticatedUserType();

        modules.add(new Module(R.string.gallery_txt, R.drawable.ic_camera, (v) -> {
            Intent i = new Intent(activity, GalleryActivity.class);
            activity.startActivity(i);
        }));

        modules.add(new Module(R.string.package_txt, R.drawable.ic_ring, (v) -> {
            Intent i = new Intent(activity, WeddingPackageActivity.class);
            activity.startActivity(i);
        }));

        if (type.equals(User.Type.DEFAULT)) {
            modules.add(new Module(R.string.booking_txt, R.drawable.ic_booking, (v) -> {
                Intent i = new Intent(activity, BookingActivity.class);
                activity.startActivityForResult(
                        i,
                        BookingActivity.BOOKING_REQUEST_CODE
                );
            }));
        }

        modules.add(new Module(R.string.schedule_txt, R.drawable.ic_date, (v) -> {
            Intent i = new Intent(activity, ScheduleActivity.class);
            activity.startActivity(i);
        }));

        modules.add(new Module(R.string.testimonial_txt, R.drawable.ic_testimoni, (v) -> {
            Intent i = new Intent(activity, TestimonialActivity.class);
            activity.startActivity(i);
        }));

        modules.add(new Module(R.string.payment_txt, R.drawable.ic_money, (v) -> {
            if (type.equals(User.Type.DEFAULT)) {
                Intent i = new Intent(activity, PaymentActivity.class);
                activity.startActivityForResult(i, PaymentActivity.PAYMENT_CREATION_REQUEST_CODE);
                return;
            }

            Intent i = new Intent(activity, PaymentListActivity.class);
            activity.startActivity(i);
        }));

        modules.add(new Module(R.string.about_us_txt, R.drawable.ic_lamp, (v) -> {
            Intent i = new Intent(activity, AboutUsActivity.class);
            activity.startActivity(i);
        }));

        modules.add(new Module(R.string.sign_out_txt, R.drawable.ic_logout, (v) -> {
            UICommon.showDialog(activity, activity.getString(R.string.warning_title_txt), activity.getString(R.string.sign_out_message_txt), (dialogInterface, i) -> {
                AppConfiguration.getInstance(activity).setAuthenticated(false);
                AppConfiguration.getInstance(activity).setAuthenticatedId(0);
                AppConfiguration.getInstance(activity).setAuthenticatedUserName("");
                AppConfiguration.getInstance(activity).setAuthenticatedUserType(0);

                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
            }, (dialogInterface, i) -> {

            });
        }));

        return modules;
    }

}
