package org.ivvy.service;

import java.util.ArrayList;
import java.util.List;

import org.ivvy.model.Person;
import org.ivvy.sqlite.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonService {

	private DBOpenHelper dbOpenHelper = null;

	public PersonService(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
	}

	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		// db.execSQL("insert into person(name, phone) values('"
		// + person.getName() + "','" + person.getPhone() +
		// "')");//在某些情况下，也许会报错。比如名字是li'ming.
		db.execSQL("insert into person(name, phone) values(?, ?)",
				new Object[] { person.getName(), person.getPhone() });
		// db.close();//由于是自己使用Database，所以，可以不不用关闭。一般建议关闭。
	}

	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(
				"update person set name=?, phone=? where id=?",
				new Object[] { person.getName(), person.getPhone(),
						person.getId() });
		//
	}

	public Person find(int inputId) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person where id=?",
				new String[] { String.valueOf(inputId) });
		if (cursor.moveToFirst()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			cursor.close();
			return new Person(id, name, phone);
		}
		return null;

	}

	public void delete(int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from person where id=?", new Object[] { id });
	}

	public List<Person> getScrollData(int offset, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from person order by id asc limit ?, ?",
				new String[] { String.valueOf(offset),
						String.valueOf(maxResult) });
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(id, name, phone));
		}
		cursor.close();
		return persons;
	}

	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(1) from person", null);
		cursor.moveToFirst();
		return cursor.getLong(0);
	}

}
