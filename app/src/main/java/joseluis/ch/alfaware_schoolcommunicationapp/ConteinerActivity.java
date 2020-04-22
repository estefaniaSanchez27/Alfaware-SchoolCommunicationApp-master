package joseluis.ch.alfaware_schoolcommunicationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import joseluis.ch.alfaware_schoolcommunicationapp.Fragments.GroupsFragment;
import joseluis.ch.alfaware_schoolcommunicationapp.Fragments.MessagesFragment;
import joseluis.ch.alfaware_schoolcommunicationapp.Fragments.ProfileFragment;
import joseluis.ch.alfaware_schoolcommunicationapp.adapters.ViewPagerAdapter;

public class ConteinerActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private ViewPager viewPager;

    ProfileFragment profileFragment;
    MessagesFragment messagesFragment;
    GroupsFragment groupsFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteiner);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menssages:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.grups:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.profile:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                }
                else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page","onPageSelected"+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().findItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager vp){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        messagesFragment = new MessagesFragment();
        groupsFragment = new GroupsFragment();
        profileFragment = new ProfileFragment();
        adapter.addFragment(messagesFragment);
        adapter.addFragment(groupsFragment);
        adapter.addFragment(profileFragment);
        vp.setAdapter(adapter);
    }
}
