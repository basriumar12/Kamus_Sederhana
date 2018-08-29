package info.and.kamus.ui.activity;

import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import info.blogbasbas.kamus.R;

public class DetailsActivity extends AppCompatActivity {

    public static String EXTRA_WORDS = "ex";
    public static String EXTRA_DETAILS="ed";
    private TextView tvWord,tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        tvDetail = (TextView) findViewById(R.id.tv_details_detail_activity);
        tvWord = (TextView) findViewById(R.id.tv_words_detail);

        String wordData = getIntent().getStringExtra(EXTRA_WORDS);
        String detailWords = getIntent().getStringExtra(EXTRA_DETAILS);

        Log.e("Tag","data intent :"+wordData + " "+detailWords);
        tvWord.setText(""+wordData);
        tvDetail.setText(""+detailWords);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if(i == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }
}
