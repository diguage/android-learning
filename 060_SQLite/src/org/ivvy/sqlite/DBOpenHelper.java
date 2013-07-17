package org.ivvy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "ivvy.db", null, 2);//第三个参数是游标工厂，如果没有自定义的就传为null，第四个参数是版本号，不能设定为0.
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person(id integer primary key autoincrement, name varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("alter table person add phone varchar(12) null");
	}

}
