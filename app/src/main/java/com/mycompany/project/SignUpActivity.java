package com.mycompany.project;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.text.*;
import android.text.method.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.*;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailTV, passwordTV;
    private Button regBtn;
	private CheckBox  checkbx;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activity);

        mAuth = FirebaseAuth.getInstance();

        init();
 
		checkbx.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					
					if (!isChecked) {
                        
					passwordTV.setTransformationMethod(PasswordTransformationMethod.getInstance());
					} else {
                        
						passwordTV.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					}
				}
			});
		
        regBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					registerNewUser();
				}
			});
    }
	
	
    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }
    
        mAuth.createUserWithEmailAndPassword(email, password)
			.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
				@Override
				public void onComplete(@NonNull Task<AuthResult> task) {
					if (task.isSuccessful()) {
						Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
						progressBar.setVisibility(View.GONE);

						Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
						startActivity(intent);
					}
					else {
						Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
						progressBar.setVisibility(View.GONE);
					}
				}
			});
    }

    private void init() {
        emailTV = (EditText) findViewById(R.id.email);
        passwordTV = (EditText) findViewById(R.id.password);
        regBtn = (Button) findViewById(R.id.register);
		checkbx= (CheckBox) findViewById(R.id.show);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
	
	
	}
	
