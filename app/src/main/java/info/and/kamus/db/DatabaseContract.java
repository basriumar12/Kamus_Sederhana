package info.and.kamus.db;

import android.provider.BaseColumns;

/**
 * Created by User on 29/08/2018.
 */

public class DatabaseContract {
    public static String ENGLISH_INDONESIA = "table_eng_ind";
    public static String INDONESIA_ENGLISH = "table_ind_eng";
    public static final class EnglishIndonesiaColumn implements BaseColumns {
        public static String DETAILS_ENG = "details_eng";
        public static String WORDS_ENG = "words_eng";
    }
    public static final class IndonesiaEnglishColumn implements BaseColumns{
        public static String WORDS_INDO = "words_indo";
        public static String DETAILS_INDO = "details_indo";
    }
}
