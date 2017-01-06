package wu.mydemo.function.pictureSelect;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yalantis.ucrop.ui.AlbumDirectoryActivity;
import com.yalantis.ucrop.ui.ImageGridActivity;
import com.yalantis.ucrop.util.LocalMediaLoader;
import com.yalantis.ucrop.util.Options;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.app.Constant;
import wu.mydemo.function.pictureSelect.adapter.GridImageAdapter;
import wu.mydemo.function.pictureSelect.util.FullyGridLayoutManager;

/**
 * 功能： 图片选择器
 * 作者： Administrator
 * 日期： 2016/12/26 16:22
 * 邮箱： descriable
 */
public class PictureSelector extends BaseActivity implements RadioGroup.OnCheckedChangeListener{


    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.minus)
    ImageButton minus;
    @Bind(R.id.select_num)
    EditText selectNum;
    @Bind(R.id.plus)
    ImageButton plus;
    @Bind(R.id.rb_image)
    RadioButton rbImage;
    @Bind(R.id.rb_video)
    RadioButton rbVideo;
    @Bind(R.id.rgbs0)
    RadioGroup rgbs0;
    @Bind(R.id.rb_single)
    RadioButton rbSingle;
    @Bind(R.id.rb_multiple)
    RadioButton rbMultiple;
    @Bind(R.id.rgbs1)
    RadioGroup rgbs1;
    @Bind(R.id.rb_photo_display)
    RadioButton rbPhotoDisplay;
    @Bind(R.id.rb_photo_hide)
    RadioButton rbPhotoHide;
    @Bind(R.id.rgbs2)
    RadioGroup rgbs2;
    @Bind(R.id.rb_default)
    RadioButton rbDefault;
    @Bind(R.id.rb_to1_1)
    RadioButton rbTo11;
    @Bind(R.id.rb_to3_2)
    RadioButton rbTo32;
    @Bind(R.id.rb_to3_4)
    RadioButton rbTo34;
    @Bind(R.id.rb_to16_9)
    RadioButton rbTo169;
    @Bind(R.id.rgbs3)
    RadioGroup rgbs3;
    @Bind(R.id.rb_preview)
    RadioButton rbPreview;
    @Bind(R.id.rb_preview_false)
    RadioButton rbPreviewFalse;
    @Bind(R.id.rgbs4)
    RadioGroup rgbs4;
    @Bind(R.id.rb_preview_video)
    RadioButton rbPreviewVideo;
    @Bind(R.id.rb_preview_video_false)
    RadioButton rbPreviewVideoFalse;
    @Bind(R.id.rgbs5)
    RadioGroup rgbs5;
    @Bind(R.id.rb_yes_copy)
    RadioButton rbYesCopy;
    @Bind(R.id.rb_no_copy)
    RadioButton rbNoCopy;
    @Bind(R.id.rgbs6)
    RadioGroup rgbs6;

    private GridImageAdapter adapter;
    private List<String> images = new ArrayList<>();
    private int maxSelectNum = 9;// 图片最大可选数量
    private int selectType = LocalMediaLoader.TYPE_IMAGE;
    private int copyMode = Constant.COPY_MODEL_DEFAULT;
    private boolean enablePreview = true;
    private boolean isPreviewVideo = true;
    private boolean enableCrop = true;
    private boolean isShow = true;
    private int selectMode = Constant.MODE_MULTIPLE;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_picture_selector;
    }


    @Override
    protected void setUpView() {
        rgbs0.setOnCheckedChangeListener(this);
        rgbs1.setOnCheckedChangeListener(this);
        rgbs2.setOnCheckedChangeListener(this);
        rgbs3.setOnCheckedChangeListener(this);
        rgbs4.setOnCheckedChangeListener(this);
        rgbs5.setOnCheckedChangeListener(this);
        rgbs6.setOnCheckedChangeListener(this);
    }


    @Override
    protected void init() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(mContext, onAddPicClickListener);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_single:
                selectMode = Constant.MODE_SINGLE;
                break;
            case R.id.rb_multiple:
                selectMode = Constant.MODE_MULTIPLE;
                break;
            case R.id.rb_image:
                selectType = LocalMediaLoader.TYPE_IMAGE;
                break;
            case R.id.rb_video:
                selectType = LocalMediaLoader.TYPE_VIDEO;
                break;
            case R.id.rb_photo_display:
                isShow = true;
                break;
            case R.id.rb_photo_hide:
                isShow = false;
                break;
            case R.id.rb_default:
                copyMode = Constant.COPY_MODEL_DEFAULT;
                break;
            case R.id.rb_to1_1:
                copyMode = Constant.COPY_MODEL_1_1;
                break;
            case R.id.rb_to3_2:
                copyMode = Constant.COPY_MODEL_3_2;
                break;
            case R.id.rb_to3_4:
                copyMode = Constant.COPY_MODEL_3_4;
                break;
            case R.id.rb_to16_9:
                copyMode = Constant.COPY_MODEL_16_9;
                break;
            case R.id.rb_preview:
                enablePreview = true;
                break;
            case R.id.rb_preview_false:
                enablePreview = false;
                break;
            case R.id.rb_preview_video:
                isPreviewVideo = true;
                break;
            case R.id.rb_preview_video_false:
                isPreviewVideo = false;
                break;
            case R.id.rb_yes_copy:
                enableCrop = true;
                break;
            case R.id.rb_no_copy:
                enableCrop = false;
                break;
        }
    }



    /**
     * 删除图片回调接口
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 进入相册
                    /**
                     * type --> 1图片 or 2视频
                     * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
                     * maxSelectNum --> 可选择图片的数量
                     * selectMode         --> 单选 or 多选
                     * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
                     * isPreview    --> 是否打开预览选项
                     * isCrop       --> 是否打开剪切选项
                     * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
                     * 注意-->type为2时 设置isPreview or isCrop 无效
                     * 注意：Options可以为空，默认标准模式
                     */
                    Options options = new Options();
                    options.setType(selectType);
                    options.setCopyMode(copyMode);
                    options.setMaxSelectNum(maxSelectNum);
                    options.setSelectMode(selectMode);
                    options.setShowCamera(isShow);
                    options.setEnablePreview(enablePreview);
                    options.setEnableCrop(enableCrop);
                    options.setPreviewVideo(isPreviewVideo);
                    AlbumDirectoryActivity.startPhoto(mContext, options);
                    break;
                case 1:
                    // 删除图片
                    Log.i("删除的下标---->", position + "");
                    images.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

    @OnClick({R.id.minus, R.id.plus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.minus:
                maxSelectNum--;
                selectNum.setText(maxSelectNum + "");
                break;
            case R.id.plus:
                maxSelectNum++;
                selectNum.setText(maxSelectNum + "");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ImageGridActivity.REQUEST_IMAGE:
                    images = (ArrayList<String>) data.getSerializableExtra(ImageGridActivity.REQUEST_OUTPUT);
                    if (images != null) {
                        adapter.setList(images);
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, PictureSelector.class);
        context.startActivity(intent);
    }
}
