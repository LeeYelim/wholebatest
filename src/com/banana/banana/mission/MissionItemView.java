package com.banana.banana.mission;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.banana.banana.R;
import com.banana.banana.mission.callmissionlist.Item;

public class MissionItemView extends FrameLayout {

	public MissionItemView(Context context) {
		super(context);
		init();
	}

	public MissionItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	ImageView iconView;
	TextView titleView, stateView, dateView, hintView;
	Item mData;
	View detailView;
	View view;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.mission_itemdata, this);
		iconView = (ImageView)findViewById(R.id.img_mission1);
		detailView=(View)findViewById(R.id.detailView);
		view=(View)findViewById(R.id.RelativeLayout1);
		stateView = (TextView)findViewById(R.id.text_state);	 
		titleView = (TextView)findViewById(R.id.text_missionName);
		dateView = (TextView)findViewById(R.id.text_date);
		hintView = (TextView)findViewById(R.id.textView4);
	}
	
	public boolean isVisibleDetailView() {
		return detailView.getVisibility() == View.VISIBLE;
	}
	
	public void setVisibileDetailView(boolean isVisible) {
		detailView.setVisibility(isVisible?View.VISIBLE:View.GONE);
	}
	
	public void setItemData(Item data) {
		mData = data;
		if(data.gender.equals("m") && (data.state == 0 || data.state == 1 || data.state == 2)) {
			iconView.setImageResource(R.drawable.mission_contents_man_icon);
			if(data.state == 0) { 
				stateView.setText("Fail");
			} else if(data.state == 1) { 
				stateView.setText("Success");
			} else if(data.state == 2) { 
				stateView.setText("Pass");
			} 
		} else if(data.gender.equals("m") && data.state == 3) {
			iconView.setImageResource(R.drawable.mission_contents_man_ing_icon);
			stateView.setBackgroundResource(R.drawable.mission_contents_yellow_icon);
		} else if(data.gender.equals("f") && (data.state == 0 || data.state == 1 || data.state == 2)){
			iconView.setImageResource(R.drawable.mission_contents_woman_icon);
			if(data.state == 0) { 
				stateView.setText("Fail");
			} else if(data.state == 1) { 
				stateView.setText("Success");
			} else if(data.state == 2) { 
				stateView.setText("Pass");
			} 
		} else if(data.gender.equals("f") && data.state == 3) {
			iconView.setImageResource(R.drawable.mission_contents_woman_ing_icon); 
			stateView.setBackgroundResource(R.drawable.mission_contents_yellow_icon);
		}
		titleView.setText(data.theme);
		dateView.setText(data.date);
		hintView.setText(data.hint);
		//iconView.setImageResource(data.iconId);
		//titleView.setText(data.Missiontitle);
	}
	
	/*public String getTitle() {
		//return mData.Missiontitle;
	}*/

}
