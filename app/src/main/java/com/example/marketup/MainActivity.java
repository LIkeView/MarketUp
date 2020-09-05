package com.example.marketup;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketup.adapter.NavHomeAdapter;
import com.example.marketup.api.Api;
import com.example.marketup.api.RetrofitClient;
import com.example.marketup.models.ApiResponse;
import com.example.marketup.models.SubfilesWithUserDetailHistory;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeHome = findViewById( R.id.swipeHome );
        toolbar = findViewById( R.id.toolbar );
        rcvallSubFileList = findViewById( R.id.rcvallSubFileList );
        navUserName  = findViewById( R.id.navUserName );

        manager = new LinearLayoutManager(MainActivity.this);
        rcvallSubFileList.setLayoutManager( manager );
//        SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
//        ProfileDetail pd = sfm.getUser();

//        navUserName.setText( pd.getCity() );

        // Toolbar

        TextView toolbartext;
        toolbar=  findViewById(R.id.toolbar);
        toolbartext=  findViewById(R.id.toolbartext);
        toolbartext.setText( "Arch Daily" );

//        FInd ID

        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view);

//        set nevigation drawer

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar, R.string.open,R.string.close );
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.setDrawerIndicatorEnabled( true );
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );


//        method calling
        initReference();
    }


    void initReference() {

//        set Progress Dialog






//        SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
//        ProfileDetail pd = sfm.getUser();





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
                Api api = RetrofitClient.getApi().create(Api.class);
                Call<ApiResponse> call = api.getallsubfile();
                call.enqueue( new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body().getResCode() == 1) {
                            ArrayList<SubfilesWithUserDetailHistory> subfilesWithUserDetailHistories = (ArrayList<SubfilesWithUserDetailHistory>) response.body().getResData().getSubfilesWithUserDetailHistory();
                            NavHomeAdapter adapter =new NavHomeAdapter( MainActivity.this,subfilesWithUserDetailHistories );
                            rcvallSubFileList.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Log.d("Z",""+t.getLocalizedMessage());
                        Toast.makeText( MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
                    }
                } );
                new Handler(  ).postDelayed(new Runnable() {
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
                    rcvallSubFileList.setAdapter(new NavHomeAdapter(MainActivity.this, subfilesWithUserDetailHistories));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Z",""+t.getLocalizedMessage());
                Toast.makeText( MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
            }
        } );

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_rupees:
                Toast.makeText( MainActivity.this, "AddArchRuppes", Toast.LENGTH_LONG ).show();
//                Intent intent = new Intent( getActivity(), AddRuppesActivity.class );
//                startActivity( intent );
                /*FragmentManager fragmentManager = getActivity().getFragmentManager(); // For AppCompat use getSupportFragmentManager
                fragmentManager.beginTransaction().replace( R.id.fragment_container ,new AddArchRupeeFragment()).commit();*/
                break;
            case R.id.nav_wallet_to_bank:
                Toast.makeText( MainActivity.this, "Wallet to bank", Toast.LENGTH_LONG ).show();
//                Intent intent1 = new Intent( getActivity(), WalletToBankAcountActivity.class );
//                startActivity( intent1 );
                break;

            case R.id.nav_passbook:
                Toast.makeText( MainActivity.this, "Passbook", Toast.LENGTH_LONG ).show();
//                Intent intent2 = new Intent( getActivity(), PassbookActivity.class );
//                startActivity( intent2 );
                break;
            case R.id.nav_add_event:
                Toast.makeText( MainActivity.this, "Add Event", Toast.LENGTH_LONG ).show();
//                Intent intent3 = new Intent( getActivity(), AddEventsActivity.class );
//                startActivity( intent3 );
                break;
            case R.id.nav_logout:
                Toast.makeText( MainActivity.this, "Log Out", Toast.LENGTH_LONG ).show();
//                SharedPrefManager sfm = SharedPrefManager.getInstance(context);
//                sfm.clear();

//                Intent i = new Intent( getActivity(), LoginActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
                break;
        }

        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

}