package org.ivvy.test;

import java.util.List;

import org.ivvy.model.Person;
import org.ivvy.service.PersonService;
import org.ivvy.sqlite.DBOpenHelper;

import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "org.ivvy";

	public void testCreateDB() throws Throwable {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();// <°ü>/database
	}

	public void testSave() throws Throwable {
		PersonService service = new PersonService(getContext());
		Person person = new Person("laoli", "123456");
		service.save(person);
	}

	public void testSave2() throws Throwable {
		PersonService service = new PersonService(getContext());
		for (int i = 0; i < 20; i++) {
			Person person = new Person("ivvy_" + i, "123456-" + i);
			service.save(person);
		}
	}

	public void testFind() throws Throwable {
		PersonService service = new PersonService(getContext());
		Person person = service.find(1);
		Log.i(TAG, person.toString());
	}

	public void testUpdate() throws Throwable {
		PersonService service = new PersonService(getContext());
		Person person = service.find(1);
		person.setName("org.ivvy");
		service.update(person);
		person = null;
		person = service.find(1);
		Log.i(TAG, person.toString());
	}

	public void testCount() throws Throwable {
		PersonService service = new PersonService(getContext());
		Log.i(TAG, String.valueOf(service.getCount()));
	}

	public void testScrollData() throws Throwable {
		PersonService service = new PersonService(getContext());
		List<Person> persons = service.getScrollData(0, 6);
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}

	public void testDelete() throws Exception {
		PersonService service = new PersonService(getContext());
		service.delete(2);
	}

}
