package com.banana.banana.mission;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.banana.banana.mission.callmissionlist.Item;

public class MissionAdapter extends BaseAdapter{
	ArrayList<Item> items =new ArrayList<Item>();
		Context mcontext;
		MissionItemView itemView;
		public MissionAdapter(Context mcontext) {
			
			this.mcontext = mcontext;
			itemView=new MissionItemView(mcontext);
		}

		public void add(Item item)
		{
			
			items.add(item);
			notifyDataSetChanged();
		}
		public void addAll(List<Item> items)
		{
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
			MissionItemView v=new MissionItemView(mcontext);
			v.setItemData(items.get(position));
			return v;
		}

	

}
