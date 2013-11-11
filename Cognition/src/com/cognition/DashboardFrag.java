package com.cognition;

import org.codeandmagic.android.gauge.GaugeView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DashboardFrag extends Fragment {

	private GaugeView mGaugeView1;
	private GaugeView mGaugeView2;

	View dbView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		dbView = inflater.inflate(R.layout.gaugefrag, container, false);
		mGaugeView1 = (GaugeView) dbView.findViewById(R.id.gauge_view1);
		//mGaugeView2 = (GaugeView) dbView.findViewById(R.id.gauge_view2);
		
		System.out.println("Hi");

		 final Button button1 = (Button) dbView.findViewById(R.id.btnButton1);
		 button1.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 mGaugeView1.setTargetValue(20);
             }
         });
         
         final Button button2 = (Button) dbView.findViewById(R.id.btnButton2);
         button2.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 mGaugeView1.setTargetValue(60);
             }
         });
         
         final Button button3 = (Button) dbView.findViewById(R.id.btnButton3);
         button3.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 mGaugeView1.setTargetValue(90);
             }
         });
		
		
		
//		XmlParser xParser = new XmlParser();
//		try {
//			xParser.proceesDocument();
//			float total = xParser.calcTotalUsageByDay(((IntervalBlock)xParser.getMp().get(1)));
//			// Set gauge values
//			mGaugeView1.setTargetValue(total/5f);
//		} catch (XMLStreamException e) {
//			e.printStackTrace();
//		}

		return dbView;
	}

}
