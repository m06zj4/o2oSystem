package tw.itlab.zhaojun.o2o.unicorn_ui_v4;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,BeaconConsumer{

    private BeaconManager beaconManager;
    String UUID, major, minor, Dist, UUID1, major1, minor1, textout,textout1, UUID2, major2, minor2, UUID3, major3, minor3;
    Collection<Beacon> max, different, different2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));     //0215為讀取iBeacon  beac為altBeacon
        beaconManager.bind(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("本地"));
        tabLayout.addTab(tabLayout.newTab().setText("热门"));
        tabLayout.addTab(tabLayout.newTab().setText("今日"));
        tabLayout.addTab(tabLayout.newTab().setText("当期"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Tab_Apdater adapter = new Tab_Apdater
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {

                Log.w("mydebug_beacon", "123");


                if (beacons.size() > 0) {


                    UUID = beacons.iterator().next().getId1().toString();
                    major = beacons.iterator().next().getId2().toString();
                    minor = beacons.iterator().next().getId3().toString();
                    Dist = String.valueOf(beacons.iterator().next().getDistance());


                    Log.w("mydebug_beacon_UUID", UUID);
                    Log.w("mydebug_beacon_major", major);
                    Log.w("mydebug_beacon_minor", minor);
                    Log.w("mydebug_beacon_dist", Dist);


                    if (max == null) {
                        max = beacons;
                        Log.w("mydebug_test", "123");
                        Log.w("mydebug_enter", "123");

                    } else {
//                        if (different.iterator().next().getId3()!=beacons.iterator().next().getId3()){
//                            out
//                        }

                        UUID1 = max.iterator().next().getId1().toString();
                        major1 = max.iterator().next().getId2().toString();
                        minor1 = max.iterator().next().getId3().toString();
                        Log.w("mydebug_test_minor0", minor1);
                        if (minor1 != beacons.iterator().next().getId3().toString()) {

                            if (different == null) {
                                different = beacons;
                                Log.w("mydebug_enter1", "123");
                            } else {
                                UUID2 = different.iterator().next().getId1().toString();
                                major2 = different.iterator().next().getId2().toString();
                                minor2 = different.iterator().next().getId3().toString();
                                Log.w("mydebug_test_minor1", minor2);

                                if (minor2 != beacons.iterator().next().getId3().toString()) {

                                    if (different2 == null) {
                                        different2 = beacons;
                                        Log.w("mydebug_enter", "123");
                                    } else {
                                        UUID3 = different2.iterator().next().getId1().toString();
                                        major3 = different2.iterator().next().getId2().toString();
                                        minor3 = different2.iterator().next().getId3().toString();
                                        Log.w("mydebug_test_minor2", minor3);

                                    }
                                }
                            }


                        }
//                        if (max.iterator().next().getDistance() > beacons.iterator().next().getDistance()) {
//                            max = beacons;
//                            Log.w("mydebug_beacon_test", "21");
//                        }
                    }
                    Toast.makeText(MainActivity.this, minor, Toast.LENGTH_SHORT).show();

                }
            }

        });
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
