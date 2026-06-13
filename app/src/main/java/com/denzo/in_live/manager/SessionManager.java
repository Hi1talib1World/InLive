package com.denzo.in_live.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "InLivePrefs";
    private static final String KEY_IS_FIRST_RUN = "is_first_run_completed";
    
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private final Context _context;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setFirstRunCompleted(boolean isCompleted) {
        editor.putBoolean(KEY_IS_FIRST_RUN, isCompleted);
        editor.apply();
    }

    public boolean isFirstRunCompleted() {
        return pref.getBoolean(KEY_IS_FIRST_RUN, false);
    }
}
