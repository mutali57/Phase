package com.mycompany.project;
import com.google.firebase.database.*;
import java.util.*;
import com.google.firebase.auth.*;
import android.support.annotation.*;
import com.google.firebase.storage.*;
import org.apache.http.client.utils.*;

public class FirebaseUtil
{    public static  FirebaseStorage mstorage;
     public static StorageReference mstorageRef;
	public  static FirebaseDatabase mFiredatabase;
	public static DatabaseReference mDatabaseReference;
	private static FirebaseUtil mFirebaseUtil;
	public static ArrayList<TravelDeal> mdeals; 
	public static FirebaseAuth mFirebaseAuth;
	
	private FirebaseUtil(){}


	public static void openFirebaseReference(String ref)
	{
		if(mFirebaseUtil==null){
			mFirebaseUtil= new FirebaseUtil();
			mFiredatabase=FirebaseDatabase.getInstance();
			connect();
		}
		mdeals= new ArrayList <TravelDeal>();
		mDatabaseReference= mFiredatabase.getInstance().getReference().child(ref);

	}

	public static void connect(){
		mstorage=FirebaseStorage.getInstance();
		mstorageRef= mstorage.getReference().child("Deals_picture");
	}
}
