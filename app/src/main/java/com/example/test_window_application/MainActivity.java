
package com.example.test_window_application;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_window_application.login.LoginFragment;

public class MainActivity extends AppCompatActivity {
    public MainActivity(){
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, LoginFragment.class, null)
                    .commit();
        }
    }
}