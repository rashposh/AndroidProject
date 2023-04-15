package com.kemia.myapplication.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;
import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;
import com.kemia.myapplication.webview;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout layout;
    private ViewGroup container;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void addItem(GoogleNews googleNews) {
        for (GoogleNewsItem item : googleNews.getItems()) {
            layout.addView(createNewCard(item));
        }
    }
    private View createNewCard(GoogleNewsItem googleNewsItem) {
        // inflate (create) another copy of our custom layout
        LayoutInflater newsInflater = getLayoutInflater();
        View myLayout = newsInflater.inflate(R.layout.testhistory, container, false);

        for (View item : getAllChildren(myLayout)) {
            if (item instanceof MaterialTextView) {
                ((MaterialTextView) item).setText(googleNewsItem.getTitle());
            }
            if (item instanceof AppCompatImageView) {
                if (!Objects.isNull(googleNewsItem.getImgBitMap()))
                    ((AppCompatImageView) item).setImageBitmap(googleNewsItem.getImgBitMap());
            }
        }
        myLayout.setOnClickListener(view -> {
            var intent = new Intent(getActivity(), webview.class);
            intent.putExtra("url", googleNewsItem.getLink());
            startActivity(intent);

            Database db = new Database();
            db.addNewsItem(googleNewsItem, getActivity().getApplicationContext());

        });

        return myLayout;
    }
    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            //Do not add any parents, just add child elements
            result.addAll(getAllChildren(child));
        }
        return result;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        layout = rootView.findViewById(R.id.lsLinear);
        this.container = rootView.findViewById(R.id.lsView);

        Database db = new Database();
        addItem(db.readFromDatabase(getActivity().getApplicationContext()));

        return rootView;
        // Inflate the layout for this fragment
    }
}