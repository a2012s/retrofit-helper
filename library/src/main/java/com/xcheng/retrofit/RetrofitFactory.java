package com.xcheng.retrofit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;

/**
 * 创建时间：2018/4/3
 * 编写人： chengxin
 * 功能描述：管理全局的Retrofit实例
 */
public final class RetrofitFactory {
    /**
     * 缓存不同配置的retrofit集合，如不同的url ,converter等
     */
    public static final Map<String, Retrofit> OTHERS = new ConcurrentHashMap<>(2);
    /**
     * 全局的Retrofit对象,like Charset#bugLevel,HttpLoggingInterceptor#level,
     * AsyncTask#mStatus,facebook->stetho->LogRedirector#sLogger,Timber->forestAsArray
     */
    public static volatile Retrofit DEFAULT;

    private RetrofitFactory() {
    }

    public static <T> T create(Class<T> service) {
        //确保多线程的情况下retrofit不为空或者被改变了 Charset#atBugLevel(String bl)
        Retrofit retrofit = DEFAULT;
        //此时由于多线程导致值被修改了不管
        if (retrofit == null) {
            throw new IllegalStateException("DEFAULT == null");
        }
        return retrofit.create(service);
    }

    /**
     * @param name 获取 OTHERS 中指定名字的retrofit
     */
    public static <T> T create(String name, Class<T> service) {
        Utils.checkNotNull(name, "name == null");
        Retrofit retrofit = OTHERS.get(name);
        if (retrofit == null) {
            throw new IllegalStateException(String.format("retrofit named with \'%s\' was not found , have you put it in OTHERS", name));
        }
        return retrofit.create(service);
    }
}
