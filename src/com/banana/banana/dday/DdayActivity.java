package com.banana.banana.dday;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.banana.banana.R;
import com.banana.banana.love.NetworkManager;
import com.banana.banana.love.NetworkManager.OnResultListener;
import com.banana.banana.mission.MissionItemData;
public class DdayActivity extends ActionBarActivity {

	ListView ddayList;
	DdayAdapter mDAdapter;
	View DdayheaderView;
	Button btnAdd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dday);
		btnAdd = (Button)findViewById(R.id.btn_add_dday);
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putInt("code", 1);
				DdayDialog dialog = new DdayDialog();
				
				
				dialog.setArguments(b);
				dialog.show(getSupportFragmentManager(), "dialog");
			}
		});
		ddayList = (ListView)findViewById(R.id.listView_dday);
		mDAdapter = new DdayAdapter();
		/*DdayheaderView = (View)getLayoutInflater().inflate(
                R.layout.dday_header_layout, null);
		
		ddayList.addHeaderView(DdayheaderView, null, false);*/
		ddayList.setAdapter(mDAdapter);
		
		initData();
		ddayList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub  
				Bundle b = new Bundle();
				b.putInt("code", 0);
				b.putInt("position", position);   
				
				DdayDialog dialog = new DdayDialog();
				dialog.setArguments(b);
				dialog.show(getSupportFragmentManager(), "dialog");  
			}
		});
	}  
	  
	private void initData() {
		
		for(int i=0;i<3;i++)
		{
			DdayItemData d=new DdayItemData();
		
			d.ddaydate="D-100";
			d.ddayName="600일";
			d.ddayDate="6.25목요일";
			mDAdapter.add(d);
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dday, menu);
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
