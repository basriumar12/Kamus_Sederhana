package info.and.kamus.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import info.and.kamus.pojo.IndoModel;
import info.and.kamus.pojo.IngModel;
import info.and.kamus.ui.activity.DetailsActivity;
import info.blogbasbas.kamus.R;

/**
 * Created by User on 29/08/2018.
 */

public class IndoAdapter extends RecyclerView.Adapter<IndoAdapter.MyHolder> {

    private ArrayList<IndoModel> data = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;
    @Override
    public IndoAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_word, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndoAdapter.MyHolder myHolder, int i) {
        final String wordsData = data.get(i).getWords();
        final String detailsWords = data.get(i).getDetails();
        myHolder.tvWOrd.setText(wordsData);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendData = new Intent(context, DetailsActivity.class);
                sendData.putExtra(DetailsActivity.EXTRA_WORDS, wordsData);
                sendData.putExtra(DetailsActivity.EXTRA_DETAILS, detailsWords);
                context.startActivity(sendData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void addItemData (ArrayList<IndoModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public IndoAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvWOrd;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvWOrd = (TextView) itemView.findViewById(R.id.tv_words);
        }
    }
}
