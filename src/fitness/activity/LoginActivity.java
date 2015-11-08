package fitness.activity;

import com.example.yjl.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import fitness.database.DatabaseHelper;


public class LoginActivity extends Activity {
	DatabaseHelper helper = new DatabaseHelper(this);
    ImageButton a;
    ImageButton b;
    
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	
		a=(ImageButton) findViewById(R.id.LoginButton);
		b=(ImageButton) findViewById(R.id.SignUpButton);
		a.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText m= (EditText)findViewById(R.id.Name);
			    String str =m.getText().toString();
				EditText n= (EditText)findViewById(R.id.Pass);
				String pass =n.getText().toString();
				
				String password = helper.searchPass(str);
				if(pass.equals(password))
				{   
					
					Intent i=new Intent(LoginActivity.this,MainActivity.class);
					i.putExtra("Username",str);
					LoginActivity.this.startActivity(i);
					
				}
				else
				{
					Toast temp = Toast.makeText(LoginActivity.this, "Username and password don not match!", Toast.LENGTH_SHORT);
					temp.show();
				}
				
				// TODO Auto-generated method stub
			}
			});
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
				LoginActivity.this.startActivity(i);// TODO Auto-generated method stub
			}
			});
		
		
	}

}

