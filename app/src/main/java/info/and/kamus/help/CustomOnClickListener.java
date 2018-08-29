package info.and.kamus.help;

import android.view.View;

/**
 * Created by User on 29/08/2018.
 */

public class CustomOnClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallBack onItemClickCallBack;

    @Override
    public void onClick(View view) {

    }
    private interface OnItemClickCallBack {
        void onItemClicked(View view, int position);
    }

    public CustomOnClickListener(int position, OnItemClickCallBack onItemClickCallBack) {
        this.position = position;
        this.onItemClickCallBack = onItemClickCallBack;
    }
}
