package com.example.knowyourcity.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.knowyourcity.Common.LoginSignup.RetailerStartUpScreen;
import com.example.knowyourcity.HelperClasses.HomeAdapter.CatogeriesAdapter;
import com.example.knowyourcity.HelperClasses.HomeAdapter.CatogeriesHelperClass;
import com.example.knowyourcity.HelperClasses.HomeAdapter.FeaturedAdpater;
import com.example.knowyourcity.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.knowyourcity.HelperClasses.HomeAdapter.MostViewHelperClass;
import com.example.knowyourcity.HelperClasses.HomeAdapter.MostViewedAdpater;
import com.example.knowyourcity.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler,MostViewedRecycler,CatogeriesRecycler;
    RecyclerView.Adapter adapter,adapter1,adapter2;

   // private GradientDrawable gradient1;

    ImageView menuIcon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        MostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        CatogeriesRecycler =findViewById(R.id.catogeries_recycler);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);


        naviagtionDrawer();

        // receycler view function calls
        MostViewedRecycler();
        featuredRecycler();
        CatogeriesRecycler();
    }

    //navigation drawer function
    private void naviagtionDrawer() {
        //navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);

      //  drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_all_categories:
            startActivity(new Intent(getApplicationContext(),AllCategories.class));
            break;
        }
        return true;
    }

    //normal function
    public void callRetailerScreens(View view){
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }





    //recycler view function
    private void CatogeriesRecycler() {
        CatogeriesRecycler.setHasFixedSize(true);
        CatogeriesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<CatogeriesHelperClass> catogeriesLocations = new ArrayList<>();
        catogeriesLocations.add(new CatogeriesHelperClass(R.drawable.res,"Restaurant"));
        catogeriesLocations.add(new CatogeriesHelperClass(R.drawable.education,"Education"));
        catogeriesLocations.add(new CatogeriesHelperClass(R.drawable.hospital,"Hospital"));
        catogeriesLocations.add(new CatogeriesHelperClass(R.drawable.shopping,"Shopping"));
        catogeriesLocations.add(new CatogeriesHelperClass(R.drawable.transport,"Transport"));

        adapter2 = new CatogeriesAdapter(catogeriesLocations);
        CatogeriesRecycler.setAdapter(adapter2);

       // GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});
    }
    private void MostViewedRecycler() {
        MostViewedRecycler.setHasFixedSize(true);
        MostViewedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<MostViewHelperClass> mostViewedLocation = new ArrayList<>();

        mostViewedLocation.add(new MostViewHelperClass(R.drawable.mac,"Mcdonlad's","This place is very nice evry one should visit this place high quality food"));
        mostViewedLocation.add(new MostViewHelperClass(R.drawable.city1,"vashi","This place is very nice evry one should visit this place high quality food"));
        mostViewedLocation.add(new MostViewHelperClass(R.drawable.city2,"brazil","This place is very nice evry one should visit this place high quality food"));

        adapter1 = new MostViewedAdpater(mostViewedLocation);
        MostViewedRecycler.setAdapter(adapter1);
    }
    private void featuredRecycler() {

        //load only those views which are visible to user
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mac,"Mcdonlad's","This place is very nice evry one should visit this place high quality food"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city1,"vashi","This place is very nice evry one should visit this place high quality food"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city2,"brazil","This place is very nice evry one should visit this place high quality food"));

        adapter = new FeaturedAdpater(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

}
