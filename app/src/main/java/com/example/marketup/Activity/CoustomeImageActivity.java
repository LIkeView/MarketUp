package com.example.marketup.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.marketup.R;

import static androidx.constraintlayout.solver.widgets.ConstraintWidget.INVISIBLE;
import static androidx.constraintlayout.solver.widgets.ConstraintWidget.VISIBLE;

public class CoustomeImageActivity extends AppCompatActivity implements View.OnClickListener{

    //    Buttons
    private RelativeLayout relativeLayoutLogo,relativeLayoutYourName,relativeLayoutFirmName,
            relativeLayoutYourEmail,relativeLayoutFirmEmail,relativeLayoutYourPhoneNumber,
            relativeLayoutFirmPhoneNumber ;
    private RelativeLayout relativeLayoutWebsite,relativeLayoutSocialIcon,relativeLayoutProductName,
            relativeLayoutProductType,relativeLayoutProductSize,relativeLayoutAdddress;
    private int LogoStatusFlag = 0;

    ImageView imageviewLogo;

    private RelativeLayout mainLayout;
    private TextView txtTitle,txtTitle2;
    private RelativeLayout relativeLayout;
    ImageView imgResultImage;
    Button btnConvertToImage;
    private int xDelta;
    private int yDelta;
    ImageView image1;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_coustome_image );

        relativeLayoutLogo = (RelativeLayout) findViewById(R.id.relativeLayoutLogo);
        relativeLayoutYourName = (RelativeLayout) findViewById(R.id.relativeLayoutYourName);
        relativeLayoutFirmName = (RelativeLayout) findViewById(R.id.relativeLayoutFirmName);
        relativeLayoutYourEmail = (RelativeLayout) findViewById(R.id.relativeLayoutYourEmail);
        relativeLayoutFirmEmail = (RelativeLayout) findViewById(R.id.relativeLayoutFirmEmail);
        relativeLayoutYourPhoneNumber = (RelativeLayout) findViewById(R.id.relativeLayoutYourPhoneNumber);
        relativeLayoutFirmPhoneNumber = (RelativeLayout) findViewById(R.id.relativeLayoutFirmPhoneNumber);
        relativeLayoutWebsite = (RelativeLayout) findViewById(R.id.relativeLayoutWebsite);
        relativeLayoutSocialIcon = (RelativeLayout) findViewById(R.id.relativeLayoutSocialIcon);
        relativeLayoutProductName = (RelativeLayout) findViewById(R.id.relativeLayoutProductName);
        relativeLayoutProductType = (RelativeLayout) findViewById(R.id.relativeLayoutProductType);
        relativeLayoutProductSize = (RelativeLayout) findViewById(R.id.relativeLayoutProductSize);
        relativeLayoutAdddress = (RelativeLayout) findViewById(R.id.relativeLayoutAdddress);
        imageviewLogo = (ImageView) findViewById( R.id.imageviewLogo );
        image1 = (ImageView) findViewById( R.id.image1 );
        relativeLayout = (RelativeLayout) findViewById( R.id.main );
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        txtTitle = (TextView) findViewById( R.id.txtTitle );
        txtTitle2 = (TextView) findViewById( R.id.txtTitle2 );
        imgResultImage = findViewById(R.id.imgResultImage);


        txtTitle2.setOnTouchListener( new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x -xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin =  xDelta-x;
                        layoutParams.bottomMargin = yDelta-y;
                        view.setLayoutParams(layoutParams);
                        break;
                }

                mainLayout.invalidate();
                return true;
            }
        } );

        txtTitle.setOnTouchListener( new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x -xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin =  xDelta-x;
                        layoutParams.bottomMargin = yDelta-y;
                        view.setLayoutParams(layoutParams);
                        break;
                }

                mainLayout.invalidate();
                return true;
            }
        } );
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageviewLogo.getLayoutParams();
        Log.d( "margin",""+layoutParams.topMargin );
        Log.d( "margin1",""+layoutParams.getMarginEnd() );
//        Log.d( "margin2",""+layoutParams.getMarginEnd() );


        imageviewLogo.setOnTouchListener( new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
//                Log.d( "check::1::",""+x );
//                Log.d( "check::2::",""+y );
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
//                        Log.d( "check::3::",""+x );
//                        Log.d( "check::4::",""+y );
//                        Log.d( "check::5::",""+xDelta );
//                        Log.d( "check::6::",""+yDelta );
                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x -xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin =  xDelta-x;
                        layoutParams.bottomMargin = yDelta-y;
                        Log.d( "check::7::",""+layoutParams.leftMargin );
                        Log.d( "check::8::",""+layoutParams.topMargin );
                        Log.d( "check::9::",""+layoutParams.rightMargin );
                        Log.d( "check::10::",""+layoutParams.bottomMargin );
                        view.setLayoutParams(layoutParams);
                        break;
                }

                mainLayout.invalidate();
                return true;
            }
        } );

        relativeLayoutLogo.setOnClickListener( this );
        relativeLayoutYourName.setOnClickListener( this );
        relativeLayoutFirmName.setOnClickListener( this );
        relativeLayoutYourEmail.setOnClickListener( this );
        relativeLayoutFirmEmail.setOnClickListener( this );
        relativeLayoutYourPhoneNumber.setOnClickListener( this );
        relativeLayoutFirmPhoneNumber.setOnClickListener( this );
        relativeLayoutWebsite.setOnClickListener( this );
        relativeLayoutSocialIcon.setOnClickListener( this );
        relativeLayoutProductName.setOnClickListener( this );
        relativeLayoutProductType.setOnClickListener( this );
        relativeLayoutProductSize.setOnClickListener( this );
        relativeLayoutAdddress.setOnClickListener( this );
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return super.onOptionsItemSelected( item );
            default:
                return super.onOptionsItemSelected( item );
        }
    }
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativeLayoutLogo:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutYourName:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutFirmName:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutYourEmail:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutYourPhoneNumber:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutFirmEmail:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutFirmPhoneNumber:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutWebsite:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutSocialIcon:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutProductName:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutProductType:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;
            case R.id.relativeLayoutProductSize:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;

            case R.id.relativeLayoutAdddress:
                if (LogoStatusFlag == VISIBLE){
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_white );
                    imageviewLogo.setVisibility( View.INVISIBLE );
                    LogoStatusFlag = INVISIBLE;
                }
                else if(LogoStatusFlag == INVISIBLE) {
                    relativeLayoutLogo.setBackgroundResource( R.drawable.check_box_blue );
                    imageviewLogo.setVisibility( View.VISIBLE );
                    LogoStatusFlag = VISIBLE;
                }
                break;


        }
    }

}
