package app.sts.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.sts.com.R;
import app.sts.com.ui.map.MapFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShellActivity extends BaseActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    View headerView;
    CircleImageView headerAvatar;
    TextView headerUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string
                .navigation_drawer_open,
                R.string.navigation_drawer_close);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);
        headerAvatar = ButterKnife.findById(headerView, R.id.header_profile_image);
        headerUsername = ButterKnife.findById(headerView, R.id.header_username);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void loadMainContainer(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment, title);
        fragmentTransaction.commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
        drawerClosed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_logOut:
                break;
            case R.id.item_one:
                loadMainContainer(new MapFragment(), "MAP");
                break;
            default:
                break;
        }
        drawerClosed();
        return true;
    }

    private void drawerClosed() {
        drawer.closeDrawer(GravityCompat.START);
    }
}
