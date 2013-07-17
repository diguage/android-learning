package org.ivvy.android.message;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new ButtonOnClickListener());
    }
    
    private class ButtonOnClickListener implements View.OnClickListener{

		public void onClick(View v) {
			EditText mobileText = (EditText) MainActivity.this.findViewById(R.id.mobile);
			String mobile = mobileText.getText().toString();
			
			EditText contentText = (EditText) MainActivity.this.findViewById(R.id.content);
			String content = contentText.getText().toString();
			
			SmsManager manager = SmsManager.getDefault();
			List<String> list = manager.divideMessage(content);
			for (String s : list) {
				manager.sendTextMessage(mobile, null, s, null, null);
			}
			Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
			
		}
    }
}