package wu.mydemo.function.bottomNavBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;

/**
 * 功能： 底部导航栏
 * 作者： Administrator
 * 日期： 2017/1/9 10:20
 * 邮箱： descriable
 */
public class BottomNavBarActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{


    @Bind(R.id.id_content)
    FrameLayout idContent;
    @Bind(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_bottom_nav_bar;
    }

    @Override
    protected void setUpView() {

        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.color_de4942)
                .setText("5")
                .setHideOnSelect(false);

        //设置BottomNavigationBar的模式，会在下面详细讲解
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        //设置BottomNavigationBar的背景风格，后面详细讲解
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //我这里增加了3个Fragment
        //BottomNavigationItem("底部导航ico","底部导航名字")
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页").setBadgeItem(numberBadgeItem)
                .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "发现")
                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的")
                        .setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)//默认选择索引为0的菜单
                .initialise();//对导航进行重绘
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    protected void init() {

    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, BottomNavBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }
}
