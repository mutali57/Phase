package com.mycompany.project;
import android.support.v7.app.*;
import android.os.*;
import com.google.firebase.database.*;
import android.support.v4.widget.*;
import android.widget.*;
import java.util.*;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.*;
import android.view.*;
import android.content.*;
import com.google.firebase.auth.*;
import com.google.android.gms.tasks.*;
import android.support.annotation.*;
public class ListActivity extends AppCompatActivity
{ private FirebaseAuth mAuth;

   ArrayList<TravelDeal> deals;
  private FirebaseDatabase mfireadatabase;
	private DatabaseReference mdatabasereference;
	private ChildEventListener mchildListener;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		mAuth = FirebaseAuth.getInstance();
		//doSignIn();
		FirebaseUtil .openFirebaseReference("traveldeals");
		RecyclerView rvDeals = (RecyclerView) findViewById(R.id.activity_listRecycler);
		final DealAdapter dAdapter= new DealAdapter();
		rvDeals.setAdapter(dAdapter);
		LinearLayoutManager  dealManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
		rvDeals.setLayoutManager(dealManager);
		
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		//FirebaseUtil.attachListener();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		//FirebaseUtil.detachListner();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		MenuInflater inflate=getMenuInflater();
	inflate.inflate(R.menu.activity_menu,menu);
		
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()){
			case R.id.insert:
				Intent intent = new Intent(this,DealActivity.class);
				intent.putExtra("deleteNull", 110);
				startActivity(intent);
				doSignIn();
				return true;
				
				
			
		}
		
		
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	} 
	
	private void doSignIn() {
		mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() 
			{
				@Override
				public void onSuccess(AuthResult authResult) {
					//System.out.println(  authResult.getUser());
					//akePictureButton.setEnabled(true);
				}
			}).addOnFailureListener(this, new OnFailureListener() {
				@Override
				public void onFailure(@NonNull Exception e) {
					String foo = e.getLocalizedMessage();
					System.out.println("foo"+foo);
				}
			});
	}
	
	
}
