package com.banana.banana.mission;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.banana.banana.R;
import com.banana.banana.love.LoveActivity;
import com.banana.banana.love.NetworkManager;
import com.banana.banana.love.NetworkManager.OnResultListener;
import com.banana.banana.mission.callmissionlist.Item;
import com.banana.banana.mission.callmissionlist.MissionResult;
import com.banana.banana.setting.SettingActivity;

public class MissionActivity extends ActionBarActivity {

	
	ListView MissionListView;
	MissionAdapter mAdapter;
	Button btn ;	
	String[] mData=null;
	ArrayAdapter<String> mArrayAdapter=null;
	Spinner mSpinner=null;
	TextView man_MissionCount,woman_MissionCount;
	TextView man_SuccessCount,woman_SuccessCount;
	TextView mission_Hint;//mission hint 텍스트뷰 
	TextView today, titleView;
	ImageView settingImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission);
		
		ActionBar actionBar = getSupportActionBar(); 
	      actionBar.setDisplayHomeAsUpEnabled(false);
	      actionBar.setDisplayShowTitleEnabled(false);
	      actionBar.setDisplayShowHomeEnabled(false);
	      actionBar.setDisplayShowCustomEnabled(true); // Custom메뉴 설정 true
	      actionBar.setCustomView(R.layout.custom_action_bar); 
	      titleView = (TextView)actionBar.getCustomView().findViewById(R.id.text_title);
	      titleView.setText("MISSION");
	      settingImg = (ImageView)actionBar.getCustomView().findViewById(R.id.img_setting);
	      settingImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MissionActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
		MissionListView=(ListView)findViewById(R.id.listView1);
		btn=(Button)findViewById(R.id.btn_add);
		//미션activity상단 구성-------------------------------------------시작 
		man_MissionCount=(TextView)findViewById(R.id.man_receiveTotal);
		woman_MissionCount=(TextView)findViewById(R.id.woman_receiveTotal);
		man_SuccessCount=(TextView)findViewById(R.id.manScore);
		woman_SuccessCount=(TextView)findViewById(R.id.womanScore);
		//미션activity상단 구성-------------------------------------------끝
		//날짜 text 설정------------------------------------------------
		today=(TextView)findViewById(R.id.Today);
		
		mAdapter=new MissionAdapter(this);
		MissionListView.setAdapter(mAdapter);
		mSpinner=(Spinner)findViewById(R.id.sort);//정렬 spinner설정
		mData=getResources().getStringArray(R.array.list_sort);//string배열 얻어오기
		mArrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,mData);
		mSpinner.setAdapter(mArrayAdapter);
		//미션 팝업창 
		//AlertDialog.Builder builder = new AlertDialog.Builder(MissionActivity.this);
		 /* btn = (Button)findViewById(R.id.btn_receive);
	        btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CustomDialog dialog = new CustomDialog();
					dialog.show(getSupportFragmentManager(), "dialog");
				}
			});*/
		
		initData();
	
		MissionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				MissionItemView itemView = (MissionItemView)view; 
				itemView.requestFocus();
				itemView.setVisibileDetailView(!itemView.isVisibleDetailView());
				//visible이되면
				mission_Hint=(TextView)itemView.detailView.findViewById(R.id.text_hint);
				Item item=(Item)mAdapter.getItem(position);
				mission_Hint.setText(item.hint);
			
			}
		});
	btn=(Button)findViewById(R.id.btn_add);
	btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent=new Intent(MissionActivity.this, AddMissionActivity.class);
				
				startActivity(intent);
			}
		});


//미션 상단 데이터 뿌리기-----------------------------

/*for(int i=0;i<=mAdapter.getCount()-1;i++){
MissionItemData m=(MissionItemData)mAdapter.getItem(i);
if(m.Missionsex.equals("남자")){
	Toast.makeText(MissionActivity.this,"남자다",Toast.LENGTH_SHORT).show();
}
}*/
//Toast.makeText(MissionActivity.this,""+man_total,Toast.LENGTH_SHORT).show();
//man_MissionCount.setText();
	
}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.mission, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
		//	return true;
		//}
		return super.onOptionsItemSelected(item);
	}
	public void initData()
	{ 
		int year = 2015;
		int month = 3;
		int orderby = 1;
		NetworkManager.getInstnace().getMissionList(MissionActivity.this, year, month, orderby, new OnResultListener<MissionResult>() {

			@Override
			public void onSuccess(MissionResult result) {
				// TODO Auto-generated method stub
				mAdapter.addAll(result.result.items.item);
				man_SuccessCount.setText(""+result.result.items.m_copleted);
				woman_SuccessCount.setText(""+result.result.items.f_completed);
				man_MissionCount.setText(""+result.result.items.m_total);
				woman_MissionCount.setText(""+result.result.items.f_total);
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
		/*
		for(int i=0;i<7;i++)
		{
			MissionItemData d=new MissionItemData();
		
			
			if(i%2==0)
				d.iconId=R.drawable.mission_contents_man_ing_icon;
			else
				d.iconId=R.drawable.mission_contents_man_icon;
				
			d.Missiontitle="count"+i;
			
			
			mAdapter.add(d);
		} */
		/*NetworkManager.getInstnace().searchMission(MissionActivity.this, "gd", 1, 10, new OnResultListener<Items>() {
			
			

			@Override
			public void onSuccess(Items result) {
				// TODO Auto-generated method stub
				mAdapter.addAll(result.item);
			}@Override
			public void onFail(int code) {
				Toast.makeText(MissionActivity.this, "fail", Toast.LENGTH_SHORT).show();
			}
		});
	*/}
}
