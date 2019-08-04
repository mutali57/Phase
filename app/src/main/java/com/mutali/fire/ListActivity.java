package com.mutali.fire;
import android.support.v7.app.*;
import android.os.*;
import com.google.firebase.database.*;
import android.support.v4.widget.*;
import android.widget.*;
import java.util.*;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.*;
public class ListActivity extends AppCompatActivity
{ 

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
		
		RecyclerView rvDeals = (RecyclerView) findViewById(R.id.activity_listRecycler);
		final DealAdapter dAdapter= new DealAdapter();
		rvDeals.setAdapter(dAdapter);
		LinearLayoutManager  dealManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
		rvDeals.setLayoutManager(dealManager);
		
	} 

	
}
