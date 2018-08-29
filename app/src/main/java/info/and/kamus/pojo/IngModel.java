package info.and.kamus.pojo;

import android.os.Parcel;
import android.os.Parcelable;



public class IngModel implements Parcelable {
    private int id;
    private String words;
    private String details;

    public IngModel() {

    }

    public IngModel(String words, String details) {
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

    public static Creator<IngModel> getCREATOR() {
        return CREATOR;
    }

    protected IngModel(Parcel in) {
        id = in.readInt();
        words = in.readString();
        details = in.readString();
    }

    public static final Creator<IngModel> CREATOR = new Creator<IngModel>() {
        @Override
        public IngModel createFromParcel(Parcel in) {
            return new IngModel(in);
        }

        @Override
        public IngModel[] newArray(int size) {
            return new IngModel[size];
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
