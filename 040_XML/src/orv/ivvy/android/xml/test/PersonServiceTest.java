package orv.ivvy.android.xml.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import orv.ivvy.android.xml.model.Person;
import orv.ivvy.android.xml.service.PersonService;

import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "org.ivvy";

	public void testGetPersons() throws Throwable {
		InputStream xml = this.getClass().getClassLoader()
				.getResourceAsStream("person.xml");
		List<Person> persons = PersonService.getPersons(xml);

		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}

	}

	public void testSave() throws Exception {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(20, "laozhang", (short) 18));
		persons.add(new Person(25, "laoli", (short) 13));
		persons.add(new Person(26, "liming", (short) 30));
		persons.add(new Person(27, "fanfan", (short) 1098));

		File file = new File(getContext().getFilesDir(), "ivvy.xml");

		FileOutputStream outStream = new FileOutputStream(file);
		PersonService.save(persons, outStream);
	}
}
