package org.ivvy.android.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter.ViewBinder;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button dealBtn = (Button) findViewById(R.id.dealBtn);
		dealBtn.setOnClickListener(new ButtonOnClickListener());
	}
	
	private class ButtonOnClickListener implements View.OnClickListener{

		public void onClick(View v) {
			EditText numText = (EditText) MainActivity.this.findViewById(R.id.phone);
			String phone = numText.getText().toString();
			Intent intent = new Intent();
			intent.setAction("android.intent.action.CALL");
			intent.setData(Uri.parse("tel:"+phone));
			startActivity(intent);
		}
		
	}
	
	
}