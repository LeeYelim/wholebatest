package com.banana.banana.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.banana.banana.R;

public class SymtomListItemView extends FrameLayout{

	TextView SymtomView;
	EditText beforeView;
	EditText afterView;
	
	public SymtomListItemView(Context context) {
		super(context);
		init();
	
	}

	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater.from(getContext()).inflate(R.layout.symtom_list_item, this);
		SymtomView = (TextView)findViewById(R.id.SymtomView);
		beforeView = (EditText)findViewById(R.id.EditBefore);
		afterView = (EditText)findViewById(R.id.Editafter);
		
	}
	
	SymtomListItemData mData;
	public void setItemData(SymtomListItemData data) {
		mData = data;
		SymtomView.setText(data.symtom);
		beforeView.setText(""+data.days_before);
		afterView.setText(""+data.days_after);
	}

}
