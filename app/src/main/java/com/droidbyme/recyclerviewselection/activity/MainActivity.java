package com.droidbyme.recyclerviewselection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.droidbyme.recyclerviewselection.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btnSingle;
    private AppCompatButton btnMultiple;
    private AppCompatButton btnSwipe;
    private AppCompatButton btnNormal;
    private AppCompatButton btnCardView;
    private AppCompatButton btnMultipleView;
    private AppCompatButton btnMultipleFrag;
    private AppCompatButton btnOTP;
    private AppCompatButton sendSms;
    private AppCompatButton tenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnCardView.setOnClickListener(this);
        btnSingle.setOnClickListener(this);
        btnMultiple.setOnClickListener(this);
        btnSwipe.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnMultipleView.setOnClickListener(this);
        btnMultipleFrag.setOnClickListener(this);
        btnOTP.setOnClickListener(this);
//        sendSms.setOnClickListener(this);
        tenses.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSingle:
                startActivity(new Intent(MainActivity.this, SingleSelectionActivity.class));
                break;

            case R.id.btnMultiple:
                startActivity(new Intent(MainActivity.this, MultipleSelectionActivity.class));
                break;

            case R.id.btnSwipe:
                startActivity(new Intent(MainActivity.this, SwipeSelectionActivity.class));
                break;

            case R.id.btnNormal:
                startActivity(new Intent(MainActivity.this, NormalRecyclerViewActivity.class));
                break;

            case R.id.btnCardView:
                startActivity(new Intent(MainActivity.this, CardRecyclerViewActivity.class));
                break;

            case R.id.btnMultipleView:
                startActivity(new Intent(MainActivity.this, MultipleViewTypeActivity.class));
                break;

            case R.id.btnTestFragment :
                startActivity(new Intent(MainActivity.this, MultipleFragments.class));
                break;

            case R.id.btnTestCode :
                startActivity(new Intent(MainActivity.this, OtpView.class));
                break;

            case R.id.btnSend :
                startActivity(new Intent(MainActivity.this, SendSms.class));
                break;

            case R.id.btnTenses :
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
                break;
        }
    }

    private void initView() {
        btnNormal = (AppCompatButton) findViewById(R.id.btnNormal);
        btnMultiple = (AppCompatButton) findViewById(R.id.btnMultiple);
        btnSingle = (AppCompatButton) findViewById(R.id.btnSingle);
        btnSwipe = (AppCompatButton) findViewById(R.id.btnSwipe);
        btnCardView = (AppCompatButton) findViewById(R.id.btnCardView);
        btnMultipleView = (AppCompatButton) findViewById(R.id.btnMultipleView);
        btnMultipleFrag = (AppCompatButton) findViewById(R.id.btnTestFragment);
        btnOTP = (AppCompatButton) findViewById(R.id.btnTestCode);
//        sendSms = (AppCompatButton) findViewById(R.id.btnSend);
        tenses = (AppCompatButton) findViewById(R.id.btnTenses);
    }
}