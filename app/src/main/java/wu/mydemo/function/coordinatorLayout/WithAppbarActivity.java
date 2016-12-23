package wu.mydemo.function.coordinatorLayout;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.coordinatorLayout.fragment.TabFragment1;
import wu.mydemo.function.coordinatorLayout.fragment.TabFragment2;
import wu.mydemo.function.coordinatorLayout.fragment.TabFragment3;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2016/12/23 14:43
 * 邮箱： descriable
 */
public class WithAppbarActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPagerFragmentAdatper adatper;
    private String titles[] = {"TAB 1", "TAB 2", "TAB 3"};

    @Override
    protected int setContentViewId() {
        return R.layout.activity_appbar_coordin;
    }

    @Override
    protected void setUpView() {
        hideTitleBar();
        mToolbar.setTitle("CoordinatorAppBar");
        setSupportActionBar(mToolbar);
        //设置toolbar后调用setDisplayHomeAsUpEnabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void init() {
        mFragment.add(TabFragment1.getInstance());
        mFragment.add(TabFragment2.getInstance());
        mFragment.add(TabFragment3.getInstance());
        adatper = new ViewPagerFragmentAdatper(getSupportFragmentManager(),titles);
        viewpager.setAdapter(adatper);
        tabs.addTab(tabs.newTab().setText(titles[0]));
        tabs.addTab(tabs.newTab().setText(titles[1]));
        tabs.addTab(tabs.newTab().setText(titles[2]));
        tabs.setupWithViewPager(viewpager);
    }


    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, WithAppbarActivity.class);
        context.startActivity(intent);
    }


    /**
     * ViewPager 适配器
     */
    class ViewPagerFragmentAdatper extends FragmentPagerAdapter {

        private String [] title;

        public ViewPagerFragmentAdatper(FragmentManager fm,String [] titles) {
            super(fm);
            this.title = titles;
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
