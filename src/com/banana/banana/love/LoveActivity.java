package com.banana.banana.love;
 
import java.util.Calendar;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.banana.banana.R;
import com.banana.banana.love.NetworkManager.OnResultListener;
import com.banana.banana.setting.SettingActivity;

import de.passsy.holocircularprogressbar.HoloCircularProgressBar;

public class LoveActivity extends ActionBarActivity {
	private HoloCircularProgressBar mHoloCircularProgressBar;
	private ObjectAnimator mProgressBarAnimator;
	ListView loveList;
	LoveAdapter mLAdapter; 
	View headerView; 
	Button btn_LoveAdd; 
	TextView noCondomView, isCondomView, LoveDayView, YearView, monthView;
	//ToggleButton repeatLovebtn;
	int orderby=0;
	int year, month;
	float isCondom, notCondom;
	Spinner lovesort;
	ArrayAdapter<CharSequence> sortAdapter;
	private CustomGallery mCustomGallery;
	private CustomGallery mCustomGallery2;
	View layoutSort, sortLayout;
	CustomGalleryImageAdapter cAdapter;
	
	LoveDialog dialog; 
	View layoutTodayPercent; 
	int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_love); 
		loveList = (ListView)findViewById(R.id.listView1); 
		//repeatLovebtn = (ToggleButton)findViewById(R.id.toggleButton_love_sort);
		//btn_LoveAdd = (Button)findViewById(R.id.btn_add_love);
		mLAdapter = new LoveAdapter(this); 
		headerView = (View)getLayoutInflater().inflate(
                R.layout.love_header_layout,null);  
		loveList.addHeaderView(headerView,null,false);
		loveList.setAdapter(mLAdapter);
		loveList.requestFocus();
		LoveDayView = (TextView)findViewById(R.id.text_love_date);
		isCondomView = (TextView)headerView.findViewById(R.id.text_iscondom1);
		noCondomView = (TextView)headerView.findViewById(R.id.text_nocondom1);
		lovesort = (Spinner)headerView.findViewById(R.id.love_sort);
	//	layoutSort = (View)headerView.findViewById(R.id.layout_sort);
	//	sortLayout = (View)headerView.findViewById(R.id.Sort_layout);
		YearView = (TextView)headerView.findViewById(R.id.text_sort_year);
		monthView = (TextView)headerView.findViewById(R.id.text_sort_month);  
		mHoloCircularProgressBar = (HoloCircularProgressBar)headerView.findViewById(R.id.holoCircularProgressBar1);
		layoutTodayPercent = (View)headerView.findViewById(R.id.layout_love_today_percent);
		sortAdapter = ArrayAdapter.createFromResource(this, R.array.list_love_sort, android.R.layout.simple_spinner_item);
		cAdapter = new CustomGalleryImageAdapter(this);
		
		
  
		
		layoutTodayPercent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(count % 2 == 0) {
					animate(mHoloCircularProgressBar, null, (float)isCondom/100, 1000);
					mHoloCircularProgressBar.setProgress((float)isCondom/100);
					count++;
				} else if(count % 2 == 1) {
					animate(mHoloCircularProgressBar, null, (float)notCondom/100, 1000);
				mHoloCircularProgressBar.setProgress((float)notCondom/100);
					count++;
				}
			}
		});


		lovesort.setAdapter(sortAdapter);
		lovesort.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override  
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(position == 1) {
				orderby = position;
				Toast.makeText(LoveActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
				initData();
				} else if(position == 0) {
					orderby = position;
					Toast.makeText(LoveActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
					initData();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				orderby = 0;
			}
		});
		
		/*btn_LoveAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();
				b.putInt("code", 1);
				dialog = new LoveDialog();
				dialog.setArguments(b);
				dialog.show(getSupportFragmentManager(), "dialog");
			}
		});*/
		
		loveList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle b = new Bundle();
				b.putInt("year", year);
				b.putInt("month", month);
				b.putInt("orderby", orderby);
				b.putInt("code", 0);
				b.putInt("position", position);  
				dialog = new LoveDialog();
				dialog.setArguments(b);
				dialog.show(getSupportFragmentManager(), "dialog");  
			}
		}); 
		
		/* mCustomGallery = (CustomGallery) findViewById(R.id.gallery);
	     mCustomGallery.setAdapter(cAdapter);
	     
	     
	     mCustomGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) { 
			
			 Toast.makeText(getBaseContext(), (position + 1) + "년도",
			    	 Toast.LENGTH_SHORT).show();
			 
			 YearView.setText(""+cAdapter.mImageID[position]);
			 year = Integer.parseInt(cAdapter.mImageID[position]);
			 initData();
			 //if(month==2){
				// Log.i("count:", ""+mCustomGallery2.getChildCount());
			//  mCustomGallery2.getChildAt(11).setVisibility(View.GONE);
			  
			 // }
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	});
	     
	  mCustomGallery2 = (CustomGallery) findViewById(R.id.gallery2); */
	  //if(month==2){
		// Toast.makeText(MyApplication.getContext(), mCustomGallery2.getChildCount(),Toast.LENGTH_LONG).show();
	  //mCustomGallery2.getChildAt(11).setVisibility(View.GONE);
	  
	 // }
	  //else{
		/* LayoutInflater mInflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   View mview =mInflater.inflate(R.layout.gallery2, null);
	  


		  ImageView image=(ImageView)mview.findViewById(R.id.image);
		  image.setBackgroundResource(R.drawable.pic10);
		  mCustomGallery2.addView(image);*/
	 // }
	 
	  
	  /*	layoutSort.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setVisibileDetailView(!isVisibleDetailView());
			}
		});*/

		initData(); 
		
		
		
	}
	
	
	private void initData() {  	 
		NetworkManager.getInstnace().getLoveList(LoveActivity.this, orderby, year, month, new OnResultListener<LoveList>() {

			@Override
			public void onSuccess(LoveList result) {
				// TODO Auto-generated method stub
			//	Toast.makeText(LoveActivity.this, result.message, Toast.LENGTH_SHORT).show();
				mLAdapter.clear();
				mLAdapter.addAll(result.items.item);
				isCondom = result.items.today_condom;
				notCondom = result.items.today_notcondom; 
				isCondomView.setText(""+isCondom);
				noCondomView.setText(""+notCondom);     
				mHoloCircularProgressBar.setProgress((float)result.items.today_notcondom/100); 
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
 
 

	public boolean isVisibleDetailView() {
		return sortLayout.getVisibility() == View.VISIBLE;
	}
	
	public void setVisibileDetailView(boolean isVisible) {
		sortLayout.setVisibility(isVisible?View.VISIBLE:View.GONE);
	}
	/*
	private void initData() {
		// TODO Auto-generated method stub 
		
			NetworkManager.getInstnace().getLoveList(this, new OnResultListener<ArrayList<LoveItemData>>() {
				
				@Override
				public void onSuccess(ArrayList<LoveItemData> result) {
					// TODO Auto-generated method stub
					for(LoveItemData d : result) {
						mLAdapter.add(d);
					}
				}
				
				@Override
				public void onFail(int code) {
					// TODO Auto-generated method stub
					
				}
			});
		
			}*/

	private void animate(final HoloCircularProgressBar progressBar, final AnimatorListener listener) {
		final float progress = (float) (Math.random() * 2);
		int duration = 3000;
		animate(progressBar, listener, progress, duration);
	}

	private void animate(final HoloCircularProgressBar progressBar, final AnimatorListener listener,
			final float progress, final int duration) {

		mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
		mProgressBarAnimator.setDuration(duration);

		mProgressBarAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationCancel(final Animator animation) {
			}

			@Override
			public void onAnimationEnd(final Animator animation) {
				progressBar.setProgress(progress);
			}

			@Override
			public void onAnimationRepeat(final Animator animation) {
			}

			@Override
			public void onAnimationStart(final Animator animation) {
			}
		});
		if (listener != null) {
			mProgressBarAnimator.addListener(listener);
		}
		mProgressBarAnimator.reverse();
		mProgressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				progressBar.setProgress((Float) animation.getAnimatedValue());
			}
		});
		progressBar.setMarkerProgress(progress);
		mProgressBarAnimator.start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_one, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_m1 :
			Intent intent = new Intent(LoveActivity.this, SettingActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
