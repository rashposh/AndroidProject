package com.kemia.myapplication.fragment_tuychon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.textview.MaterialTextView;
import com.kemia.myapplication.Fetch.Fetch;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsHandler;
import com.kemia.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NongFragment extends Fragment {

    LinearLayout layout;
    private ViewGroup container;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nong.
     */
    // TODO: Rename and change types and number of parameters
    public static NongFragment newInstance(String param1, String param2) {
        NongFragment fragment = new NongFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nong, container, false);

        layout = (LinearLayout) view.findViewById(R.id.nongLinear);
        this.container = container;
        var t = new Fetch();
        var a = new GoogleNewsHandler("a", this::addItem);

        t.execute(a);

        return view;
    }

    private void addItem(GoogleNews googleNews) {
        for (var item : googleNews.getItems()) {
            layout.addView(test(item.getTitle()));
        }

    }

    private View test(String text) {
        // inflate (create) another copy of our custom layout
        LayoutInflater newsInflater = getLayoutInflater();
        View myLayout = newsInflater.inflate(R.layout.new_item, container, false);

        for (View item : getAllChildren(myLayout)) {
            if (item instanceof MaterialTextView) {
                ((MaterialTextView) item).setText(text);
            }
        }

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
}