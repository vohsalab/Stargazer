package com.firstsputnik.stargazer.View;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.firstsputnik.stargazer.R;
import com.firstsputnik.stargazer.Receiver.OneTimeAlarmReceiver;

import java.util.Calendar;
import java.util.Date;


public class DetailsDialogFragment extends DialogFragment {

    private long datetime;
    private Calendar notificationTime;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        datetime = arguments.getLong("Date");
        Date meetDate = new Date(datetime);
        notificationTime = Calendar.getInstance();
        notificationTime.setTimeInMillis(datetime - 900000);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_details_dialog, null);
        TextView tv  = (TextView) v.findViewById(R.id.date_view);
        tv.setText(meetDate.toString());
        final CheckBox cb = (CheckBox) v.findViewById(R.id.alarm_checkbox);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cb.isChecked()) {
                            setupAlarm(datetime);
                        }
                        else setupNotification(datetime);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DetailsDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    private void setupNotification(long datetime) {
        Toast.makeText(getActivity(), "Setting up Notification for " +
                notificationTime.get(Calendar.HOUR) + ":" + notificationTime.get(Calendar.MINUTE), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity().getApplicationContext(), OneTimeAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(getActivity(), 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pi);
        Log.d("NotificationSetup", "Alarm is set up");
    }

    private void setupAlarm(long datetime) {
        Toast.makeText(getActivity(),
                "Setting up alarm for " + notificationTime.get(Calendar.HOUR) + ":" + notificationTime.get(Calendar.MINUTE), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, notificationTime.get(Calendar.HOUR));
        i.putExtra(AlarmClock.EXTRA_MINUTES, notificationTime.get(Calendar.MINUTE));
        startActivity(i);
    }
}
