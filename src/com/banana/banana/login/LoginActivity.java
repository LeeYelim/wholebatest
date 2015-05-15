package com.banana.banana.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.banana.banana.JoinCodeInfoParcel;
import com.banana.banana.R;
import com.banana.banana.SplashActivity;
import com.banana.banana.love.NetworkManager;
import com.banana.banana.love.NetworkManager.OnResultListener;
import com.banana.banana.main.BananaMainActivity;
import com.banana.banana.signup.BirthDayInfoActivity;
import com.banana.banana.signup.CoupleRequestActivity;
import com.banana.banana.signup.FirstMeetingActivity;
import com.banana.banana.signup.JoinResult;

public class LoginActivity extends ActionBarActivity {

	EditText idView;
	EditText pwdView;
	Button btn_login_ok;
	int join_code = 0;  //-----나중에 지워
	String gender = "F";//-----나중에 지워
	int user_req = 0;//-----나중에 지워
	int user_no;
	String g, user_phone; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		idView = (EditText)findViewById(R.id.edit_userid);
		pwdView = (EditText)findViewById(R.id.edit_password);
		btn_login_ok = (Button)findViewById(R.id.btn_login_ok);
		btn_login_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String userid = idView.getText().toString();
				final String password = pwdView.getText().toString();
				TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
			 	user_phone = telManager.getLine1Number();
			 	if(user_phone.startsWith("+82")){
			 		user_phone = user_phone.replace("+82", "0");
			 	}
			 	String num1 = user_phone.substring(0, 3);
				String num2 = user_phone.substring(3, 7);
				String num3 = user_phone.substring(7, 11);
				user_phone = num1+"-"+num2+"-"+num3;
				String reg_id = "1";
				NetworkManager.getInstnace().login(LoginActivity.this, userid, password, user_phone, reg_id, new OnResultListener<LoginResult>() {
					
					@Override
					public void onSuccess(LoginResult result) {  
						getJoinInfo();
					} 

					@Override
					public void onFail(int code) { 
						
					}
				});
				
			}
		});
				/*NetworkManager.getInstance().login(LoginActivity.this, userid, password, new OnResultListener<String>() {
					
					@Override
					public void onSuccess(String result) {
						PropertyManager.getInstance().setUserId(userid);
						PropertyManager.getInstance().setPassword(password);  
						Intent intent = new Intent(LoginActivity.this, BananaMainActivity.class);
		 				intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
		 				intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);

					}
						@Override
						public void onError(int code) {
							
						}
					});
				}
			});*/ 
		 
		}
	private void autoLogin() {
		NetworkManager.getInstnace().autoLogin(LoginActivity.this, user_no, user_phone, new OnResultListener<AutoLoginResponse>() {

			@Override
			public void onSuccess(AutoLoginResponse result) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void getJoinInfo() {
		
		NetworkManager.getInstnace().searchJoinInfo(LoginActivity.this, join_code, gender, user_req, new OnResultListener<JoinResult>() {

			
			@Override
			public void onSuccess(JoinResult result) {
				// TODO Auto-generated method stub 
				int join_code = result.result.items.join_code;
				user_req= result.result.items.user_req;
				g = result.result.items.gender; 
				user_no = result.result.user_no;
				
				autoLogin();
				if(join_code == 0) {
					Intent intent = new Intent(LoginActivity.this, BananaMainActivity.class);  
					
					intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
					intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK); 
					startActivity(intent);
				} 
				else if(join_code == 3) { 
					JoinCodeInfoParcel joinData = new JoinCodeInfoParcel();
					joinData.join_code = join_code;
					Intent intent = new Intent(LoginActivity.this, CoupleRequestActivity.class); 
					startActivity(intent); 
					finish();
				} else if(join_code == 4) {
					notInputJoinDetail();
				} else if(join_code == 5) {
					Dialog();
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
		
	protected void notInputJoinDetail() {
		// TODO Auto-generated method stub
			if(user_req == 0 && g.equals("F")) {
				Intent intent = new Intent(LoginActivity.this, FirstMeetingActivity.class);
				startActivity(intent); 
			} else if(user_req == 1 && g.equals("F")) {
				Intent intent = new Intent(LoginActivity.this, BirthDayInfoActivity.class);
				startActivity(intent); 
			} else if(user_req == 0 && g.equals("M")) {
				Intent intent = new Intent(LoginActivity.this, FirstMeetingActivity.class);
				startActivity(intent); 
			} else if(user_req == 1 && g.equals("M")) {
				Intent intent = new Intent(LoginActivity.this, BirthDayInfoActivity.class);
				startActivity(intent); 
			}
	}


		protected void Dialog() {
	
			AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("Alert Dialog");
			builder.setMessage("상대방이 탈퇴하였습니다. 모든 정보가 삭제됩니다.");
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(LoginActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(LoginActivity.this, "Cancel Click", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(LoginActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(LoginActivity.this, "Dialog Canceled", Toast.LENGTH_SHORT).show();
				}
			});
//			builder.setCancelable(false);
			
			builder.create().show();
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
