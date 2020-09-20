package com.example.marketup.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketup.Activity.SaveListActivity;
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

public class NavSaveAdapter extends RecyclerView.Adapter<NavSaveAdapter.UsersViewHolder> {


    private Activity context;
    private ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories;
    int selecteditem;

    public NavSaveAdapter(Activity context, ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories) {
        this.context = context;
        this.subfilesWithUserDetailHistories = subfilesWithUserDetailHistories;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_save, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position ) {
        EventDetail eventDetail = new EventDetail();


        int margin = dpTopx( 88 );

        final ViewTreeObserver observer= holder.relativeLayoutMain.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int layoutWith = holder.relativeLayoutMain.getWidth();
                        Log.d("Log", "width: " + layoutWith);
                        RelativeLayout.LayoutParams layoutParamsrelativeLayoutMain = (RelativeLayout.LayoutParams) holder.relativeLayoutMain.getLayoutParams();
                        layoutParamsrelativeLayoutMain.height = layoutWith;
                        Log.d("Log", "height: " + layoutParamsrelativeLayoutMain.height);

                    }
                });
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imageviewLogo.getLayoutParams();
            layoutParams.setMarginStart(  88 );

            holder.imageviewMain.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selecteditem = holder.getAdapterPosition();
                    Intent intent = new Intent( context, SaveListActivity.class );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra( "select", selecteditem);
                    intent.putExtra( "image", subfilesWithUserDetailHistories.get(position).getSubFilePath());
//                intent.putExtra( userDetail.getUserId(), listEvents.get(position).getEventId());
//                intent.putExtra( String.valueOf( pd.getUserId() ), listEvents.get(position).getEventId());
                    context.startActivity(intent);

                }
            } );
            Picasso.with( context ).load( subfilesWithUserDetailHistories.get( position ).getSubFilePath() ).fit().centerCrop().into( holder.imageviewMain );

//        Glide.with(context).load(subfilesWithUserDetailHistories.get(position).getSubFilePath()).into(holder.imgProjHome);
    }

    private int dpTopx(int dp){
        float px = dp * context.getResources().getDisplayMetrics().density;
        return (int) px;
    }
//    public void flipperImage(@NonNull UsersViewHolder holder,int image){
//        ImageView imageView = new ImageView( context );
//        imageView.setBackgroundResource( image );
//        holder.viewFlipperHome.addView( imageView );
//        holder.viewFlipperHome.setFlipInterval( 4000 );
//        holder.viewFlipperHome.setAutoStart( true );
////        annimation
//        holder.viewFlipperHome.setInAnimation( context,R.anim.fragment_fast_out_extra_slow_in );
//        holder.viewFlipperHome.setOutAnimation( context,R.anim.nav_default_enter_anim );
//    }

    @Override
    public int getItemCount() {
        return subfilesWithUserDetailHistories.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewMain,imageviewLogo;
        CardView cardViewHome;
        RelativeLayout relativeLayoutMain;
        LinearLayout linearlayoutvotebutton;
//        Button btnHomeView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            cardViewHome = itemView.findViewById( R.id.cardViewHome );
            relativeLayoutMain = itemView.findViewById( R.id.relativeLayoutMain );
            imageviewMain = itemView.findViewById( R.id.imageviewMain );
            imageviewLogo = itemView.findViewById( R.id.imageviewLogo );
        }
    }
}
