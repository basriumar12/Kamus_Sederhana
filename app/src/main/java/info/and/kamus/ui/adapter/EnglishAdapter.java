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

import info.and.kamus.pojo.IngModel;
import info.and.kamus.ui.activity.DetailsActivity;
import info.blogbasbas.kamus.R;


public class EnglishAdapter extends RecyclerView.Adapter<EnglishAdapter.MyHolder> {

    private ArrayList<IngModel> data = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;
    @Override
    public EnglishAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_word, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishAdapter.MyHolder myHolder, int i) {
        final String words = data.get(i).getWords();
        final String details_words = data.get(i).getDetails();

        myHolder.tvWOrd.setText(words);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendData = new Intent(context, DetailsActivity.class);
                sendData.putExtra(DetailsActivity.EXTRA_WORDS, words);
                sendData.putExtra(DetailsActivity.EXTRA_DETAILS, details_words);
                context.startActivity(sendData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void addItemData (ArrayList<IngModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public EnglishAdapter(Context context) {
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
