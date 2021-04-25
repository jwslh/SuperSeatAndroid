package indi.wkq.superseatandroid.utils.sdkInit;

import android.app.Application;
import android.content.Context;

import com.tencent.mmkv.MMKV;
import com.xuexiang.xpage.PageConfig;
import com.xuexiang.xpage.PageConfiguration;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xutil.XUtil;

import java.util.List;

import indi.wkq.superseatandroid.activity.MainActivity;
import indi.wkq.superseatandroid.base.BaseActivity;

/**
 * @author calesq
 * @date 2021/4/25
 */
public class BaseLibInit {
    /**
     * 初始化基础库
     */
    public static void init(Application application) {
        initUtils(application);
        initPage(application);

    }
    private static void initUtils(Application application) {
        XUtil.init(application);
        MMKV.initialize(application);
    }

    /**
     * 初始化XPage页面框架
     *
     * @param application
     */
    private static void initPage(Application application) {
        //自动注册页面
        //自动注册页面
        PageConfig.getInstance()
                .debug("PageLog")       //开启调试
                .setContainActivityClazz(MainActivity.class) //设置默认的容器Activity，按需设置（非必须）
                .init(application);            //初始化页面配置

    }
}
