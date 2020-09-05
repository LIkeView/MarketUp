package com.example.marketup.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketup.ImageViewActivity;
import com.example.marketup.MainActivity;
import com.example.marketup.R;
import com.example.marketup.models.EventDetail;
import com.example.marketup.models.SubfilesWithUserDetailHistory;
import com.example.marketup.models.UserList;
import com.squareup.picasso.Picasso;
import com.zolad.zoominimageview.ZoomInImageView;

import java.util.ArrayList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.models.User;
//import com.aswdc.archdaily.models.UserDetail;

public class NavHomeAdapter extends RecyclerView.Adapter<NavHomeAdapter.UsersViewHolder> {

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
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        EventDetail eventDetail = new EventDetail();
//        UserDetail userDetail = new UserDetail();

        holder.imgProjHome.setOnClickListener( new View.OnClickListener() {
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
        holder.textUserName.setText( subfilesWithUserDetailHistories.get( position ).getName() );
        Picasso.with( context ).load( subfilesWithUserDetailHistories.get( position ).getSubFilePath() ).fit().centerCrop().into( holder.imgProjHome );
//        Glide.with(context).load(subfilesWithUserDetailHistories.get(position).getSubFilePath()).into(holder.imgProjHome);
    }

    @Override
    public int getItemCount() {
        return subfilesWithUserDetailHistories.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textUserName,textvoted,textVoteCount ;
        ZoomInImageView imgProjHome;
        LinearLayout linearlayoutvotebutton;
//        Button btnHomeView;
        public UsersViewHolder(View itemView) {
            super(itemView);

            linearlayoutvotebutton = itemView.findViewById(R.id.linearlayoutvotebutton);
            textUserName = itemView.findViewById(R.id.textUserName);
            textvoted = itemView.findViewById(R.id.textvoted);
            textVoteCount = itemView.findViewById(R.id.textVoteCount);
//            textMaxuser = itemView.findViewById(R.id.textMaxuser);
            imgProjHome = itemView.findViewById( R.id.imgProjHome );

        }
    }
}
