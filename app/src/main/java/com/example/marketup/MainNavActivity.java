package com.example.marketup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

//import com.aswdc.archdaily.Fragment.ProjectsFragment;
//import com.aswdc.archdaily.ui.dashboard.DashboardFragment;
//import com.aswdc.archdaily.ui.notifications.NotificationsFragment;

public class MainNavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rcvallSubFileList;
    Context context;
    SwipeRefreshLayout swipeHome;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    TextView navUserName;
    ImageView backButton;
    LinearLayoutManager manager;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_nav );
//        Toolbar toolbar;
//        toolbar = findViewById( R.id.toolbar );

        Toolbar toolbar;
        TextView toolbartext;
        toolbar = findViewById( R.id.toolbar);
        toolbartext =  findViewById(R.id.toolbartext);

        toolbartext.setText( "Daily Branding" );
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view);

        //set nevigation drawer

        actionBarDrawerToggle = new ActionBarDrawerToggle( MainNavActivity.this,drawerLayout,toolbar, R.string.open,R.string.close );
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.setDrawerIndicatorEnabled( true );
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );

        BottomNavigationView navigationView = findViewById( R.id.bottom_nav );
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_event, R.id.navigation_select_layout,R.id.navigation_profile)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        navigationView.setOnNavigationItemSelectedListener( this );
//        displayFragment( new NavHomeFragment() );
//        Log.d( "Use:L::::",""+ SharedPrefManager.getInstance( this ).getUser().getUserId() );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_rupees:
                Toast.makeText( MainNavActivity.this, "AddArchRuppes", Toast.LENGTH_LONG ).show();
//                Intent intent = new Intent( getActivity(), AddRuppesActivity.class );
//                startActivity( intent );
                /*FragmentManager fragmentManager = getActivity().getFragmentManager(); // For AppCompat use getSupportFragmentManager
                fragmentManager.beginTransaction().replace( R.id.fragment_container ,new AddArchRupeeFragment()).commit();*/
                break;
            case R.id.nav_wallet_to_bank:
                Toast.makeText( MainNavActivity.this, "Wallet to bank", Toast.LENGTH_LONG ).show();
//                Intent intent1 = new Intent( getActivity(), WalletToBankAcountActivity.class );
//                startActivity( intent1 );
                break;

            case R.id.nav_passbook:
                Toast.makeText(MainNavActivity.this, "Passbook", Toast.LENGTH_LONG ).show();
//                Intent intent2 = new Intent( getActivity(), PassbookActivity.class );
//                startActivity( intent2 );
                break;
            case R.id.nav_add_event:
                Toast.makeText( MainNavActivity.this, "Add Event", Toast.LENGTH_LONG ).show();
//                Intent intent3 = new Intent( getActivity(), AddEventsActivity.class );
//                startActivity( intent3 );
                break;
            case R.id.nav_logout:
                Toast.makeText( MainNavActivity.this, "Log Out", Toast.LENGTH_LONG ).show();
//                SharedPrefManager sfm = SharedPrefManager.getInstance(context);
//                sfm.clear();
//
//                Intent i = new Intent( getActivity(), LoginActivity.class);
//                i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
                break;
        }

        drawerLayout.closeDrawer( GravityCompat.START );
        return true;

    }



//    private void displayFragment(Fragment fragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace( R.id.relativeLayout, fragment )
//                .commit();
//    }


//    com.aswdc.archdaily.Fragment.HomeFragment homeFragment = new com.aswdc.archdaily.Fragment.HomeFragment();

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        Fragment fragment = null;
//
//        switch (item.getItemId()) {
//            case R.id.menu_home:
//                fragment = new NavHomeFragment();
//                break;
//            case R.id.menu_event:
//                fragment = new NavEventFragment();
//                break;
//            case R.id.menu_users:
//                fragment = new NavWinnerListFragment();
//                break;
//            case R.id.menu_settings:
//                fragment = new NavProfileFragment();
//                break;
//        }
//
//        if (fragment != null) {
//            displayFragment( fragment );
//        }
//
//        return false;
//    }
}


