package info.and.kamus.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 29/08/2018.
 */

public class IndoModel implements Parcelable {

    private int id;
    private String words;
    private String details;

    public IndoModel(){

    }
    public IndoModel(String words, String details) {
        this.words = words;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static Creator<IndoModel> getCREATOR() {
        return CREATOR;
    }

    protected IndoModel(Parcel in) {
        id = in.readInt();
        words = in.readString();
        details = in.readString();
    }

    public static final Creator<IndoModel> CREATOR = new Creator<IndoModel>() {
        @Override
        public IndoModel createFromParcel(Parcel in) {
            return new IndoModel(in);
        }

        @Override
        public IndoModel[] newArray(int size) {
            return new IndoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(words);
        parcel.writeString(details);
    }
}
