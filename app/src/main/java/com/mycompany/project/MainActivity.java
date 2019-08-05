package com.mycompany.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {
	Button dbButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		final DatabaseReference myRef = database.getReference("message");
		dbButton = (Button) findViewById(R.id.button2);
		dbButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Write a message to the database
					myRef.setValue("Hello, World!");
				}
			});
	}
}
