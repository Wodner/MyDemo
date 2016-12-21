package wu.mydemo.function.swipeListDelete.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 功能 ：左滑动删除
 * 使用 ：使用的时候，在item的布局中  第一个摆放 列表显示内容，第二个摆放 滑动时候显示的内容
 * Created by Administrator on 2016/12/8.
 */
public class SwipeLayout extends FrameLayout {

    private View mBackView;
    private View mFrontView;
    /**itme控件的高度*/
    private int mHeight;
    /**前面控件的宽 --即屏幕宽度*/
    private int mWidth;
    /**移动距离*/
    private int mRange;
    private ViewDragHelper mViewDragHelper;
    private static final String CLOSE_EXPAND_ACTION = "swipelayout_delete_close";

    public SwipeLayout(Context context){
        super(context, null);
        mViewDragHelper = ViewDragHelper.create(this,0.7f,mCallBack);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet){
        super(context, attributeSet, 0);
        mViewDragHelper = ViewDragHelper.create(this,0.7f,mCallBack);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this,0.7f,mCallBack);

    }

    /**
     * 拖拽回调
     */
    ViewDragHelper.Callback mCallBack = new ViewDragHelper.Callback(){
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            closeAll(getContext());
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            /**这个判断主要是不让控件前面控件右滑动 即左滑动删除*/
            // 定义真正的偏移量
            int offset = 0;
            if (child == mFrontView) {//在这里处理放置的逻辑拖拽的前view
                if (left > 0) {
                    return 0;
                } else if (left < -mRange) {
                    offset =  -mRange;
                }else {
                    offset = left;
                }
            }//拖拽的后view
            else if (child == mBackView) {
                if (left > mWidth) {
                    offset = mWidth;
                } else if (left < mWidth - mRange) {
                    offset = mWidth - mRange;
                }else {
                    offset = left;
                }
            }
            return offset;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            //传递事件，如果是拖拽的前view，
            if (changedView == mFrontView){
                //Offset this view's horizontal location by the specified amount of pixels.
                //也就是说我的我的前view左滑了dx，那么我的后view也是左滑dx，右滑同理
                mBackView.offsetLeftAndRight(dx);
            } else if (changedView == mBackView){
                //拖拽的是后view的话，前View的处理方式一样
                mFrontView.offsetLeftAndRight(dx);
            }
            dispatchSwipeEvent();
            invalidate();//兼容老版本
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (xvel == 0 && mFrontView.getLeft() < -mRange / 2.0f) {
                open();
            } else if (xvel < 0) {
                open();
            } else {
                close();
            }
        }
    };

    /**
     * 关闭
     */
    public void close() {
        close(true);
    }
    public void open() {
        open(true);
    }

    /**
     * 关闭
     * @param isSmooth
     */
    public void close(boolean isSmooth) {
        int finalLeft = 0;
        if (isSmooth) {
            //开始动画 如果返回true表示没有完成动画
            if (mViewDragHelper.smoothSlideViewTo(mFrontView, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            layoutContent(false);
        }
    }

    /**
     * 打开
     * @param isSmooth
     */
    public void open(boolean isSmooth) {
        int finalLeft = -mRange;
        if (isSmooth) {
        //开始动画
            if (mViewDragHelper.smoothSlideViewTo(mFrontView, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            layoutContent(true);
        }
    }

    /**
     * 持续动画
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //这个是固定的
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**交给ViewDragHelper判断是否去拦截事件*/
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mViewDragHelper.cancel();
            return false;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mViewDragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回true，这里表示去拦截事件
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /**后view*/
        mBackView = getChildAt(1);
        /**前view*/
        mFrontView = getChildAt(0);
    }

    /**
     * 在这里获取宽和高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /**高度*/
        mHeight = mFrontView.getMeasuredHeight();
        /**宽度*/
        mWidth = mFrontView.getMeasuredWidth();
        /**移动距离*/
        mRange = mBackView.getMeasuredWidth();
        // 注册广播
        IntentFilter filter = new IntentFilter(CLOSE_EXPAND_ACTION);
        mReceiver = new CloseExpandReceiver();
        getContext().registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        layoutContent(false);
    }

    private void layoutContent(boolean isOpen) {
        //摆放前view
        Rect frontRect = computeFrontViewRect(isOpen);
        mFrontView.layout(frontRect.left, frontRect.top, frontRect.right, frontRect.bottom);
        //摆放后view
        Rect backRect = computeBackViewRect(frontRect);
        mBackView.layout(backRect.left,backRect.top,backRect.right,backRect.bottom);
        //前置前view
        bringChildToFront(mFrontView);
    }


    /**
     * 我们可以把前view相当于一个矩形
     *
     * @param frontRect
     * @return
     */
    private Rect computeBackViewRect(Rect frontRect) {
        int left = frontRect.right;
        return new Rect(left, 0, left + mRange, 0 + mHeight);
    }
    private Rect computeFrontViewRect(boolean isOpen) {
        int left = 0;
        if (isOpen) {
            left = -mRange;
        }
        return new Rect(left, 0, left + mWidth, 0 + mHeight);
    }



    public static void closeAll(Context context){
        Intent intent = new Intent();
        intent.setAction(SwipeLayout.CLOSE_EXPAND_ACTION);
        context.sendBroadcast(intent);
    }

    private BroadcastReceiver mReceiver;
    private class CloseExpandReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("我接收到广播了");
            // 布局内容区域
            mFrontView.layout(0, 0, mWidth, mHeight);
            // 布局删除区域
            mBackView.layout(mWidth, 0, mWidth + mRange, mHeight);
            status = Status.Close;
        }
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mReceiver != null){
            getContext().unregisterReceiver(mReceiver);
            System.out.println("取消注册广播");
        }
    }


    protected void dispatchSwipeEvent() {
        //判断是否为空
        if (swipeLayoutListener != null) {
            swipeLayoutListener.onDraging(this);
        }
        // 记录上一次的状态
        Status preStatus = status;
        // 更新当前状态
        status = updateStatus();
        if (preStatus != status && swipeLayoutListener != null) {
            if (status == Status.Close) {
                swipeLayoutListener.onClose(this);
            } else if (status == Status.Open) {
                swipeLayoutListener.onOpen(this);
            } else if (status == Status.Draging) {
                if (preStatus == Status.Close) {
                    swipeLayoutListener.onStartOpen(this);
                } else if (preStatus == Status.Open) {
                    swipeLayoutListener.onStartClose(this);
                }
            }
        }
    }

    /**
     * 更新状态
     *
     * @return
     */
    private Status updateStatus() {
        //得到前view的左边位置
        int left = mFrontView.getLeft();
        if (left == 0) {
        //如果位置是0，就是关闭状态
            return Status.Close;
        } else if (left == -mRange) {
        //如果左侧边距是后view的宽度的负值，状态为开
            return Status.Open;
        }
        //其他状态就是拖拽
        return Status.Draging;
    }


/**--------------------------------------------------------------------------------------------------*/

    /**
     * 默认状态是关闭
     */
    private Status status = Status.Close;

    private OnSwipeLayoutListener swipeLayoutListener;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OnSwipeLayoutListener getSwipeLayoutListener() {
        return swipeLayoutListener;
    }

    public void setSwipeLayoutListener(OnSwipeLayoutListener swipeLayoutListener) {
        this.swipeLayoutListener = swipeLayoutListener;
    }
    /**
     * 定义三种状态
     */
    public enum Status {
        Close, Open, Draging
    }

    /**
     * 定义回调接口 这个在我们
     */
    public interface OnSwipeLayoutListener {
        /**
         * 关闭
         * @param mSwipeLayout
         */
        void onClose(SwipeLayout mSwipeLayout);
        /**
         * 打开
         * @param mSwipeLayout
         */
        void onOpen(SwipeLayout mSwipeLayout);
        /**
         * 绘制
         * @param mSwipeLayout
         */
        void onDraging(SwipeLayout mSwipeLayout);
        /**
         * 要去关闭
         */
        void onStartClose(SwipeLayout mSwipeLayout);
        /**
         * 要去开启
         */
        void onStartOpen(SwipeLayout mSwipeLayout);
    }

}
