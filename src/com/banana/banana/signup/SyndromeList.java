package com.banana.banana.signup;

import android.os.Parcel;
import android.os.Parcelable;

public class SyndromeList implements Parcelable{ 
	String syndrome_name;
	String syndrome_before;
	String syndrome_after;
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(syndrome_name);
		dest.writeString(syndrome_before);
		dest.writeString(syndrome_after);
	}
	
		public static final Parcelable.Creator<SyndromeList> CREATOR = 
			new Creator<SyndromeList>() {
				
				@Override
				public SyndromeList[] newArray(int size) { 
					return new SyndromeList[size];
				}
				
				@Override
				public SyndromeList createFromParcel(Parcel source) { 
					return new SyndromeList(source);
				}
			};
			
		public SyndromeList(Parcel src) {
			syndrome_name = src.readString();
			syndrome_before = src.readString();
			syndrome_after = src.readString();
		}
		public SyndromeList() {
			// TODO Auto-generated constructor stub
		}
}
