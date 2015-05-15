package com.banana.banana.signup;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.banana.banana.MyApplication;

public class SymtomAdapter extends BaseAdapter{
	ArrayList<SyndromeList> items =new ArrayList<SyndromeList>();
		Context mcontext;
		SymtomItemView itemView;
		int count;
		boolean exist,save;
		 
		public SymtomAdapter(Context mcontext) {
			
			this.mcontext = mcontext;
			itemView=new SymtomItemView(mcontext);
			
		}

		public void add(SyndromeList item)
		{
			String str=null;
			if(items.size()==0){
				items.add(item);
				notifyDataSetChanged();
			}
			else if(items.size()>0&&items.size()<3){
				for(int i=0;i<=items.size()-1;i++){
					str=((SyndromeList)(items.get(i))).syndrome_name;
					if(str.equals(item.syndrome_name)){
						//같으면
						exist=true;
						save=true;
					}else{
						//다르면
						exist=false;
					}
				}
				
				
				if(exist==false&&save==false){
					items.add(item);
					save=false;
					notifyDataSetChanged();
				}else{
					Toast.makeText(MyApplication.getContext(), "같은게 있다", Toast.LENGTH_SHORT).show();
					exist=false;
					save=false;
				}
			}
		}
		 
		public void addAll(List<SyndromeList> items)
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
			SymtomItemView v=new SymtomItemView(mcontext);
			v.setItemData(items.get(position));
			return v;
		}
		
		public void clear()
		{
			items.clear();
		}
			public void remove(SyndromeList item)
			{
				String str=null;
				int position = 0;
				for(int i=0;i<=items.size()-1;i++){
					str=((SyndromeList)(items.get(i))).syndrome_name;
					if(str.equals(item.syndrome_name)){
						//같으면
						exist=true;
						save=true;
						position=i;
					}else{
						//다르면
						exist=false;
					}
				}
				
				
				if(exist==false&&save==false){
					//items.remove(position);
					save=false;
					notifyDataSetChanged();
				}else{
					Toast.makeText(MyApplication.getContext(), "같은게 있다", Toast.LENGTH_SHORT).show();
					items.remove(position);
					notifyDataSetChanged();
					exist=false;
					save=false;
				}
			}

}
