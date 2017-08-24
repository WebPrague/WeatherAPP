package com.example.admin.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.CompoundButton;

import com.example.admin.weatherapp.UI.BaseActivity;

/**
 * 
 * @author dz
 *
 */
public class SpUtil {

	private static final String sharedPreferencesInfo = "shareInfo";

	private static Context mContext;

	private static SharedPreferences saveInfo;

	private static Editor saveEditor;

	private static SpUtil sharedPreferencesUtil = new SpUtil();
    
    private SpUtil() {
	}

	public static SpUtil getinstance(BaseActivity context) {
        if (null != context) {
            mContext = context.getApplicationContext();
        }
        if(saveInfo==null){
			synchronized (SpUtil.class){
				if (saveInfo == null && mContext != null) {
					saveInfo = mContext.getSharedPreferences(sharedPreferencesInfo, Context.MODE_PRIVATE);
					saveEditor = saveInfo.edit();
				}
			}
		}

		return sharedPreferencesUtil;
	}

	public boolean isContainKey(String key) {
		return saveInfo.contains(key);
	}

	public boolean clearItem(String key) {
		saveEditor.remove(key);
		return saveEditor.commit();
	}

	public String getString(String key) {
		return saveInfo.getString(key, "");
	}

	public String getString(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	public boolean setString(String key, String value) {
		saveEditor.putString(key, value);
		return saveEditor.commit();
	}

	public int getInt(String key) {
		return saveInfo.getInt(key, 0);
	}

	public int getInt(String key, int defaultValue) {
		return saveInfo.getInt(key, defaultValue);
	}

	public boolean setInt(String key, int value) {
		saveEditor.putInt(key, value);
		return saveEditor.commit();
	}

	public long getLong(String key, long defValue) {
		return saveInfo.getLong(key, defValue);
	}

	public boolean setLong(String key, long value) {
		saveEditor.putLong(key, value);
		return saveEditor.commit();
	}

	public boolean setBoolean(String key, boolean value) {
		saveEditor.putBoolean(key, value);
		return saveEditor.commit();
	}

	public boolean getBoolean(String key) {
		return saveInfo.getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}

	
	/**
	 * 白天/夜间模式
	 * 0:白天模式
	 * 1:夜间模式
	 */
	public final String READDER_MODE ="KEY_READER_MODE";

	public int getReaderModeCode() {
		return saveInfo.getInt(READDER_MODE, 0);
	}

	public void setReaderModeCode(int value) {
        saveEditor.putInt(READDER_MODE, value);
		saveEditor.commit();
	}

	
}
