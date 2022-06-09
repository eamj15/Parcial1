package com.example.parcial2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.parcial2.R;

public class SIU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_i_u);

        final WebView wb = findViewById(R.id.webview);

        wb.loadUrl("https://utp.ac.pa");
    }
}
