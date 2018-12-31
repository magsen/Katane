package gui;

import java.util.Locale;//Representing specific geographic, political, and cultural areas
import java.util.MissingResourceException;//Signals that a resource is missing.
import java.util.ResourceBundle;//This class allows you to easily write programs that are localized or translated into different language developments.

public class Messages {
	private static final String BUNDLE_NAME = "gui.messages";

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(
			"de", "DE"));
	//This method gets the resource bundle using the specified base name and locale and the caller's class loader.

	private Messages() {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(
				"de", "DE"));
	}

	public static void initDE() {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(
				"de", "DE"));
	}

	public static void initEN() {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(
				"en", "US"));
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
