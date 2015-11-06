package com.example.yjl;

import com.example.db.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class SignUpActivity extends Activity {

	DatabaseHelper helper = new DatabaseHelper(this);
	
	ImageButton a ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		a=(ImageButton) findViewById(R.id.imageButton1);
		a.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText name =(EditText)findViewById(R.id.TFName);
				EditText email =(EditText)findViewById(R.id.TFemail);
				EditText pass1 =(EditText)findViewById(R.id.TFpass1);
				EditText pass2 =(EditText)findViewById(R.id.TFpass2);
				
				String nameStr =name.getText().toString();
				String emailStr =email.getText().toString();
				String pass1Str =pass1.getText().toString();
				String pass2Str =pass2.getText().toString();
			
			if( !pass1Str.equals(pass2Str))
			{
				Toast pass = Toast.makeText(SignUpActivity.this, "Passwords don not match!", Toast.LENGTH_SHORT);
				pass.show();
			}
			else
			{
				Contact c = new Contact();
				c.setName(nameStr);
				c.setEmail(emailStr);
				c.setPass(pass1Str);
				
				helper.insertContact(c);
				Toast success = Toast.makeText(SignUpActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT);
				success.show();
			}
			
			
		
				// TODO Auto-generated method stub
			}
			});
	}
}
	
