package com.yw.android.store;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.ww.mvp.WWApplication;
import com.yw.android.store.bean.user.UserBean;
import com.yw.android.store.config.AppConfig;

import ww.com.core.Debug;
import ww.com.core.utils.ACache;
import ww.com.http.OkHttpRequest;

/**
 * Created by feng on 2017/9/19.
 * application
 */

public class BaseApplication extends WWApplication {

    private static final String KEY_USER_CACHE = "auth_user";
    private static BaseApplication instance;
    private ACache cache;

    private UserBean user;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        cache = ACache.get(this);
        boolean debug = BuildConfig.DEBUG;
        //设置代码日志打印
        Debug.setDebug(debug);
        Debug.setTag("Store");
        //初始化ImageLoader
        initImageLoader(getApplicationContext());
        //设置OkhttpRequest请求日志打印
        OkHttpRequest.setLogging(AppConfig.Debug);
    }



    public static BaseApplication getInstance() {
        return instance;
    }

    public void saveUser(UserBean user){
        if (user == null) {
            cache.remove(KEY_USER_CACHE);
        } else {
            cache.put(KEY_USER_CACHE, user);
        }
        this.user = user;
    }

    public UserBean getUserBean() {
        if (user != null) {
            return user;
        }
        user = cache.getAsObject(KEY_USER_CACHE);
        return user;
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCache(DefaultConfigurationFactory.createDiscCache(context, new
                        Md5FileNameGenerator(), 0, 100))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(150)
                .build();
        ImageLoader.getInstance().init(config);
    }
    public static DisplayImageOptions getDisplayImageOptions(int drawableRes) {
        return getDisplayImageOptions(drawableRes, drawableRes, drawableRes);
    }
    public static DisplayImageOptions getDisplayImageOptions(int onLoading,
                                                             int emptyUri, int onFail) {
        return getDisplayImageBuilder(onLoading, emptyUri, onFail).build();
    }

    public static DisplayImageOptions.Builder getDisplayImageBuilder(
            int onLoading, int emptyUri, int onFail) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.resetViewBeforeLoading(true).cacheInMemory(true)
                .cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .showImageOnLoading(onLoading).showImageForEmptyUri(emptyUri)
                .showImageOnFail(onFail).build();
        return builder;
    }

}
