package wu.mydemo.function.music.model;

/**
 * 类描述：
 * 作  者：wuwen
 * 时  间：on 2016/11/19 at 20:08
 * 修改备注：
 */

public class MusicState {
    public static final int
            IDLE = 0,       /**  */
            INITIALIZED = 1,/** 初始化 */
            PREPARED = 2,   /** 准备完成 */
            PREPARING = 3,  /** 准备ing  */
            STARTED = 4,    /** 开始 */
            PAUSED = 5,     /** 暂停 */
            STOPPED = 6,    /** 停止 */
            COMPLETED = 7,  /** 播放完成 */
            END = -1,       /** 最后 */
            ERROR = -2;     /** 错误 */
}
