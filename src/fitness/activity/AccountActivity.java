package fitness.activity;

import com.example.yjl.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import fitness.database.DatabaseHelper;


public class AccountActivity extends Activity {

	DatabaseHelper helper = new DatabaseHelper(this);
	
	ImageButton a ;
	ImageButton b ;
	TextView c;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		a=(ImageButton) findViewById(R.id.NewimageButton1);
		b=(ImageButton) findViewById(R.id.cancelButton);
		
		Intent j=getIntent();
		String username=j.getStringExtra("Username");
		
	    c =(TextView) findViewById(R.id.manager);
		c.setText("Welcome,"+ username +"!");
		
		a.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				 Intent j=getIntent();
			     String username=j.getStringExtra("Username");
				EditText email =(EditText)findViewById(R.id.NewTFemail);
				EditText pass1 =(EditText)findViewById(R.id.NewTFpass1);
				EditText pass2 =(EditText)findViewById(R.id.NewTFpass2);
				
				
				String emailStr =email.getText().toString();
				String pass1Str =pass1.getText().toString();
				String pass2Str =pass2.getText().toString();
		if(emailStr.length()!=0 || pass1Str.length() != 0 ){
			if( !pass1Str.equals(pass2Str))
			{
				Toast pass = Toast.makeText(AccountActivity.this, "Passwords don not match!", Toast.LENGTH_SHORT);
				pass.show();
			}
			else
			{
				
				
				helper.upDate(username,pass1Str,emailStr);
				Toast success = Toast.makeText(AccountActivity.this, "Update successfully!", Toast.LENGTH_SHORT);
				success.show();
				
			}
			
		    }
			else
			{
			Toast unsuccess = Toast.makeText(AccountActivity.this, "You should complete the form!", Toast.LENGTH_SHORT);
			unsuccess.show();
			}
		}
		
				// TODO Auto-generated method stub
			
		});
		
            b.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v) {
				
				EditText email =(EditText)findViewById(R.id.NewTFemail);
				EditText pass1 =(EditText)findViewById(R.id.NewTFpass1);
				EditText pass2 =(EditText)findViewById(R.id.NewTFpass2);
				
				email.setText("");
				pass1.setText("");
				pass2.setText("");
				
			}
			});
			
			
		}	
}
