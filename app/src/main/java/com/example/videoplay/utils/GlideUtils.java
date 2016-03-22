package com.example.videoplay.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.example.videoplay.R;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Huangwy
 * @TIME 2016/3/18 20:54
 */
public class GlideUtils {

    public static GlideUtils glideUtils = null;

    /**
     * 图片缓存路径
     */
    private static final String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "vp" + File.separator + "cache/image/";

    /**
     * 清楚磁盘缓存线程池，现行大多数GUI程序都是单线程的。
     * Android中单线程可用于数据库操作，文件操作，
     * 应用批量安装，应用批量删除等不适合并发
     * 但可能IO阻塞性及影响UI线程响应的操作。
     */
    private ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    public static GlideUtils getInstance() {
        if (glideUtils == null) {
            glideUtils = new GlideUtils();
        }
        return glideUtils;
    }

    private GlideUtils() {
    }

    /**
     * 获取图片缓存文件
     *
     * @param context
     * @return
     */
    public File getImageCacheDir(Context context) {
        return Glide.getPhotoCacheDir(context);
    }

    /**
     * 设置图片缓存路径
     *
     * @param context
     */
    public void setImageCacheDir(Context context) {
        GlideBuilder glideBuilder = new GlideBuilder(context);
        glideBuilder.setDiskCache(new DiskLruCacheFactory(dir, 50 * 1024 * 1024));
    }

    /**
     * 清空内存
     *
     * @param context
     */
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清空磁盘缓存
     *
     * @param context
     */
    public void clearDiskCache(final Context context) {
        singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        });
    }

    public void loadUrl(Context context, String url, ImageView imageView) {
        loadData(context, url).into(imageView);
    }

    public void loadUrl(Fragment fragment, String url, ImageView imageView) {
        loadData(fragment, url).into(imageView);
    }

    public void loadUrl(Activity activity, String url, ImageView imageView) {
        loadData(activity, url).into(imageView);
    }

    public GenericRequestBuilder loadData(Fragment fragment, String url) {
        if (checkGif(url)) {
            return Glide.with(fragment).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE);
        } else {
            return Glide.with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter().error(R.drawable.empty);
        }
    }

    public GenericRequestBuilder loadData(Activity activity, String url) {
        if (checkGif(url)) {
            return Glide.with(activity).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE);
        } else {
            return Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter().error(R.drawable.empty);
        }
    }

    public GenericRequestBuilder loadData(Context context, String url) {
        if (checkGif(url)) {
            return Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE);
        } else {
            return Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter().error(R.drawable.empty);
        }
    }

    /**
     * 检测图片格式
     *
     * @param url
     * @return
     */
    private boolean checkGif(String url) {
        return !TextUtils.isEmpty(url) && url.endsWith(".gif");
    }
}
