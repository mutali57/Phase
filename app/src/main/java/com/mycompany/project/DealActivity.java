package com.mycompany.project;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;
import com.google.android.gms.tasks.*;
import com.google.firebase.database.*;
import com.google.firebase.storage.*;
import com.google.firebase.auth.*;


public class DealActivity extends AppCompatActivity
{
	private FirebaseDatabase mFirebaseData;
	private DatabaseReference mDatabaseRef;
	private EditText editTitle;
	private EditText editDescription;
	private EditText editPrice;
	private ImageView imgView;
	private Button buttonimg;
	
	FirebaseAuth mAth;
	private static final int RESULT_PICTURE = 42;
	private TravelDeal deal;

	

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dealactivity);
		FirebaseUtil.openFirebaseReference("traveldeals");
        imgView = (ImageView) findViewById(R.id.dealactivityImageView);
		mFirebaseData= FirebaseUtil.mFiredatabase;
		mDatabaseRef= FirebaseUtil.mDatabaseReference;
		mAth=FirebaseAuth.getInstance();
		//mAuth = FirebaseAuth.getInstance();
			//doSignIn();
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
		showImage(deal.getImageUrl());
		buttonimg.setOnClickListener(new View.OnClickListener(){

				

				@Override
				public void onClick(View p1)
				{
					Intent intnt=new Intent(Intent.ACTION_GET_CONTENT);
					
					intnt.setType("image/jpeg");
					intnt.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
					startActivityForResult(Intent.createChooser(intnt,"insert_picture"),RESULT_PICTURE );
					// TODO: Implement this method
				}
			});
	}

	
	
	
	
	
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==RESULT_PICTURE){
			try{Uri imaguri = null;
			if(data.getData()!=null){
		 imaguri= data.getData();}
			//FirebaseUtil.connect();
			 StorageReference ref= FirebaseUtil.mstorageRef.child(imaguri.getLastPathSegment());
			 
			ref.putFile(imaguri).addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						String ex = e.getLocalizedMessage();
					}
				}).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>(){

					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
					{
						String url= taskSnapshot.getDownloadUrl().toString();
						deal.setImageUrl(url);
						String pictureName=taskSnapshot.getStorage().getPath();
						showImage(deal.getImageUrl());
						//TODO: Implement thish method
					}
					

					
				});
				}catch(NullPointerException e){
					e.printStackTrace();
				}
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
				try{
				deleteDeal();
				Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show();
				backToList();
				}catch(NullPointerException e){
					
					
				}
				return true;
			case R.id.signout:
				if(mAth.getCurrentUser()!=null){
					
				
				mAth.signOut();
				}
				Intent intent= new Intent(this,MainActivity.class);
				startActivity(intent);
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

	
	private void showImage(String url){
		if (url!=null && url.isEmpty()==false){
			int width= getResources().getDisplayMetrics().widthPixels;
			Glide
				.with(this)
				
				.load(url)
				.centerCrop().override(width,width)
				.placeholder(android.R.drawable.btn_minus).into(imgView);
			
			
		}
		
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
		if (deal.getImageName()!=null && deal.getImageName().isEmpty()==false){
			StorageReference picRef=FirebaseUtil.mstorage.getReference().child(deal.getImageName());
		picRef.delete().addOnSuccessListener(new OnSuccessListener <Void>(){

				@Override
				public void onSuccess(Void p1)
				{
					Toast.makeText(getApplicationContext(), deal.getImageName()+" Succesfully Deleted",Toast.LENGTH_SHORT).show();
				}
			}).
			addOnFailureListener(new OnFailureListener(){

				@Override
				public void onFailure(Exception p1)
				{
					// TODO: Implement this method
				}}
				);
				
			
		}
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
