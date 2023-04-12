package com.kemia.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.fragment.CaNhanFragment;
import com.kemia.myapplication.fragment.TinTucFragment;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_ACTION_BAR));
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Fragment fmnew = new TinTucFragment();
        loadFragment(fmnew);
        mnBottom = findViewById(R.id.bottomNavigationView);
        //

        // Load lên Fragment
        mnBottom.setOnItemSelectedListener(getListener());

//        Database db = new Database();
//        db.addTestData();
//        db.readFromDatabase(getApplicationContext());
//        db.dropTable(getApplicationContext());
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
    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}