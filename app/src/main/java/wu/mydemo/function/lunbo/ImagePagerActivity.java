package wu.mydemo.function.lunbo;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.utils.SystemTool;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/1/18 16:03
 * 邮箱： descriable
 */
public class ImagePagerActivity extends BaseActivity{


    @Bind(R.id.banner)
    Banner banner;

    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_image_pager;
    }

    @Override
    protected void setUpView() {

        images.add("http://s9.rr.itc.cn/r/wapChange/20165_5_23/a5wxzk0986707182352.jpg");
        images.add("http://img4.duitang.com/uploads/item/201209/23/20120923175620_NvfmU.jpeg");
        images.add("http://tupian.enterdesk.com/2015/xu/08/13/1/7.jpg");
        images.add("http://img17.3lian.com/d/file/201701/20/cac6ce4701972ffb2ad76afd840b2162.jpg");
        titles.add("星空图片一");
        titles.add("冲锋战神");
        titles.add("飓风音速");
        titles.add("海贼王");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


        banner.setOnBannerClickListener(listener);
    }

    @Override
    protected void init() {

    }


    private OnBannerClickListener listener = new OnBannerClickListener() {
        @Override
        public void OnBannerClick(int position) {
            SystemTool.showSnackeBar(banner,"点击 " + position);
        }
    };
}
