package meysam.navigationdrawertest.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import meysam.navigationdrawertest.fragment.FirstFragment;
import meysam.navigationdrawertest.interfaces.FragmentChangeListener;
import meysam.navigationdrawertest.R;
import meysam.navigationdrawertest.adapter.DrawerAdapter;
import meysam.navigationdrawertest.model.DrawerItem;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<DrawerItem> mDrawerItemList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolbar);

        mDrawerItemList = new ArrayList<DrawerItem>();

        DrawerItem item = new DrawerItem();
        item.setIcon(R.mipmap.ic_launcher);
        item.setTitle("خانه");
        mDrawerItemList.add(item);

        DrawerItem item2 = new DrawerItem();
        item2.setIcon(R.mipmap.ic_launcher);
        item2.setTitle("ارسال");
        mDrawerItemList.add(item2);

        mRecyclerView = findViewById(R.id.drawerRecyclerView);
        DrawerAdapter adapter = new DrawerAdapter(mDrawerItemList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        drawerLayout = findViewById(R.id.drawerLayout);

        ImageView imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawerLayout.openDrawer(GravityCompat.START);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                Log.d("DBG","Clicked");
            }
        });

        /*mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();*/

        adapter.setOnItemClickLister(new DrawerAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View v, int position) {
                Toast.makeText(MainActivity.this, "You clicked at position: "+ position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 1:
                        FirstFragment fragment = new FirstFragment();
                        replaceFragment(fragment);
                        break;
                    case 2:
                        break;

                }
            }
        });
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
