package com.mycompany.project;
import com.google.firebase.database.*;
import java.util.*;
//import com.firebase.ui.auth.util.*;
import org.apache.http.auth.*;
//import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.*;
//import com.firebase.ui.auth.*;
import android.support.annotation.*;



public class FirebaseUtil
{
	public  static FirebaseDatabase mFiredatabase;
	public static DatabaseReference mDatabaseReference;
	private static FirebaseUtil mFirebaseUtil;
	public static ArrayList<TravelDeal> mdeals; 
	//public static FirebaseAuth mFirebaseAuth;
	//public static FirebaseAuth.AuthStateListener mAuthList
	private FirebaseUtil(){}


	public static void openFirebaseReference(String ref)
	{
		if(mFirebaseUtil==null){
			mFirebaseUtil= new FirebaseUtil();
			mFiredatabase=FirebaseDatabase.getInstance();
			//mdeals= new ArrayList <TravelDeal>();
			//mFirebaseAuth=FirebaseAuth.getInstance();
			/*mAuthList = new FirebaseAuth.AuthStateListener(){

				@Override
				public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
				{
					List <AuthUI.IdpConfig> providerS= Arrays.asList(
						new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_LINK_PROVIDER.).build(),
						new AuthUI.IdpConfig.Builder(AuthUI.GOOGlE_PROVIDER).build());


					// TODO: Implement this method
				}
			};*/
		}
		mdeals= new ArrayList <TravelDeal>();
		mDatabaseReference= mFiredatabase.getInstance().getReference().child(ref);

	}


	/*public static void attachListener(){
		mFirebaseAuth.addAuthStateListener(mAuthList);
	}

	public static void detachListner(){
		mFirebaseAuth.removeAuthStateListener(mAuthList);

	}*/
}
