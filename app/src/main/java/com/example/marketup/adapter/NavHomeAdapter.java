package com.example.marketup.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketup.ImageViewActivity;
import com.example.marketup.R;
import com.example.marketup.models.EventDetail;
import com.example.marketup.models.SubfilesWithUserDetailHistory;
import com.example.marketup.models.UserList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;
//import com.aswdc.archdaily.models.UserDetail;

public class NavHomeAdapter extends RecyclerView.Adapter<NavHomeAdapter.UsersViewHolder> {

    private GestureDetector mGestureDetector;

    private Activity context;
    private ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories;
    private ArrayList<UserList> userLists;
    int eventID;

    public NavHomeAdapter(Activity context, ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories) {
        this.context = context;
        this.subfilesWithUserDetailHistories = subfilesWithUserDetailHistories;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_home, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position ) {


        if (position == 0 ){
            Log.d( "aaaa",""+position );
            holder.view_fillper_card.setVisibility( View.VISIBLE );
            int[] resources = { R.drawable.gate, R.drawable.view_pager,  R.drawable.account, R.drawable.bluebutton };

            for (int i = 0; i < resources.length; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(resources[i]);
                holder.viewFlipperHome.addView(imageView);

                holder.viewFlipperHome.setInAnimation(context, android.R.anim.slide_in_left);
                holder.viewFlipperHome.setOutAnimation(context, android.R.anim.slide_out_right);
                holder.viewFlipperHome.setAutoStart(true);
                holder.viewFlipperHome.setFlipInterval(4000); // flip every 2 seconds (2000ms)
                CustomGestureDetector customGestureDetector = new CustomGestureDetector();
                mGestureDetector = new GestureDetector(context, customGestureDetector);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imageviewLogo.getLayoutParams();
            layoutParams.setMarginStart(  18 );
            RelativeLayout.LayoutParams layoutParamsrelativeLayoutMain = (RelativeLayout.LayoutParams) holder.relativeLayoutMain.getLayoutParams();
            layoutParamsrelativeLayoutMain.height = 1081;

            holder.imageviewMain.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( context, ImageViewActivity.class );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra( "image", subfilesWithUserDetailHistories.get(position).getSubFilePath());
                    context.startActivity(intent);

                }
            } );
            Picasso.with( context ).load( subfilesWithUserDetailHistories.get( position ).getSubFilePath() ).fit().centerCrop().into( holder.imageviewMain );

        }
        else {
            Log.d( "aaaa1",""+position );
            holder.view_fillper_card.setVisibility( View.GONE );

            int margin = dpTopx( 88 );


            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imageviewLogo.getLayoutParams();
            layoutParams.setMarginStart(  18 );
            RelativeLayout.LayoutParams layoutParamsrelativeLayoutMain = (RelativeLayout.LayoutParams) holder.relativeLayoutMain.getLayoutParams();
//            layoutParamsrelativeLayoutMain.height = 1081;

            holder.imageviewMain.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( context, ImageViewActivity.class );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra( "image", subfilesWithUserDetailHistories.get(position).getSubFilePath());
                    context.startActivity(intent);

                }
            } );
            Picasso.with( context ).load( subfilesWithUserDetailHistories.get( position ).getSubFilePath() ).into( holder.imageviewMain );

        }

    }


    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return false;
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public boolean onFling(@NonNull UsersViewHolder holder, MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                holder.viewFlipperHome.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                holder.viewFlipperHome.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private int dpTopx(int dp){
        float px = dp * context.getResources().getDisplayMetrics().density;
        return (int) px;
    }
    public void flipperImage(@NonNull UsersViewHolder holder,int image){
        ImageView imageView = new ImageView( context );
        imageView.setBackgroundResource( image );
        holder.viewFlipperHome.addView( imageView );
        holder.viewFlipperHome.setFlipInterval( 4000 );
        holder.viewFlipperHome.setAutoStart( true );
//        annimation
        holder.viewFlipperHome.setInAnimation( context,R.anim.fragment_fast_out_extra_slow_in );
        holder.viewFlipperHome.setOutAnimation( context,R.anim.nav_default_enter_anim );
    }

    @Override
    public int getItemCount() {
        return subfilesWithUserDetailHistories.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewMain,imageviewLogo;
        ViewFlipper viewFlipperHome;
//        TextView textappname;
        CardView cardViewHome;
        RelativeLayout relativeLayoutMain;
        CardView view_fillper_card;
//        Button btnHomeView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            viewFlipperHome = itemView.findViewById( R.id.viewFlipperHome );
            view_fillper_card = itemView.findViewById( R.id.view_fillper_card );
//            textappname = itemView.findViewById( R.id.textappname );
            cardViewHome = itemView.findViewById( R.id.cardViewHome );
            relativeLayoutMain = itemView.findViewById( R.id.relativeLayoutMain );
            imageviewMain = itemView.findViewById( R.id.imageviewMain );
            imageviewLogo = itemView.findViewById( R.id.imageviewLogo );

        }
    }
}
