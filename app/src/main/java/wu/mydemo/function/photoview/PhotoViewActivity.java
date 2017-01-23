package wu.mydemo.function.photoview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.photoview.adapter.PhotoViewAdapter;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/1/22 17:12
 * 邮箱： descriable
 */
public class PhotoViewActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recycleView;

    private PhotoViewAdapter recycleAdapter;
    private List<String> stringList = new ArrayList<>();


    @Override
    protected int setContentViewId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void setUpView() {
        initRecycleView();
    }

    @Override
    protected void init() {

    }

    private void initRecycleView(){
        stringList.clear();
        stringList.add("http://s9.rr.itc.cn/r/wapChange/20165_5_23/a5wxzk0986707182352.jpg");
        stringList.add("http://img4.duitang.com/uploads/item/201209/23/20120923175620_NvfmU.jpeg");
        stringList.add("http://tupian.enterdesk.com/2015/xu/08/13/1/7.jpg");
        stringList.add("http://img17.3lian.com/d/file/201701/20/cac6ce4701972ffb2ad76afd840b2162.jpg");
        recycleAdapter = new PhotoViewAdapter(this,stringList);
        recycleView.setLayoutManager(new GridLayoutManager(this,2));
        recycleView.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(listener);
    }

    private PhotoViewAdapter.OnRecyclerViewItemClickListener listener = new PhotoViewAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View v, String msg, int postion) {
            Intent intent=new Intent(mContext,PhotoViewDetailActivity.class);
            intent.putExtra("list", (Serializable)stringList);
            intent.putExtra("index",postion);
            startActivity(intent);
        }
    };

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        context.startActivity(intent);
    }

}
