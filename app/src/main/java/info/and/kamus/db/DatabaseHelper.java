package info.and.kamus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static info.and.kamus.db.DatabaseContract.EnglishIndonesiaColumn.DETAILS_ENG;
import static info.and.kamus.db.DatabaseContract.EnglishIndonesiaColumn.WORDS_ENG;
import static info.and.kamus.db.DatabaseContract.IndonesiaEnglishColumn.DETAILS_INDO;
import static info.and.kamus.db.DatabaseContract.IndonesiaEnglishColumn.WORDS_INDO;
import static info.and.kamus.db.DatabaseContract.ENGLISH_INDONESIA;
import static info.and.kamus.db.DatabaseContract.INDONESIA_ENGLISH;

/**
 * Created by User on 29/08/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "kamus.db";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENGLISH_INDONESIA =
            "CREATE TABLE " + ENGLISH_INDONESIA +
                    " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WORDS_ENG + " TEXT NOT NULL, " + DETAILS_ENG +
                    " TEXT NOT NULL);";

    public static String CREATE_TABLE_INDONESIA_ENGLISH =
            "CREATE TABLE " + INDONESIA_ENGLISH +
                    " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + WORDS_INDO + " TEXT NOT NULL, " + DETAILS_INDO +
                    " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH_INDONESIA);
        db.execSQL(CREATE_TABLE_INDONESIA_ENGLISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INDONESIA_ENGLISH);
        db.execSQL("DROP TABLE IF EXISTS " + ENGLISH_INDONESIA);

        onCreate(db);
    }
}
