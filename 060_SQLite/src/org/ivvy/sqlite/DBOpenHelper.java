package org.ivvy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "ivvy.db", null, 2);//�������������α깤�������û���Զ���ľʹ�Ϊnull�����ĸ������ǰ汾�ţ������趨Ϊ0.
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
