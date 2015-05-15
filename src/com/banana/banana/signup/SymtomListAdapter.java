package com.banana.banana.signup;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SymtomListAdapter extends BaseAdapter{

	ArrayList<SymtomListItemData> items = new ArrayList<SymtomListItemData>();
	Context mContext;
	
	public SymtomListAdapter(Context context) {
		mContext = context;
	}
	
	
	public void remove(String item) {
		int position = searchPosition(item);
		items.remove(position);
		notifyDataSetChanged();
	}
	
	public void add(SymtomListItemData item) { 
		//String s = item.symtom;
		//int position = searchPosition(s);

		if(items.size() < 3) {
			items.add(item);
			notifyDataSetChanged();
		}
	}
	
	public void addAll(List<SymtomListItemData> items) {
		this.items.addAll(items);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SymtomListItemView v;
		if(convertView == null) {
			v = new SymtomListItemView(mContext);
		} else {
			v = (SymtomListItemView)convertView;
		}
		v.setItemData(items.get(position));
				
		return v;
	} 
	
	public int searchPosition(String s) {
		for(int i = 0; i < 3; i++) {
			if(items.get(i).symtom.equals(s)){
				return i;
			} else {
				return -1;
			}
		}
		return -1;
	}
	
	 
}
