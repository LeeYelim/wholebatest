package com.banana.banana.signup;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ListView;
=======
>>>>>>> yelim
import android.widget.TextView;

import com.banana.banana.R;

public class SymtomInfoActivity extends ActionBarActivity {
<<<<<<< HEAD

	Button btn_before, btn_next;
	TextView tv1,tv2,tv3,tv4, tv5;
	ListView SymtomListView;
	SymtomAdapter mAdapter;
	SyndromeList data;
	int ListCount=0;
	boolean a,b,c,d,e;  
=======
 
	Button btn_before, btn_next;
	ListLinearLayout symtomListView;
	//SymtomAdapter mAdapter;
	int[] textViewList = { 
			R.id.textSymtom1, 
			R.id.textSymtom2, 
			R.id.textSymtom3,
			R.id.textSymtom4, 
			R.id.textSymtom5, 
			R.id.textSymtom6,
			R.id.textSymtom7, 
			R.id.textSymtom8, 
			R.id.textSymtom9,
			R.id.textSymtom10, 
			R.id.textSymtom11, 
			R.id.textSymtom12,
			R.id.textSymtom13,
			R.id.textSymtom14, 
			R.id.textSymtom15, 
			R.id.textSymtom16,
			R.id.textSymtom17 
			};
	SyndromeList[] dataList = SyndromeList.SYNDROME_DATA;
	View.OnClickListener textViewClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int index = (Integer)v.getTag();
			symtomListView.set(dataList[index]);
		}
	};
>>>>>>> yelim
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_symtom_info);
<<<<<<< HEAD
		SymtomListView=(ListView)findViewById(R.id.list_Symtom);
		a=true;
		b=true;
		c=true;
		d=true;
		mAdapter=new SymtomAdapter(this);
		
		SymtomListView.setAdapter(mAdapter);
		
		
		tv1=(TextView)findViewById(R.id.textSymtom1);
		tv1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 data=new SyndromeList();
			 
			data.syndrome_name=tv1.getText().toString();
			data.syndrome_before=tv1.getText().toString()+"전";
			data.syndrome_after=tv1.getText().toString()+"후";
			
			if(a){ 
				mAdapter.add(data);
				a=false;
			}
			else{
				mAdapter.remove(data);
				a=true;	
			}				
			}
	});	
	
		tv2=(TextView)findViewById(R.id.textSymtom2);
		tv2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			SyndromeList data=new SyndromeList(); 
			data.syndrome_name=tv2.getText().toString();
			data.syndrome_before=tv2.getText().toString()+"전";
			data.syndrome_after=tv2.getText().toString()+"후";
		
			
			if(b){ 
				mAdapter.add(data);
				b=false;
			}
			else{
				mAdapter.remove(data);
				b=true;	
			} 	
		}
	});	
		tv3=(TextView)findViewById(R.id.textSymtom3);
		tv3.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				data=new SyndromeList(); 
				data.syndrome_name=tv3.getText().toString();
				data.syndrome_before=tv3.getText().toString()+"전";
				data.syndrome_after=tv3.getText().toString()+"후"; 
				if(c){
					
					mAdapter.add(data);
					c=false;
					}
				else{
					mAdapter.remove(data);
					c=true; 
				} 
				}
		
	
			});	
		tv4=(TextView)findViewById(R.id.textSymtom4);
		tv4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 data=new SyndromeList(); 
				
				data.syndrome_name=tv4.getText().toString();
				data.syndrome_before=tv4.getText().toString()+"전";
				data.syndrome_after=tv4.getText().toString()+"후";
			
				if(d){
					
					mAdapter.add(data);
					d=false;
					}
				else{
					mAdapter.remove(data);
					d=true;
					
				} 
				
					
				}
		});	
	
			tv5=(TextView)findViewById(R.id.textSymtom5);
			tv5.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 data=new SyndromeList();
					  
					data.syndrome_name=tv5.getText().toString();
					data.syndrome_before=tv5.getText().toString()+"전";
					data.syndrome_after=tv5.getText().toString()+"후";
				 
					if(e){ 
						mAdapter.add(data);
						e=false;
						}
					else{
						mAdapter.remove(data);
						e=true; 
					}  
					}
			});	
=======
		for (int i = 0; i < textViewList.length; i++) {
			TextView tv = (TextView)findViewById(textViewList[i]);
			tv.setTag((Integer)i);
			tv.setOnClickListener(textViewClickListener);
		}
		symtomListView = (ListLinearLayout)findViewById(R.id.list_Symtom);
>>>>>>> yelim
			
			btn_before = (Button)findViewById(R.id.btn_before); 
			btn_before.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			btn_next = (Button)findViewById(R.id.btn_next);
			btn_next.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) { 
<<<<<<< HEAD
					ArrayList<SyndromeList> items = mAdapter.items;
=======
//					ArrayList<SyndromeList> items = mAdapter.items;
>>>>>>> yelim
					/*Intent i = getIntent();
					Bundle bundle = i.getExtras();   
					Intent intent = new Intent(SymtomInfoActivity.this, UseInfoActivity.class);
					intent.putExtra("items", items);
					intent.putExtra("bundle", bundle);
					startActivity(intent);*/
<<<<<<< HEAD
					
					Bundle bundle = getIntent().getExtras();
					WomanInfoParcelData wdata = bundle.getParcelable("wdata");
					wdata.syndromes = items;
=======
					Bundle bundle = getIntent().getExtras();
					WomanInfoParcelData wdata = bundle.getParcelable("wdata"); 	
					wdata.syndromes = symtomListView.getSyndromeList();
					
					 
					
					
					/*for(int i=0; i<=items.size(); i++) {	
						 
						if(!((SyndromeList)SymtomListView.getItemAtPosition(i)).syndrome_before.equals("") 
								&& !((SyndromeList)SymtomListView.getItemAtPosition(i)).syndrome_after.equals("")) { 
							wdata.syndromes.add(((SyndromeList)SymtomListView.getItemAtPosition(i)));
						}
					}*/
						
					
					//wdata.syndromes = items;
>>>>>>> yelim
					Intent intent = new Intent(SymtomInfoActivity.this, UseInfoActivity.class);
					intent.putExtra("wdata", wdata);
					startActivity(intent);
				}
			});	
			
			
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.symtom_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
