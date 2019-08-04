package com.mutali.fire;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;

public class SignUpActivity  extends Activity
{
 EditText email;
 EditText newpassword;
 EditText password;
 Button  savebutton;
 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
	    email= (EditText) findViewById(R.id.signup_editT_email);
		newpassword= (EditText) findViewById(R.id.signupEditTextnewPassword);
		password= (EditText) findViewById(R.id.signupEditTextPassword);
		savebutton= (Button) findViewById(R.id.signsave);
		savebutton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
				}
				
				
			
		});
	}
	
}
