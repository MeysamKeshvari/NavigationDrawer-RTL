package meysam.navigationdrawertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import meysam.navigationdrawertest.fragment.FirstFragment;
import meysam.navigationdrawertest.fragment.MainFragment;
import meysam.navigationdrawertest.interfaces.FragmentChangeListener;
import meysam.navigationdrawertest.R;
import meysam.navigationdrawertest.adapter.DrawerAdapter;
import meysam.navigationdrawertest.model.DrawerItem;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<DrawerItem> mDrawerItemList;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        mRecyclerView = findViewById(R.id.drawerRecyclerView);

        MainFragment fragment = new MainFragment();
        replaceFragment(fragment);

       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               drawerLayout.openDrawer(GravityCompat.END);
               if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                   drawerLayout.closeDrawer(GravityCompat.END);
               } else {
                   drawerLayout.openDrawer(GravityCompat.END);
               }
           }
       });

        mDrawerItemList = new ArrayList<DrawerItem>();

        DrawerItem item = new DrawerItem();
        item.setIcon(R.mipmap.ic_launcher);
        item.setTitle("خانه");
        mDrawerItemList.add(item);

        DrawerItem item2 = new DrawerItem();
        item2.setIcon(R.mipmap.ic_launcher);
        item2.setTitle("ارسال");
        mDrawerItemList.add(item2);


        DrawerAdapter adapter = new DrawerAdapter(mDrawerItemList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);


        adapter.setOnItemClickLister(new DrawerAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View v, int position) {
                Toast.makeText(MainActivity.this, "You clicked at position: "+ position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 1:
                        FirstFragment fragment = new FirstFragment();
                        replaceFragment(fragment);
                        drawerLayout.closeDrawers();
                        break;
                    case 2:
                        break;

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_drawer){
            drawerLayout.openDrawer(GravityCompat.END);
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }
        return super.onOptionsItemSelected(item);
    }*/
}
