package com.mycompany.project;

import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;
import java.io.*;
import java.net.*;
import android.os.*;
import android.preference.*;
import com.google.firebase.database.*;
import android.util.*;
import com.bumptech.glide.*;


public class DealAdapter extends RecyclerView.Adapter<DealAdapter.MyViewHolder>
{

	
	static ArrayList<TravelDeal>  deals;
	private FirebaseDatabase mfireadatabase;
	private DatabaseReference mdatabasereference;
	private ChildEventListener mchildListener;
   private  ImageView  imageThumb;
	public DealAdapter()
	{
	
		
		FirebaseUtil.openFirebaseReference("traveldeals");
		mfireadatabase= FirebaseUtil.mFiredatabase;
		mdatabasereference= FirebaseUtil.mDatabaseReference;
		deals=FirebaseUtil.mdeals;
		mchildListener = new  ChildEventListener(){

			@Override
			public void onChildAdded(DataSnapshot dataSnapShot, String p2)
			{
				
				TravelDeal td=dataSnapShot.getValue(TravelDeal.class);
				Log.d("deal: ",td.getTitle());
				td.setId(dataSnapShot.getKey());
				deals.add(td);
				notifyItemChanged(deals.size()-1);
				// TODO: Implement this method
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapShot, String p2)
			{
				// TODO: Implement this method
			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapShot)
			{
				// TODO: Implement this method
			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapShot, String p2)
			{
				// TODO: Implement this method
			}

			@Override
			public void onCancelled(DatabaseError dataSnapShot)
			{
				// TODO: Implement this method
			}



		};

		mdatabasereference.addChildEventListener(mchildListener);
		

	}


	@Override
	public DealAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int p2)
	{
        Context context=parent.getContext();
		View v= LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
		// TODO: Implement this method
		MyViewHolder holder=new MyViewHolder(v);
		return  holder;
	}

	@Override
	public void onBindViewHolder( DealAdapter.MyViewHolder holder,  int position)
	{
		
		TravelDeal deal= deals.get(position);
		
		holder.bind(deal);
		}
	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return deals.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
	{

		@Override
		public void onClick(View view)
		{
			int position= getAdapterPosition();
			TravelDeal selectDeal=deals.get(position);
			Intent intent=new Intent(view.getContext(),DealActivity.class);
			intent.putExtra("Deal",selectDeal);
			view.getContext().startActivity(intent);
			// TODO: iImplement this method
		}

		
		TextView title;
        TextView description;
		TextView price;
		ImageView thumb;
		

		public MyViewHolder(View v)
		{

			super(v);
             v.setOnClickListener(this);
			title=  v.findViewById(R.id.row_title);
			description =v.findViewById(R.id.description);
			price =v.findViewById(R.id.price);
			thumb= v.findViewById(R.id.row_layoutImageView);
			
			
		}
		
		public  void bind (TravelDeal deal){
			title.setText(deal.getTitle());
			description.setText(deal.getDescription());
			price.setText(deal.getPrice());
			showImage(deal.getImageUrl());
		}
		
		private void showImage(String url){
			if (url!=null && url.isEmpty()==false){
				
				Glide
					.with(thumb.getContext()).load(url)
					.centerCrop().fitCenter()
					.placeholder(android.R.drawable.btn_minus).into(thumb);


			}
		
	}
}}

	
						
	
	
	
	
	

