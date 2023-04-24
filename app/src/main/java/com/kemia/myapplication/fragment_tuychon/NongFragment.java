package com.kemia.myapplication.fragment_tuychon;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.kemia.myapplication.Adapter.NewItemAdapter;
import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.Fetch.Fetch;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsHandler;
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;
import com.kemia.myapplication.webview;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NongFragment extends Fragment {

    RecyclerView recyclerView;
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

    public NongFragment(SearchView searchView) {
        searchView.setOnClickListener(view -> {
            System.out.println("VL");
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                resetItem();
                getItemFromInternet(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        }) ;
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

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nong, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvNewItem);



        this.container = container;
        getItemFromInternet("");// nếu search trống thì nó lấy trang mới nhất

        var adapter = new NewItemAdapter(items);//tạo adapter cho recyclerView
        recyclerView.setAdapter(adapter);//gán adapter đó cho recyclerView đó
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    private ArrayList<GoogleNewsItem> items = new ArrayList<>();// itiem là mảng động chứa nhiều dữ liệu của nhiều bài báo

    private void getItemFromInternet(String url) {
        var fetch = new Fetch(url);//tạo đối tượng fetch và truyền địa chỉ nó vào
        var handler = new GoogleNewsHandler(url, this::createView);
        fetch.execute(handler);
    }

    private void resetItem() {
        items.clear();
        recyclerView.removeAllViews();
    }

    private void createView(GoogleNews googleNews) {
        items.addAll(googleNews.getItems());// thêm toán bộ dữ liệu vào itiem đó
        var adapter = new NewItemAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}