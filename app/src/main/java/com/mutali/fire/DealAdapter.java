package com.mutali.fire;

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


public class DealAdapter extends RecyclerView.Adapter<DealAdapter.MyViewHolder>
{

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return deals.size();
	}
	ArrayList<TravelDeal> deals;
	private FirebaseDatabase mfireadatabase;
	private DatabaseReference mdatabasereference;
	private ChildEventListener mchildListener;

	public DealAdapter()
	{
	
		
		FirebaseUtil.openFirebaseReference("traveldeals");
		mfireadatabase= FirebaseUtil.mFiredatabase;
		deals=FirebaseUtil.mdeals;
		mdatabasereference= FirebaseUtil.mDatabaseReference;
		mchildListener = new  ChildEventListener(){

			@Override
			public void onChildAdded(DataSnapshot dataSnapShot, String p2)
			{
				
				TravelDeal td=dataSnapShot.getValue(TravelDeal.class);
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

		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
		// TODO: Implement this method
		MyViewHolder holder=new MyViewHolder(v);
		return  holder;
	}

	@Override
	public void onBindViewHolder(final DealAdapter.MyViewHolder holder, final int position)
	{
		TravelDeal deal= deals.get(position);
		holder.bind(deal);
		}


	public static class MyViewHolder extends RecyclerView.ViewHolder{
		TextView title;
        
		

		public MyViewHolder(View v)
		{

			super(v);

			title= (TextView) v.findViewById(R.id.row_title);
			
		}
		
		public  void bind (TravelDeal deal){
			title.setText(deal.getTitle());
		}
	}
}

	
							
	
	
	
	
	

