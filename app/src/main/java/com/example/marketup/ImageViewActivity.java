package com.example.marketup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

public class ImageViewActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private ImageView image;
    private TextView txtTitle,txtTitle2;
    private RelativeLayout relativeLayout;
    ImageView imgResultImage;
    Button btnConvertToImage;
    private Button button;
    String ImagePath;
    Uri URI;
    private int xDelta;
    private int yDelta;
    ImageView image1;
    Context context;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_iamge);

        image1 = (ImageView) findViewById( R.id.image1 );
        relativeLayout = (RelativeLayout) findViewById( R.id.main );
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image = (ImageView) findViewById(R.id.image);
        txtTitle = (TextView) findViewById( R.id.txtTitle );
        txtTitle2 = (TextView) findViewById( R.id.txtTitle2 );
//        button = (Button) findViewById( R.id.button );

        imgResultImage = findViewById(R.id.imgResultImage);

        String Image = getIntent().getStringExtra( "image");
        Picasso.with( context ).load( Image ).fit().centerCrop().into( image1 );

        btnConvertToImage = findViewById(R.id.btnConvertToimage);


        btnConvertToImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap image = getBitmapFromView(mainLayout);
//                imgResultImage.setImageBitmap(image);

                ImagePath = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        image,
                        "Day Name",
                        "Day Discription"
                );

                URI = Uri.parse(ImagePath);
                Toast.makeText(ImageViewActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();


            }
        });

//        button.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap bitmap=Bitmap.createBitmap( view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888 );
//                Canvas canvas = new Canvas( bitmap );
////        Drawable bgDrawable = view.getBackground();
////        if (bgDrawable != null){
////            bgDrawable.draw( canvas );
////        }else {
////            canvas.drawColor( R.color.whiteTextColor );
////        }
//                mainLayout.draw(canvas);
//            }
//        } );

//        View content = findViewById(R.id.main);
//        content.setDrawingCacheEnabled(true);
//        Bitmap bitmap = content.getDrawingCache();
//        File file = new File("/sdcard/" + "yourimagename" + ".png");
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileOutputStream ostream = new FileOutputStream(file);
//            bitmap.compress( Bitmap.CompressFormat.PNG, 10, ostream);
//            ostream.close();
//            content.invalidate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            content.setDrawingCacheEnabled(false);
//        }
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
                        Toast.makeText(ImageViewActivity.this,
                                "I'm here!", Toast.LENGTH_SHORT)
                                .show();
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
                        Toast.makeText(ImageViewActivity.this,
                                "I'm here!", Toast.LENGTH_SHORT)
                                .show();
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
        image.setOnTouchListener(onTouchListener());
    }
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor( Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

//    private void share(){
//        Bitmap bitmap =getBitmapFormView(mainLayout);
////        try {
////            File file = new File( this.getExternalCacheDir(),"lorex.png" );
////            FileOutputStream fout =  new FileOutputStream( file );
////            bitmap.compress( Bitmap.CompressFormat.PNG,100,fout );
////            fout.flush();
////            fout.close();
////            file.setReadable( true,false );
////            Intent intent = new Intent( Intent.ACTION_SEND );
////            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
////            intent.putExtra( Intent.EXTRA_STREAM, Uri.fromFile(file) );
////            intent.setType( "image/png" );
////            startActivity( Intent.createChooser( intent,"share by" ) );
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }

//    @SuppressLint("ResourceAsColor")
//    private Bitmap getBitmapFormView(View view){
//
//    }


    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

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
                        Toast.makeText(ImageViewActivity.this,
                                "I'm here!", Toast.LENGTH_SHORT)
                                .show();
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
        };
    }


}