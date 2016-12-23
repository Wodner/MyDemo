package wu.mydemo.function.coordinatorLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.main.adapter.MainRecycleAdapter;
import wu.mydemo.utils.MyStatusBarUtil;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2016/12/23 14:44
 * 邮箱： descriable
 */
public class WithCollapsingActivity extends BaseActivity {


    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;

    private MainRecycleAdapter recycleAdapter;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected int setContentViewId() {
        return R.layout.activity_collasing;

    }

    @Override
    protected void setUpView() {
        hideTitleBar();
        MyStatusBarUtil.setStatusTransparent(mContext,false);
        toolbar.setTitle("Go Spurs Go");
        setSupportActionBar(toolbar);
        //设置toolbar后调用setDisplayHomeAsUpEnabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void init() {
        initRecycleView();
    }

    private void initRecycleView(){
        stringList.clear();
        Collections.addAll(stringList, getResources().getStringArray(R.array.itme_function));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setLayoutManager(layoutManager);
        recycleAdapter = new MainRecycleAdapter(mContext,stringList);

        recycleView.setAdapter(recycleAdapter);
    }
    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, WithCollapsingActivity.class);
        context.startActivity(intent);
    }

}
