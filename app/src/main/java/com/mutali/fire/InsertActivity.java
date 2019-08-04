package com.mutali.fire;
import android.test.*;
import android.app.*;
import android.os.*;
import java.security.*;
import android.view.*;
import com.google.firebase.database.*;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;


public class InsertActivity extends AppCompatActivity
{
	private FirebaseDatabase mFirebaseData;
	private DatabaseReference mDatabaseRef;
	private EditText editTitle;
	private EditText editDescription;
	private EditText editPrice;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.databaseinput);
		FirebaseUtil.openFirebaseReference("traveldeals");

		mFirebaseData= FirebaseUtil.mFiredatabase;
		mDatabaseRef= FirebaseUtil.mDatabaseReference;
			
		
		editDescription= (EditText) findViewById(R.id.databaseinput_description);
		editPrice= (EditText) findViewById(R.id.databaseinput_textPrice);
		editTitle=(EditText) findViewById(R.id.databaseinput_txtTitle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.save_menu:
				saveDeal();
				clean();
				return true;
			default: 
			//TODO: Implement this method
				return super.onOptionsItemSelected(item);
		}
		}
		
		
		

	private void clean()
	{editPrice.setText("");
	editTitle.setText("");
	editDescription.setText("");
		// TODO: Implement this method
	}

	private void saveDeal()
	{
		String title = editTitle.getText().toString();
		String price = editPrice.getText().toString();
		String description= editDescription.getText().toString();
		TravelDeal deal= new TravelDeal(title,description,price,"");
	
		
		
		mDatabaseRef.push().setValue(deal,new DatabaseReference.CompletionListener() {
				@Override
				public void onComplete(DatabaseError dataError, DatabaseReference dataRef) {
				
					if(dataError != null) {
						System.err.println("Error adding.!");
						dataError.toException().printStackTrace();
					}
				}
			});
		
		// TODO: Implement this method
	}
	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		MenuInflater inflate= getMenuInflater();
		inflate.inflate(R.menu.save_menu,menu);
		// TODO: Implement this method
		return true;
	}
	
	
	
	
}
