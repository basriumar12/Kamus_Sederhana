package info.and.kamus.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;

import info.and.kamus.db.DatabaseHelper;
import info.and.kamus.pojo.IndoModel;
import info.and.kamus.pojo.IngModel;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static info.and.kamus.db.DatabaseContract.ENGLISH_INDONESIA;
import static info.and.kamus.db.DatabaseContract.EnglishIndonesiaColumn.DETAILS_ENG;
import static info.and.kamus.db.DatabaseContract.EnglishIndonesiaColumn.WORDS_ENG;
import static info.and.kamus.db.DatabaseContract.INDONESIA_ENGLISH;
import static info.and.kamus.db.DatabaseContract.IndonesiaEnglishColumn.DETAILS_INDO;
import static info.and.kamus.db.DatabaseContract.IndonesiaEnglishColumn.WORDS_INDO;

/**
 * Created by User on 29/08/2018.
 */

public class EnglishIndonesiaHelper {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    public EnglishIndonesiaHelper(Context context) {
        this.context = context;
    }

    public EnglishIndonesiaHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<IngModel> getDataEnglish(String word){
        Cursor cursor = database.query(ENGLISH_INDONESIA,null, WORDS_ENG + " LIKE ?", new String[]{"%" + word + "%"},null, null,WORDS_ENG);
        cursor.moveToFirst();
        ArrayList<IngModel> arrayList = new ArrayList<>();
        IngModel kamusModel;
        if(cursor.getCount()>0){
            do{
                kamusModel = new IngModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                kamusModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORDS_ENG)));
                kamusModel.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(DETAILS_ENG)));

                arrayList.add(kamusModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public void beginTransaction(){
        database.beginTransaction();
    }
    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }
    public void endTransaction(){
        database.endTransaction();
    }


    public void insertTransaction(IngModel kamusModel){
        String sql = "INSERT INTO " + ENGLISH_INDONESIA + " (" + WORDS_ENG + ", " + DETAILS_ENG + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWords());
        stmt.bindString(2, kamusModel.getDetails());
        stmt.execute();
        stmt.clearBindings();
    }


}
