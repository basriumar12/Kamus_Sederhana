package info.and.kamus.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import info.and.kamus.db.helper.EnglishIndonesiaHelper;
import info.and.kamus.pojo.IngModel;
import info.and.kamus.ui.adapter.EnglishAdapter;
import info.blogbasbas.kamus.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment {


    public EnglishFragment() {
        // Required empty public constructor
    }

    RecyclerView rvEnglish ;
    SearchView searchView;
    EnglishIndonesiaHelper englishIndonesiaHelper;
    EnglishAdapter englishAdapter;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english, container, false);
        rvEnglish = (RecyclerView)view.findViewById(R.id.rv_english_indonesia);
        searchView = (SearchView) view.findViewById(R.id.search_word_english_indonesia);
        rootView = (LinearLayout) view.findViewById(R.id.root_layout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                englishIndonesiaHelper = new EnglishIndonesiaHelper(getContext());
                englishAdapter = new EnglishAdapter(getContext());
                rvEnglish.setLayoutManager(new LinearLayoutManager(getContext()));
                rvEnglish.setAdapter(englishAdapter);
                englishIndonesiaHelper.open();
                ArrayList<IngModel> ingModels = englishIndonesiaHelper.getDataEnglish(newText);
                englishIndonesiaHelper.close();
                englishAdapter.addItemData(ingModels);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        searchView.setQuery("",false);
        rootView.requestFocus();
    }
}
