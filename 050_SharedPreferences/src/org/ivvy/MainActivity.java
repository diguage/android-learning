package org.ivvy;

import java.util.Map;

import org.ivvy.service.PreferencesService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private PreferencesService service;
	private EditText nameText;
	private EditText ageText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) this.findViewById(R.id.saveBtn);
		button.setOnClickListener(new ButtonClickListener());

		nameText = (EditText) findViewById(R.id.name);
		ageText = (EditText) findViewById(R.id.age);

		service = new PreferencesService(getApplicationContext());
		Map<String, String> params = service.getPreferences();
		nameText.setText(params.get("name"));
		ageText.setText(Integer.parseInt(params.get("age")));
	}

	private final class ButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			String name = nameText.getText().toString();
			int age = Integer.parseInt(ageText.getText().toString());
			service.save(name, age);
			Toast.makeText(getApplicationContext(), R.string.success,
					Toast.LENGTH_LONG).show();
		}

	}
}