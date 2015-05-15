package com.banana.banana.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.banana.banana.R;
import com.banana.banana.love.NetworkManager;
import com.banana.banana.love.NetworkManager.OnResultListener;
import com.banana.banana.mission.callmissionlist.MissionResult;

public class AddMissionActivity extends ActionBarActivity {
	Button btnOK;
	ToggleButton btnTheme1, btnTheme2, btnTheme3, btnTheme4, btnTheme5, btnTheme6;
	
	String mission_theme;
	boolean selected = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_mission);
		
		btnTheme1 = (ToggleButton)findViewById(R.id.ToggleTheme1); 
		btnTheme2 = (ToggleButton)findViewById(R.id.ToggleTheme2); 
		btnTheme3 = (ToggleButton)findViewById(R.id.ToggleTheme3); 
		btnTheme4 = (ToggleButton)findViewById(R.id.ToggleTheme4); 
		btnTheme5 = (ToggleButton)findViewById(R.id.ToggleTheme5); 
		btnTheme6 = (ToggleButton)findViewById(R.id.ToggleTheme6); 
		
		if(btnTheme1.isChecked()) {
			mission_theme = "섹시";
			btnTheme2.setChecked(false);;
			btnTheme3.setSelected(false);
			btnTheme4.setSelected(false);
			btnTheme5.setSelected(false);
			btnTheme6.setSelected(false);
			selected = true;
		} else if (btnTheme2.isChecked()) {
			mission_theme = "악마";
			btnTheme1.setSelected(false);
			btnTheme3.setSelected(false);
			btnTheme4.setSelected(false);
			btnTheme5.setSelected(false);
			btnTheme6.setSelected(false);
			selected = true;
		} else if (btnTheme3.isChecked()) {
			mission_theme = "랜덤"; 
			btnTheme1.setSelected(false);
			btnTheme2.setSelected(false);
			btnTheme4.setSelected(false);
			btnTheme5.setSelected(false);
			btnTheme6.setSelected(false);
			selected = true;
		} else if (btnTheme4.isChecked()) {
			mission_theme = "첫만남"; 
			btnTheme2.setSelected(false);
			btnTheme3.setSelected(false);
			btnTheme1.setSelected(false);
			btnTheme5.setSelected(false);
			btnTheme6.setSelected(false);
			selected = true;
		} else if (btnTheme5.isChecked()) {
			mission_theme = "천사";
			btnTheme2.setSelected(false);
			btnTheme3.setSelected(false);
			btnTheme4.setSelected(false);
			btnTheme1.setSelected(false);
			btnTheme6.setSelected(false);
			selected = true;
		} else if (btnTheme6.isChecked()) {
			mission_theme = "애교"; 
			btnTheme2.setSelected(false);
			btnTheme3.setSelected(false);
			btnTheme4.setSelected(false);
			btnTheme5.setSelected(false);
			btnTheme1.setSelected(false);
			selected = true;
		}
		
		 
		btnOK=(Button)findViewById(R.id.btn_ok);
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				//if(selected  == true) {
					addMission();
				//}
			}
		});
	}

	protected void addMission() {
		// TODO Auto-generated method stub
		NetworkManager.getInstnace().addMission(AddMissionActivity.this, mission_theme, new OnResultListener<MissionResult>() {
			
			@Override
			public void onSuccess(MissionResult result) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(AddMissionActivity.this,MissionSendAlram.class);
				Toast.makeText(AddMissionActivity.this, mission_theme, Toast.LENGTH_SHORT).show();
				startActivity(intent); 
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_mission, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
