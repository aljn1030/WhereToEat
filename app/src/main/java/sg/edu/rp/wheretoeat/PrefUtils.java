package sg.edu.rp.wheretoeat;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

	private final static String PREF_NAME = "config";
	private final static String PREF_LANG = "lang";

	public static String getLang(Context context) {

		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

		return pref.getString(PREF_LANG, "");
	}

	public static void setLang(Context context, String lang) {

		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

		pref.edit().putString(PREF_LANG, lang).commit();
	}
}
