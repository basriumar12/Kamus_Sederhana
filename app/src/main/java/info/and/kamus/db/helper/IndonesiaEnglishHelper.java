package info.and.kamus.db.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import info.and.kamus.db.DatabaseHelper;
import info.and.kamus.pojo.IndoModel;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static info.and.kamus.db.DatabaseContract.INDONESIA_ENGLISH;
import static info.and.kamus.db.DatabaseContract.IndonesiaEnglishColumn.DETAILS_INDO;
import static info.and.kamus.db.DatabaseContract.IndonesiaEnglishColumn.WORDS_INDO;


public class IndonesiaEnglishHelper {
    private Context ctx;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public IndonesiaEnglishHelper(Context ctx) {
        this.ctx = ctx;
    }
    public IndonesiaEnglishHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(ctx);
        database = databaseHelper.getWritableDatabase();
        return this;
    }
    public void closeIndo (){
        databaseHelper.close();
    }

    public ArrayList<IndoModel> getDataIndo(String word){
        Cursor cursor = database.query(INDONESIA_ENGLISH,
                null,
                WORDS_INDO+ " LIKE ?",
                new String[]{"%" + word + "%"},
                null, null,
                WORDS_INDO);
        cursor.moveToFirst();
        ArrayList<IndoModel> arrayList = new ArrayList<>();
        IndoModel indoModel;

        if(cursor.getCount()>0){
            do{
                indoModel = new IndoModel();
                indoModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indoModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORDS_INDO)));
                indoModel.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(DETAILS_INDO)));
                arrayList.add(indoModel);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public void beginTransactionIndo(){
        database.beginTransaction();
    }
    public void setTransactionSuccessIndo(){
        database.setTransactionSuccessful();
    }
    public void endTransactionIndo(){
        database.endTransaction();
    }


    public void insertTransactionIndo(IndoModel kamusIndoModel){
        String sql = "INSERT INTO " + INDONESIA_ENGLISH + " (" + WORDS_INDO + ", " + DETAILS_INDO+ ") VALUES (?, ?)";
        SQLiteStatement stamt = database.compileStatement(sql);
        stamt.bindString(1, kamusIndoModel.getWords());
        stamt.bindString(2, kamusIndoModel.getDetails());
        stamt.execute();
        stamt.clearBindings();
    }

}
