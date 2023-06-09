package com.kemia.myapplication.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kemia.myapplication.Adapter.LSAdapter;
import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView recyclerView;
    private Button delAllBtn;
    private List<GoogleNewsItem> items = new ArrayList<>();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        this.recyclerView = view.findViewById(R.id.lsView);
        this.delAllBtn = view.findViewById(R.id.lsDelBtn);

        setupView();

        delAllBtn.setOnClickListener(v -> {
            Database db = new Database();
            db.deleteAll(v.getContext());
            setupView();
        });

        return view;
        // Inflate the layout for this fragment
    }

    private void setupView(){//tạo lại
        Database db = new Database();
        var ggN = db.readFromDatabase(getActivity().getApplicationContext());

        items.clear();
        createView(ggN);
    }
    private void createView(GoogleNews googleNews) {//tạo bảng
        items.addAll(googleNews.getItems());
        var adapter = new LSAdapter(items);
        recyclerView.setAdapter(adapter);//recyculerView sắp theo adapter đó để hiển thị lên
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));//set kiểu cho recyculerView đó
    }

}