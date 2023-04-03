package com.kemia.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationBarView;
import com.kemia.myapplication.R;
import com.kemia.myapplication.fragment_tuychon.MoiFragment;
import com.kemia.myapplication.fragment_tuychon.NongFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TinTucFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TinTucFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NavigationBarView topNavigationView;

    public TinTucFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TinTucFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TinTucFragment newInstance(String param1, String param2) {
        TinTucFragment fragment = new TinTucFragment();
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
        View view = inflater.inflate(R.layout.fragment_tin_tuc, container, false);

        System.out.println("ca nsuic");
        topNavigationView = (NavigationBarView) view.findViewById(R.id.topNavigationView);

        topNavigationView.setOnItemSelectedListener(item -> {
            Fragment fm;
            switch (item.getItemId())
            {
                case R.id.nong:
                    fm = new NongFragment();
                    loadFragment(fm);
                    return true;
                case R.id.moinhat:
                    fm = new MoiFragment();
                    loadFragment(fm);
                    return true;
            }
            return true;
        });


        return view;
    }


    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getChildFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment_tintuc,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}