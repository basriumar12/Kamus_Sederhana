package info.and.kamus.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import info.and.kamus.db.helper.EnglishIndonesiaHelper;
import info.and.kamus.db.helper.IndonesiaEnglishHelper;
import info.and.kamus.pojo.IndoModel;
import info.and.kamus.pojo.IngModel;
import info.and.kamus.pref.AppPref;
import info.blogbasbas.kamus.R;

public class SplachScreenActivity extends AppCompatActivity {

    final String TAG = LoadDataToDatabase.class.getSimpleName();
    EnglishIndonesiaHelper englishIndonesiaHelper;
    IndonesiaEnglishHelper indonesiaEnglishHelper;
    AppPref appPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        //load data asyntask
        new LoadDataToDatabase().execute();
    }

    public class LoadDataToDatabase extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            englishIndonesiaHelper = new EnglishIndonesiaHelper(SplachScreenActivity.this);
            indonesiaEnglishHelper = new IndonesiaEnglishHelper(SplachScreenActivity.this);
            appPreferences = new AppPref(SplachScreenActivity.this);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreferences.getFirstRun();
            if(firstRun){
                ArrayList<IngModel> kamusModels = preLoadRaw();
                ArrayList<IndoModel> kamusIndoModels = preLoadIndoRaw();

                englishIndonesiaHelper.open();

                englishIndonesiaHelper.beginTransaction();

                try {
                    for (IngModel model: kamusModels){
                        englishIndonesiaHelper.insertTransaction(model);
                    }
                    englishIndonesiaHelper.setTransactionSuccess();
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: Exception");
                }
                englishIndonesiaHelper.endTransaction();
                englishIndonesiaHelper.close();
                indonesiaEnglishHelper.open();
                indonesiaEnglishHelper.beginTransactionIndo();

                try {
                    for(IndoModel indoModel: kamusIndoModels){
                        indonesiaEnglishHelper.insertTransactionIndo(indoModel);
                    }
                    indonesiaEnglishHelper.setTransactionSuccessIndo();
                }catch (Exception e){
                    Log.e(TAG, "doInBackground: Exception");
                }

                indonesiaEnglishHelper.endTransactionIndo();

                indonesiaEnglishHelper.closeIndo();

                appPreferences.setFirstRun(false);
            }else {
                try {
                    synchronized (this){
                        this.wait(2000);
                    }
                }catch (Exception e){
                    Log.e("Tag","Error :"+e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(SplachScreenActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private ArrayList<IngModel> preLoadRaw() {
        ArrayList<IngModel> kamusModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do{
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                IngModel kamusModel;
                kamusModel = new IngModel(splitstr[0], splitstr[1]);
                kamusModels.add(kamusModel);
                count++;
            }while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Tag","Error :"+e.getMessage());

        }
        return kamusModels;
    }

    private ArrayList<IndoModel> preLoadIndoRaw() {
        ArrayList<IndoModel> kamusIndoModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do{
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                IndoModel kamusIndoModel;
                kamusIndoModel = new IndoModel(splitstr[0], splitstr[1]);
                kamusIndoModels.add(kamusIndoModel);
                count++;
            }while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusIndoModels;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
