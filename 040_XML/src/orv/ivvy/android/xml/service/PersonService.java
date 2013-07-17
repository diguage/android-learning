package orv.ivvy.android.xml.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import orv.ivvy.android.xml.model.Person;

public class PersonService {
	/**
	 * 获取人员信息
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static List<Person> getPersons(InputStream xml) throws Exception {
		List<Person> persons = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(xml, "UTF-8");
		int event = parser.getEventType();
		Person person = null;
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();
				break;

			case XmlPullParser.START_TAG:
				if ("person".equals(parser.getName())) {
					Integer id = Integer.parseInt(parser.getAttributeValue(0));
					person = new Person();
					person.setId(id);
				} else if ("name".equals(parser.getName())) {
					person.setName(parser.nextText());
				} else if ("age".equals(parser.getName())) {
					person.setAge(Short.parseShort(parser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("person".equals(parser.getName())) {
					persons.add(person);
					person = null;
				}
				break;
			/*
			 * 在Pull解析器中有几种节点类型 文档开始节点 标签开始节点 文本节点（空白节点属于文本节点） 标签结束节点 文档结束节点
			 */
			default:
				break;
			}

			event = parser.next();
		}
		return persons;
	}

	/**
	 * @param persons
	 * @param out
	 * @throws Exception
	 */
	public static void save(List<Person> persons, OutputStream out)
			throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(out, "UTF-8");
		serializer.startDocument("UTF-8", true);
		serializer.startTag(null, "persons");
		for (Person person : persons) {
			serializer.startTag(null, "person");
			serializer.attribute(null, "id", person.getId().toString());

			serializer.startTag(null, "name");
			serializer.text(person.getName());
			serializer.endTag(null, "name");

			serializer.startTag(null, "age");
			serializer.text(person.getAge().toString());
			serializer.endTag(null, "age");

			serializer.endTag(null, "person");
		}
		serializer.endTag(null, "persons");
		serializer.endDocument();

		out.flush();
		out.close();

	}

}
