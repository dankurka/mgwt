package de.kurka.mobile.contact.client.module;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Contact {
	private String firstName;
	private String lastName;

	private String id;

	public Contact() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static Contact fromJSON(String json) throws ParseException {
		JSONValue jsonParse = JSONParser.parseStrict(json);

		JSONObject jsonContact = jsonParse.isObject();
		if (jsonContact == null) {
			throw new ParseException();
		}

		Contact contact = new Contact();

		JSONString jsonFirstName = jsonContact.get("firstName").isString();
		String firstName = jsonFirstName != null ? jsonFirstName.stringValue() : "";
		contact.setFirstName(firstName);

		JSONString jsonId = jsonContact.get("id").isString();
		String id = jsonId != null ? jsonId.stringValue() : "";
		contact.setId(id);

		JSONString jsonLastName = jsonContact.get("lastName").isString();
		String lastName = jsonLastName != null ? jsonLastName.stringValue() : "";
		contact.setLastName(lastName);

		return contact;
	}

	public String toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstName", new JSONString(this.firstName != null ? this.firstName : ""));
		jsonObject.put("lastName", new JSONString(this.lastName != null ? this.lastName : ""));
		jsonObject.put("id", new JSONString(this.id != null ? this.id : ""));

		return jsonObject.toString();

	}
}
