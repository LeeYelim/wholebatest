package com.banana.banana.signup;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.banana.banana.R;

public class SymtomItemView extends FrameLayout {
TextView Symtom,before,after;
	public SymtomItemView(Context context) {
		super(context);
		init();
	}

	public SymtomItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	ImageView iconView;
	TextView titleView;
	SyndromeList mData;
	View detailView;
	
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.symtom_layout, this);
		Symtom=(TextView)findViewById(R.id.textView4);
		before=(TextView)findViewById(R.id.textView1);
		after=(TextView)findViewById(R.id.textView3);
		
		iconView = (ImageView)findViewById(R.id.img_mission1);
		detailView=(View)findViewById(R.id.detailView);
		
			
		
		titleView = (TextView)findViewById(R.id.textView1);
	}
	
	public boolean isVisibleDetailView() {
		return detailView.getVisibility() == View.VISIBLE;
	}
	
	public void setVisibileDetailView(boolean isVisible) {
		detailView.setVisibility(isVisible?View.VISIBLE:View.GONE);
	}
	
	public void setItemData(SyndromeList data) {
		mData = data;
		Symtom.setText(mData.syndrome_name);
		before.setText(mData.syndrome_before);
		after.setText(mData.syndrome_after);
		//iconView.setImageResource(data.iconId);
		//titleView.setText(data.Missiontitle);
	}
	
	/*public String getTitle() {
		return mData;
	}*/

}
