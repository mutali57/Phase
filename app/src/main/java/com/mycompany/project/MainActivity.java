package com.mycompany.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.*;
//import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button registerBtn, loginBtn;
	private FirebaseAuth mAuth;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mAuth = FirebaseAuth.getInstance();
		
        init();

        registerBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
					startActivity(intent);
				}
			});
        loginBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					System.out.println(mAuth.getCurrentUser());
			        if(mAuth.getCurrentUser()!=null){
						Intent intent = new Intent(MainActivity.this,DealActivity.class);
						startActivity(intent);
					}else{
					Intent intent = new Intent(MainActivity.this, LogInActivity.class);
					startActivity(intent);
					}
				}
			});
    }
	/*public void onCheckbox(View view) {
		// Is the view now checked?
		boolean checked = ((CheckBox) view).isChecked();

		// Check which checkbox was clicked
		switch(view.getId()) {
			case R.id.checkbox_meat:
				if (checked)
                // Put some meat on the sandwich
				else
                // Remove the meat
				break;
			
				// TODO: Veggie sandwich
		}
	}
	*/
	
	
	public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }
	
    private void init() {
        registerBtn = (Button) findViewById(R.id.register);
        loginBtn = (Button) findViewById(R.id.login);
    }
	
	@Override
	public void onStart() {
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		//updateUI(currentUser);
	}
	/*
	private void updateUI(FirebaseUser user) {
        if (user != null) {
            ((TextView) findViewById(R.id.textSignInStatus)).setText(
				"User ID: " + user.getUid());
        } else {
            ((TextView) findViewByiId(R.id.textSignInStatus)).setText(
				"Error: sign in failed.");
        }
    }
	*/
	
	};


