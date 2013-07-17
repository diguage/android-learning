package org.ivvy.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {

	private Context context;

	public PreferencesService(Context context) {
		this.context = context;
	}

	public void save(String name, int age) {
		SharedPreferences preferences = context.getSharedPreferences("ivvy",
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("name", name);
		editor.putInt("age", age);
		editor.commit();
	}

	public Map<String, String> getPreferences() {
		Map<String, String> params = new HashMap<String, String>();

		SharedPreferences preferences = context.getSharedPreferences("ivvy",
				Context.MODE_PRIVATE);
		params.put("name", preferences.getString("name", ""));// 第二个参数时，如果没有这个参数的，则返回这个more的值。
		params.put("age", String.valueOf(preferences.getInt("age", 0)));
		return params;
	}
}
