package wu.mydemo.function.swipeListDelete;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.swipeListDelete.adapter.ListAdapter;
import wu.mydemo.function.swipeListDelete.widget.SwipeLayout;

/**
 * 滑动删除
 * Created by Administrator on 2016/12/21.
 */
public class SwipeListDeleteActivity extends BaseActivity {


    @Bind(R.id.list)
    ListView listView;

    private ListAdapter adapter;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected int setContentViewId() {
        return R.layout.activity_swipe_list_layout;
    }

    @Override
    protected void setUpView() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    protected void init() {
        stringList.clear();
        Collections.addAll(stringList, getResources().getStringArray(R.array.itme_function));
        adapter = new ListAdapter(mContext,stringList);

        listView.setAdapter(adapter);
        adapter.setOnItemDeleteClickListener(new ListAdapter.OnItemDeleteClickListener() {
            @Override
            public void onItemDelete(View v, int postion) {
                stringList.remove(postion);
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    SwipeLayout.closeAll(mContext);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, SwipeListDeleteActivity.class);
        context.startActivity(intent);
    }
}
