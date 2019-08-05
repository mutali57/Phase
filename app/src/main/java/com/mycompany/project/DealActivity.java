package com.mycompany.project;
import android.test.*;
import android.app.*;
import android.os.*;
import java.security.*;
import android.view.*;
import com.google.firebase.database.*;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.net.*;
import com.google.firebase.storage.*;
import com.google.android.gms.tasks.*;


public class DealActivity extends AppCompatActivity
{
	private FirebaseDatabase mFirebaseData;
	private DatabaseReference mDatabaseRef;
	private EditText editTitle;
	private EditText editDescription;
	private EditText editPrice;
	private Button buttonimg;
	private static final int RESULT = 42;
	private TravelDeal deal;

	private StorageReference mstoragere;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dealactivity);
		FirebaseUtil.openFirebaseReference("traveldeals");

		mFirebaseData= FirebaseUtil.mFiredatabase;
		mDatabaseRef= FirebaseUtil.mDatabaseReference;
			
		buttonimg= (Button) findViewById(R.id.dealImgBUT);
		
		editDescription= (EditText) findViewById(R.id.databaseinput_description);
		editPrice= (EditText) findViewById(R.id.databaseinput_textPrice);
		editTitle=(EditText) findViewById(R.id.databaseinput_txtTitle);
		final Intent Intent=getIntent();
		TravelDeal deal=(TravelDeal) Intent.getSerializableExtra("Deal");
		if(deal==null){
			deal= new TravelDeal();
		}
		this.deal=deal;
		editTitle.setText(deal.getTitle());
		editDescription.setText(deal.getDescription());
		editPrice.setText(deal.getPrice());
		buttonimg.setOnClickListener(new View.OnClickListener(){

				

				@Override
				public void onClick(View p1)
				{
					Intent intnt=new Intent(Intent.ACTION_GET_CONTENT);
					
					intnt.setType("image/jpeg");
					intnt.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
					startActivityForResult(intnt.createChooser(intnt,"insert picture"), RESULT);
					// TODO: Implement this method
				}
			});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==RESULT &&requestCode==RESULT_OK){
			Uri imaguri= data.getData();
			 StorageReference ref= FirebaseUtil.mstorageRef.child(imaguri.getLastPathSegment());
			//.ref.putFile(imaguri).addOnSuccessListener(this, new OnSuccessListener<UploadTask>(){});
		}
		
		
		
	}
 
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.save_menu:
				saveDeal();
				clean();
				backToList();
				return true;
			case R.id.delete:
				
				deleteDeal();
				Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show();
				backToList();
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
		deal.setTitle(editTitle.getText().toString());
		deal.setPrice( editPrice.getText().toString());
		deal.setDescription(editDescription.getText().toString());
		if(deal.getId()==null){
			mDatabaseRef.push().setValue(deal);
		}
		else{
			mDatabaseRef.child(deal.getId()).setValue(deal);
		}
		
		
		;
		
		// TODO: Implement this method
	}
	
private void  deleteDeal(){
	if(deal==null){
		Toast.makeText(this,"please save the deal before deleting",Toast.LENGTH_SHORT).show();
	return;
		}
		mDatabaseRef.child(deal.getId()).removeValue();
}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		MenuInflater inflate= getMenuInflater();
		inflate.inflate(R.menu.save_menu,menu);
		// TODO: Implement this method
		return true;
	}
	private void  backToList(){
		Intent intent = new Intent(this,ListActivity.class);
		startActivity(intent);
	}
	
	
	
}
