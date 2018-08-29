package info.and.kamus.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import info.blogbasbas.kamus.R;

/**
 * Created by User on 29/08/2018.
 */

public class AppPref {
    SharedPreferences prefs;
    Context context;

    public AppPref(Context ctx){
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        this.context = ctx;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.app_first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.app_first_run);
        return prefs.getBoolean(key, true);
    }
}
