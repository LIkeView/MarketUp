package com.example.marketup.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketup.R;
import com.example.marketup.Tools.CheckNetwork;
import com.example.marketup.adapter.NavHomeAdapter;
import com.example.marketup.adapter.SaveListAdapter;
import com.example.marketup.api.Api;
import com.example.marketup.api.RetrofitClient;
import com.example.marketup.models.ApiResponse;
import com.example.marketup.models.SubfilesWithUserDetailHistory;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveListActivity extends AppCompatActivity {

    RecyclerView rcvallSubFileList;
    Context context;
    SwipeRefreshLayout swipeHome;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView navUserName;
    ImageView backButton;
    LinearLayoutManager manager;
    int selecteditem;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_save_list );

        swipeHome = findViewById( R.id.swipeHome );
        toolbar = findViewById( R.id.toolbar );
        rcvallSubFileList = findViewById( R.id.rcvallSubFileList );
//        navUserName  = view.findViewById( R.id.navUserName );

        manager = new LinearLayoutManager( context );
        rcvallSubFileList.setLayoutManager( manager );

        // Toolbar

        TextView toolbartext;
        toolbar=  findViewById( R.id.toolbar);
        toolbartext=  findViewById(R.id.toolbartext);

        if(CheckNetwork.isInternetAvailable(SaveListActivity.this)) //returns true if internet available
        {
            initReference();
            selecteditem = getIntent().getIntExtra( "select", 0 );
            rcvallSubFileList.scrollToPosition( selecteditem );
            //do something. loadwebview.
        }
        else
        {
            Toast.makeText(context,"No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    void initReference() {
//        set Progress Dialog
        rcvallSubFileList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                }
            }
        });
        getData();

//        navUserName.setText("pd.getName() " );

//        Set swipe Refrish
        swipeHome.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                new Handler(  ).postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        swipeHome.setRefreshing( false );
                    }
                } ,400);
            }
        } );


    }
    void getData(){
        Api api = RetrofitClient.getApi().create(Api.class);
        Call<ApiResponse> call = api.getallsubfile();
        call.enqueue( new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getResCode() == 1) {
                    ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories = (ArrayList<SubfilesWithUserDetailHistory>) response.body().getResData().getSubfilesWithUserDetailHistory();
                    rcvallSubFileList.setAdapter(new SaveListAdapter(SaveListActivity.this, subfilesWithUserDetailHistories));
                }
                else
                {
                    Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Z",""+t.getLocalizedMessage());
                Toast.makeText( context, t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
            }
        } );

    }
}