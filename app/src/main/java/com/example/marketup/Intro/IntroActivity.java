package com.example.marketup.Intro;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.marketup.MainNavActivity;
import com.example.marketup.R;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    EditText editTextId;
    Animation btnAnim ;
    TextView tvSkip;
    CardView card_intro;
    EditText editTextYourName ,editTextFirmName, editTextFirmMobileNumber;
    EditText edtiTextYourEmail,edtiTextFirmEmail,editTextWebsite,editTextAddress;
    EditText edtiTextProduct,edtiTextProductType,editTextProductSize;
    LinearLayout linearLayoutIcon;
    LinearLayout liniarlayoutFirstPage,liniarlayoutSecondPage,lastPage,linearLayoutPrivew;
    TextView txtViewYourEmail,txtViewFirmEmai;
    ImageView chooseLogo,logoRight;
    Uri uri;
    Bitmap bitmap;

    private static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen

        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when this activity is about to be launch we need to check if its openened before or not

        if (restorePrefData()) {

            Intent mainActivity = new Intent(getApplicationContext(),MainNavActivity.class );
            startActivity(mainActivity);
            finish();


        }

        setContentView(R.layout.activity_intro);

        // hide the action bar

        getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        editTextId = findViewById( R.id.editTextId );
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);
        card_intro = findViewById( R.id.card_intro );
        chooseLogo = findViewById( R.id.chooseLogo );
        editTextYourName = findViewById( R.id.editTextYourName );
        editTextFirmName = findViewById( R.id.editTextFirmName );
        editTextFirmMobileNumber = findViewById( R.id.editTextFirmMobileNumber );
        edtiTextYourEmail = findViewById( R.id.edtiTextYourEmail );
        edtiTextFirmEmail = findViewById( R.id.edtiTextFirmEmail );
        editTextWebsite = findViewById( R.id.editTextWebsite );
        editTextAddress = findViewById( R.id.editTextAddress );
        edtiTextProduct = findViewById( R.id.edtiTextProduct );
        edtiTextProductType = findViewById( R.id.edtiTextProductType );
        editTextProductSize = findViewById( R.id.editTextProductSize );
        linearLayoutIcon = findViewById( R.id.linearLayoutIcon );
        liniarlayoutFirstPage = findViewById( R.id.liniarlayoutFirstPage );
        liniarlayoutSecondPage = findViewById( R.id.liniarlayoutSecondPage );
        linearLayoutPrivew = findViewById( R.id.linearLayoutPrivew );
        lastPage = findViewById( R.id.lastPage );
        txtViewYourEmail = findViewById( R.id.txtViewYourEmail );
        txtViewFirmEmai = findViewById( R.id.txtViewFirmEmai );
        logoRight = findViewById( R.id.logoRight );


        chooseLogo.setOnClickListener( this );



//      EdtiTextYourEmail EditText TextWatcher
        edtiTextYourEmail.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtViewYourEmail.setText( edtiTextYourEmail.getText().toString().trim() );
            }
        } );

