package com.example.marketup.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class NavSelectAdapter extends RecyclerView.Adapter<NavSelectAdapter.UsersViewHolder> {

    private Activity context;
    private ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories;
    private ArrayList<UserList> userLists;
    int eventID;

    public NavSelectAdapter(Activity context, ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories) {
        this.context = context;
        this.subfilesWithUserDetailHistories = subfilesWithUserDetailHistories;
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.recyclerview_select_layout, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        EventDetail eventDetail = new EventDetail();
//        UserDetail userDetail = new UserDetail();
        int margin = dpTopx( 88 );
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imageviewLogo.getLayoutParams();
        layoutParams.setMarginStart(  10 );

        holder.imageviewMain.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, ImageViewActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText( context, "Data not found", Toast.LENGTH_SHORT).show();

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

    @Override
    public int getItemCount() {
        return subfilesWithUserDetailHistories.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        ImageView imageviewMain,imageviewLogo;
        RelativeLayout relativeLayoutMain;
        LinearLayout linearlayoutvotebutton;
//        Button btnHomeView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            relativeLayoutMain = itemView.findViewById( R.id.relativeLayoutMain );
            imageviewMain = itemView.findViewById( R.id.imageviewMain );
            imageviewLogo = itemView.findViewById( R.id.imageviewLogo );

        }
    }
}
