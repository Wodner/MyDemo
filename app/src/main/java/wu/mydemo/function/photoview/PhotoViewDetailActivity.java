package wu.mydemo.function.photoview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;
import wu.mydemo.R;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/1/23 14:39
 * 邮箱： descriable
 */
public class PhotoViewDetailActivity extends AppCompatActivity {


    @Bind(R.id.view_pager)
    HackyViewPager viewPager;
    @Bind(R.id.tv_count)
    TextView tvCount;

    private int index = 0;
    private int all = 0;
    private static List<String> stringList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_detail);
        ButterKnife.bind(this);

        stringList = (List<String>) getIntent().getSerializableExtra("list");
        index = getIntent().getIntExtra("index", 0);
        Logger.w(index + "  --  " + stringList.toArray());
        all = stringList.size();
        viewPager.setAdapter(new SamplePagerAdapter(this, stringList));
        viewPager.setCurrentItem(index);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int i = position+1;
                tvCount.setText(i + "/" + all);
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }


    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        context.startActivity(intent);
    }


    class SamplePagerAdapter extends PagerAdapter {

        private Context mContext;
        private List<String> stringList = new ArrayList<>();

        public SamplePagerAdapter(Context context, List<String> list) {
            this.mContext = context;
            this.stringList = list;
        }

        @Override
        public int getCount() {
            return stringList.size();
        }



        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            final PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);

            Picasso.with(mContext)
                    .load(stringList.get(position))
                    .into(photoView, new Callback() {
                        @Override
                        public void onSuccess() {
                            attacher.update();
                        }
                        @Override
                        public void onError() {
                        }
                    });
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
