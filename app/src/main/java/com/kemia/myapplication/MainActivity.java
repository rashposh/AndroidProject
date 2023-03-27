//package com.kemia.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.SearchView;
//
//public class MainActivity extends AppCompatActivity {
//
//    Button more;
////    SearchView search;
//    Button bao1;
//    Button trend;
//    Button like;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        setContentView(R.layout.activity_main);
//        more = findViewById(R.id.more);
////        search = findViewById(R.id.search);
//
//        bao1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, BaoMoiActivity.class));
//            }
//        }
//        );
//        /**video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, VideoActivity.class));
//            }
//        }
//        )*/
//        trend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {setContentView(R.layout.baomoi);}
//        }
//        );
//        like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {setContentView(R.layout.baomoi);}
//        }
//        );
//    }
//}




package com.kemia.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.kemia.myapplication.fragment.TinTucFragment;
import com.kemia.myapplication.fragment.CaNhanFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE));
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Fragment fmnew = new TinTucFragment();
        loadFragment(fmnew);
        mnBottom = findViewById(R.id.bottomNavigationView);
        //

        // Load lên Fragment
        mnBottom.setOnItemSelectedListener(getListener());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return true;
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener()
    {
        return new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment fmNew;
                switch (item.getItemId())
                {
                    case R.id.self:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new TinTucFragment();
                        loadFragment(fmNew);
                        return true;

                    case R.id.cc:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new CaNhanFragment();
                        loadFragment(fmNew);
                        return true;
                }
                return true;
            }
        };
    }

    // Hàm load fragment
    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}