//      EdtiTextFirmEmail EditText TextWatcher
        edtiTextFirmEmail.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtViewFirmEmai.setText( edtiTextFirmEmail.getText().toString().trim() );
            }
        } );

        // fill list screen

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("",""));
        mList.add(new ScreenItem("",""));
        mList.add(new ScreenItem("",""));
        mList.add(new ScreenItem("",""));
        mList.add(new ScreenItem("",""));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }
                if (position == mList.size()-5) {
                    loadFirstScreen();

                    edtiTextYourEmail.setVisibility( View.INVISIBLE );
                    edtiTextFirmEmail.setVisibility( View.INVISIBLE );
                    editTextWebsite.setVisibility( View.INVISIBLE );
                    editTextAddress.setVisibility( View.INVISIBLE );

//                    third page invisibal
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );

                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );

                }
                if (position == mList.size()-4) {
                    loadSecondScreen();
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );

                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );


                }
                if (position == mList.size()-3) {
//                    visibal
                    edtiTextProduct.setVisibility( View.VISIBLE );
                    edtiTextProductType.setVisibility( View.VISIBLE );
                    editTextProductSize.setVisibility( View.VISIBLE );
                    linearLayoutIcon.setVisibility( View.VISIBLE );
//                    invisibal
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );

                    edtiTextYourEmail.setVisibility( View.INVISIBLE );
                    edtiTextFirmEmail.setVisibility( View.INVISIBLE );
                    editTextWebsite.setVisibility( View.INVISIBLE );
                    editTextAddress.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );

                }
                if (position == mList.size()-2) {
//                    visibal
                    linearLayoutPrivew.setVisibility( View.VISIBLE );
//                    invisibal
                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );


                }
                if (position == mList.size()-1) {
                    loaddLastScreen();
                    //                    third page invisibal
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );

                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );

                }
                else {
                    btnGetStarted.setVisibility( View.INVISIBLE);
                    btnNext.setVisibility( View.VISIBLE);
                    tvSkip.setVisibility( View.VISIBLE);
                    tabIndicator.setVisibility( View.VISIBLE);
                }
            }
        });

        // tablayout add change listener


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-5) {
                    loadFirstScreen();
                    edtiTextYourEmail.setVisibility( View.INVISIBLE );
                    edtiTextFirmEmail.setVisibility( View.INVISIBLE );
                    editTextWebsite.setVisibility( View.INVISIBLE );
                    editTextAddress.setVisibility( View.INVISIBLE );

//                    third page invisibal
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );
                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );

                }
                if (tab.getPosition() == mList.size()-4) {
                    loadSecondScreen();
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );
                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );


                }
                if (tab.getPosition() == mList.size()-3) {
//                    visibal
                    edtiTextProduct.setVisibility( View.VISIBLE );
                    edtiTextProductType.setVisibility( View.VISIBLE );
                    editTextProductSize.setVisibility( View.VISIBLE );
                    linearLayoutIcon.setVisibility( View.VISIBLE );
//                    invisibal
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );
                    edtiTextYourEmail.setVisibility( View.INVISIBLE );
                    edtiTextFirmEmail.setVisibility( View.INVISIBLE );
                    editTextWebsite.setVisibility( View.INVISIBLE );
                    editTextAddress.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );

                }
                if (tab.getPosition() == mList.size()-2) {
//                    visibal
                    linearLayoutPrivew.setVisibility( View.VISIBLE );

//                    invisibal
                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );
                    lastPage.setVisibility( View.INVISIBLE );


                }
                if (tab.getPosition() == mList.size()-1) {
                    loaddLastScreen();
                    //third page invisibal
                    linearLayoutPrivew.setVisibility( View.INVISIBLE );
                    edtiTextProduct.setVisibility( View.INVISIBLE );
                    edtiTextProductType.setVisibility( View.INVISIBLE );
                    editTextProductSize.setVisibility( View.INVISIBLE );
                    linearLayoutIcon.setVisibility( View.INVISIBLE );

                }
               else {
                    btnGetStarted.setVisibility( View.INVISIBLE);
                    btnNext.setVisibility( View.VISIBLE);
                    tvSkip.setVisibility( View.VISIBLE);
                    tabIndicator.setVisibility( View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Get Started button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(), MainNavActivity.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData();
                finish();



            }
        });

        // skip button click listener

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
                card_intro.setVisibility( View.INVISIBLE );
            }
        });



    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();

    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {
        lastPage.setVisibility( View.VISIBLE );

        btnNext.setVisibility( View.INVISIBLE);
        btnGetStarted.setVisibility( View.VISIBLE);
        tvSkip.setVisibility( View.INVISIBLE);
        tabIndicator.setVisibility( View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }

    private void loadFirstScreen(){
        liniarlayoutFirstPage.setVisibility( View.VISIBLE );
        card_intro.setVisibility( View.VISIBLE );
        editTextYourName.setVisibility( View.VISIBLE );
        editTextFirmName.setVisibility( View.VISIBLE );
        editTextFirmMobileNumber.setVisibility( View.VISIBLE );
    }
    private void loadSecondScreen(){
        liniarlayoutSecondPage.setVisibility( View.VISIBLE );
        edtiTextYourEmail.setVisibility( View.VISIBLE );
        edtiTextFirmEmail.setVisibility( View.VISIBLE );
        editTextWebsite.setVisibility( View.VISIBLE );
        editTextAddress.setVisibility( View.VISIBLE );
        card_intro.setVisibility( View.INVISIBLE );
        editTextYourName.setVisibility( View.INVISIBLE );
        editTextFirmName.setVisibility( View.INVISIBLE );
        editTextFirmMobileNumber.setVisibility( View.INVISIBLE );
    }
    private void InvisiableEditText(){
        card_intro.setVisibility( View.INVISIBLE );
        editTextYourName.setVisibility( View.INVISIBLE );
        editTextFirmName.setVisibility( View.INVISIBLE );
        editTextFirmMobileNumber.setVisibility( View.INVISIBLE );
        edtiTextYourEmail.setVisibility( View.INVISIBLE );
        btnGetStarted.setVisibility( View.INVISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), uri );

                        chooseLogo.setImageBitmap( bitmap );
                        logoRight.setImageBitmap( bitmap );
                        String imagepath = data.getData().getPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    private void selectImage() {
        if (ActivityCompat.checkSelfPermission( IntroActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( IntroActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE );
        } else {
            Intent galleryIntent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
            startActivityForResult( galleryIntent, PICK_IMAGE );
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chooseLogo:
                selectImage();
                break;

        }
    }
}
