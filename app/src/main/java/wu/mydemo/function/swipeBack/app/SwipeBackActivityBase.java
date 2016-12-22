package wu.mydemo.function.swipeBack.app;


import wu.mydemo.function.swipeBack.helper.SwipeBackLayout;

public interface SwipeBackActivityBase {
     SwipeBackLayout getSwipeBackLayout();

      void setSwipeBackEnable(boolean enable);

     void scrollToFinishActivity();

}
