package com.kemia.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kemia.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaNhanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaNhanFragment extends Fragment {

    Button btLS;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Switch aSwitch;

    public CaNhanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaNhanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaNhanFragment newInstance(String param1, String param2) {
        CaNhanFragment fragment = new CaNhanFragment();
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
        var view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        //btLS = view.findViewById(R.id.btlichsu);

        //btLS.setOnClickListener(v -> {
          //  var intent = new Intent(getActivity(), lichsu.class);
            //startActivity(intent);
        //});

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String about = getString(R.string.about);
        String description = about + "\n \nPhan Thiên Ân \n \nDương Toàn Trung";
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(description);
    }
}