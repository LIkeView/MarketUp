package com.example.marketup.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.marketup.R;
import com.example.marketup.Tools.AutoFitGridLayoutManager;
import com.example.marketup.Tools.CheckNetwork;
import com.example.marketup.adapter.NavHomeAdapter;
import com.example.marketup.adapter.NavSaveAdapter;
import com.example.marketup.api.Api;
import com.example.marketup.api.RetrofitClient;
import com.example.marketup.models.ApiResponse;
import com.example.marketup.models.SubfilesWithUserDetailHistory;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavSaveFragment extends Fragment {

    RecyclerView rcvallSubFileList;
    Context context;
    SwipeRefreshLayout swipeHome;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView navUserName;
    ImageView backButton;
    AutoFitGridLayoutManager manager;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_save, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        swipeHome = view.findViewById( R.id.swipeHome );
        toolbar = view.findViewById( R.id.toolbar );
        rcvallSubFileList = view.findViewById( R.id.rcvallSubFileList );
//        navUserName  = view.findViewById( R.id.navUserName );

                manager = new AutoFitGridLayoutManager( getActivity() ,3);
        rcvallSubFileList.setLayoutManager( manager );
//        SharedPrefManager sfm = SharedPrefManager.getInstance(getActivity());
//        ProfileDetail pd = sfm.getUser();

//        navUserName.setText( pd.getCity() );

        // Toolbar

        TextView toolbartext;
        toolbar=  view.findViewById( R.id.toolbar);
        toolbartext=  view.findViewById(R.id.toolbartext);
//        toolbartext.setText( "Daily Branding" );

//        FInd ID
//
//        drawerLayout= view.findViewById(R.id.drawer_layout);
//        navigationView= view.findViewById(R.id.nav_view);

//        set nevigation drawer

//        actionBarDrawerToggle = new ActionBarDrawerToggle( getActivity(),drawerLayout,toolbar, R.string.open,R.string.close );
//        drawerLayout.addDrawerListener( actionBarDrawerToggle );
//        actionBarDrawerToggle.setDrawerIndicatorEnabled( true );
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener( this );

        if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
        {
            initReference();
            //do something. loadwebview.
        }
        else
        {
            Toast.makeText(getActivity(),"No Internet Connection", Toast.LENGTH_LONG).show();
        }

//        method calling

    }

//      create method

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
                    rcvallSubFileList.setAdapter(new NavSaveAdapter(getActivity(), subfilesWithUserDetailHistories));
                }
                else
                {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Z",""+t.getLocalizedMessage());
                Toast.makeText( getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
            }
        } );

    }




//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.nav_add_rupees:
//                Toast.makeText( getActivity(), "AddArchRuppes", Toast.LENGTH_LONG ).show();
////                Intent intent = new Intent( getActivity(), AddRuppesActivity.class );
////                startActivity( intent );
//                /*FragmentManager fragmentManager = getActivity().getFragmentManager(); // For AppCompat use getSupportFragmentManager
//                fragmentManager.beginTransaction().replace( R.id.fragment_container ,new AddArchRupeeFragment()).commit();*/
//                break;
//            case R.id.nav_wallet_to_bank:
//                Toast.makeText( getActivity(), "Wallet to bank", Toast.LENGTH_LONG ).show();
////                Intent intent1 = new Intent( getActivity(), WalletToBankAcountActivity.class );
////                startActivity( intent1 );
//                break;
//
//            case R.id.nav_passbook:
//                Toast.makeText( getActivity(), "Passbook", Toast.LENGTH_LONG ).show();
////                Intent intent2 = new Intent( getActivity(), PassbookActivity.class );
////                startActivity( intent2 );
//                break;
//            case R.id.nav_add_event:
//                Toast.makeText( getActivity(), "Add Event", Toast.LENGTH_LONG ).show();
////                Intent intent3 = new Intent( getActivity(), AddEventsActivity.class );
////                startActivity( intent3 );
//                break;
//            case R.id.nav_logout:
//                Toast.makeText( getActivity(), "Log Out", Toast.LENGTH_LONG ).show();
////                SharedPrefManager sfm = SharedPrefManager.getInstance(context);
////                sfm.clear();
////
////                Intent i = new Intent( getActivity(), LoginActivity.class);
////                i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
////                startActivity(i);
//                break;
//        }
//
//        drawerLayout.closeDrawer( GravityCompat.START );
//        return true;
//
//    }

}
