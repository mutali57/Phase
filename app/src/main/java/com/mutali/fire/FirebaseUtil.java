package com.mutali.fire;
import com.google.firebase.database.*;
import java.util.*;

public class FirebaseUtil
{
	public  static FirebaseDatabase mFiredatabase;
	public static DatabaseReference mDatabaseReference;
	private static FirebaseUtil mFirebaseUtil;
	public static ArrayList<TravelDeal> mdeals;  
	private FirebaseUtil(){}
	public static void openFirebaseReference(String ref)
	{
		if(mFirebaseUtil==null){
			mFirebaseUtil= new FirebaseUtil();
			mFiredatabase=FirebaseDatabase.getInstance();
			mdeals= new ArrayList <TravelDeal>();
		}
		mDatabaseReference= mFiredatabase.getInstance().getReference().child(ref);
		
	}
}
