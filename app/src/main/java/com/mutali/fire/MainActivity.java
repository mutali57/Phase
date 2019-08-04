package com.mutali.fire;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;
import android.content.*;
//import com.google.firebase.database.*;
//mport com.android.support

public class MainActivity extends Activity  implements View.OnClickListener
{
	
	
	private static final int RC_SIGN_IN = 100;
	ImageButton  signup;
	ImageButton  googlesignin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		signup = (ImageButton) findViewById(R.id.mainsignup);
		googlesignin = (ImageButton) findViewById(R.id.mainsignGoogle);
		signup.setOnClickListener(this);
		googlesignin.setOnClickListener(this);

	}
	@Override
	public void onClick(View v)
	{
		switch(v.getId()){
			case R.id.mainsignup:
				Intent intent= new Intent(this,SignUpActivity.class);
				startActivity(intent);
				break;
			case R.id.mainsignGoogle:
				/*startActivityForResult(
					//AuthUI.getInstance().createSignInIntentBuilder()
					.build(),
					RC_SIGN_IN)*/
				break;
			
		}

		// TODO: Implement this method
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		/*if(requestCode == RC_SIGN_IN){
			handleSignInResponse(resultCode, data);
			return;
	
}
*/

}

	/*@MainThread
	private void handleSignInResponse(int resultCode, Intent data) {
		IdpResponse response = IdpResponse.fromResultIntent(data);
		Toast toast;
		// Successfully signed in
		if (resultCode == ResultCodes.OK) {
			//startActivity(SignedInActivity.createIntent(this, response));
			finish();
			return;
		} else {
			// Sign in failed
			if (response == null) {
				// User pressed back button
				toast = Toast.makeText(this, "Sign in was cancelled!", Toast.LENGTH_LONG);
				toast.show();
				return;
			}
			if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
				toast = Toast.makeText(this, "You have no internet connection", Toast.LENGTH_LONG);
				toast.show();
				return;
			}
			if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
				toast = Toast.makeText(this, "Unknown Error!", Toast.LENGTH_LONG);
				toast.show();
				return;
			}
		}
		toast = Toast.makeText(this, "Unknown Error!", Toast.LENGTH_LONG);
		toast.show();
	}
	*/
	
	public static Intent createIntent(Context context) {
		Intent in = new Intent();
		in.setClass(context, MainActivity.class);
		return in;
	}
}
