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

public class LogInActivity extends AppCompatActivity {

    private EditText emailTV, passwordTV;
    private Button loginBtn;
    private ProgressBar progressBar;
	private CheckBox  checkBox;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        mAuth = FirebaseAuth.getInstance();

        init();

        loginBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					loginUserAccount();
				}
			});
			
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

					if (!isChecked) {

						passwordTV.setTransformationMethod(PasswordTransformationMethod.getInstance());
					} else {

						passwordTV.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					}
				}
			});
			
    }

    private void loginUserAccount() {
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
      
        mAuth.signInWithEmailAndPassword(email, password)
			.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
				@Override
				public void onComplete(@NonNull Task<AuthResult> task) {
					if (task.isSuccessful()) {
						Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
						progressBar.setVisibility(View.GONE);
                         Bundle bundle = new Bundle();
						Intent intent = new Intent(LogInActivity.this, DealActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
					
					}
					else {
						Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
						progressBar.setVisibility(View.GONE);
					}
				}
			});
    }
	
	

    private void init() {
        emailTV = (EditText) findViewById(R.id.email);
        passwordTV = (EditText) findViewById(R.id.password);
        checkBox =(CheckBox) findViewById(R.id.showCheckBox);
        loginBtn = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
	}
	
