package com.example.marketup.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.marketup.ImageViewActivity;
import com.example.marketup.Intro.IntroActivity;
import com.example.marketup.R;

public class SignUpWithMobile extends AppCompatActivity implements View.OnClickListener {

    private PinView pinView;
    private Button next;
    private TextView topText,textU;
    private EditText  userPhone;
    private ConstraintLayout first, second;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);*/
        setContentView(R.layout.activity_sign_up_with_mobile);

        topText = findViewById(R.id.topText);
        pinView = findViewById(R.id.pinView);
        next = findViewById(R.id.button);
//        userName = findViewById(R.id.username);
        userPhone = findViewById(R.id.userPhone);
        first = findViewById(R.id.first_step);
        second = findViewById(R.id.secondStep);
        textU = findViewById(R.id.textView_noti);
        first.setVisibility( View.VISIBLE);

        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (next.getText().equals("Let's go!")) {
            String phone = userPhone.getText().toString();

//            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
                next.setText("Verify");
                first.setVisibility(View.GONE);
                second.setVisibility(View.VISIBLE);
                topText.setText("I Still don't trust you.\nTell me something that only two of us know.");
//            } else {
//                Toast.makeText(SignUpWithMobile.this, "Please enter the details", Toast.LENGTH_SHORT).show();
//            }
        } else if (next.getText().equals("Verify")) {
            String OTP = pinView.getText().toString();
            if (OTP.equals("3456")) {
                pinView.setLineColor( Color.GREEN);
                textU.setText("OTP Verified");
                textU.setTextColor(Color.GREEN);
                next.setText("Next");
                Intent intent = new Intent( SignUpWithMobile.this , IntroActivity.class );
                startActivity( intent );
            } else {
                pinView.setLineColor(Color.RED);
                textU.setText("Incorrect OTP");
                textU.setTextColor(Color.RED);
            }
        }

    }
}