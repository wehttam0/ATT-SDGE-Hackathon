package com.cognition;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class profile_frag extends Fragment {
	TextView textView;
	View dbView;
	Switch onOffSwitch;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		dbView = inflater.inflate(R.layout.profilefrag, container, false);
		textView = (TextView) dbView.findViewById(R.id.status);
		textView.setText("Calendar Sync is Off");
		
        // listener handler
        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.enable:
                        showNotification();
                        break;
                }
            }
        };

        // we will set the listeners
        dbView.findViewById(R.id.enable).setOnClickListener(handler);
		
		onOffSwitch = (Switch)  dbView.findViewById(R.id.enable); 
		onOffSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if(isChecked){
				textView.setText("Calendar Sync is On");
			}else{
				textView.setText("Calendar Sync is Off");
			}
		  }       
		});
		
		return dbView;
	}
	
    @SuppressLint("NewApi")
	public void showNotification(){

        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // this is it, we'll build the notification!
        // in the addAction method, if you don't want any icon, just set the first param to 0
        Notification mNotification = new Notification.BigTextStyle(
        		new Notification.Builder(getActivity())

            .setContentTitle("New Post!")
            .setContentText("Notice! Your calendar states you will be")
            //+"\n"+ "out of the house for several days...." + "Con-EN will automatically" +"\n"+ "set your vacation energy profile")
            .setSound(soundUri)
            .setSmallIcon(R.drawable.blacklogo))
        	.bigText("Notice! Your calendar states you will be" +"\n"+ "out of the house for several days...." +
            		"Con-EN will automatically" +"\n"+ "set your vacation energy profile")
            .build();

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(android.content.Context.NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
    }
}