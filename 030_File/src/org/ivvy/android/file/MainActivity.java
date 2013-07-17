package org.ivvy.android.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class MainActivity extends Activity {
	private static final String TAG = "org.ivvy";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// testFile();
		testSDCard();

	}

	// 测试SDCard卡的存储
	private void testSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
			File saveFile = new File(sdCardDir, "sdcard.txt");

			try {
				FileOutputStream outStream = new FileOutputStream(saveFile);
				outStream.write("ivvy.org——分享带来快乐".getBytes());
				outStream.close();
			} catch (IOException e) {
				Log.e(TAG, "对不起，向SDCard存储文件时出错");
				Log.e(TAG, e.getMessage());
			}

		}

	}

	// 测试直接的文件存储
	private void testFile() {
		try {
			FileOutputStream outStream = this.openFileOutput("ivvy.txt",
					Context.MODE_PRIVATE);
			outStream.write("ivvy.org——分享带来快乐".getBytes());
			outStream.close();

			FileInputStream inStream = this.openFileInput("ivvy.txt");
			Log.i(TAG, readInStream(inStream));

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}
			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}
		return null;
	}
}