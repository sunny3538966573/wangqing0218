package com.bw.wangqing0218;

import com.bw.bean.BannerBean;
import com.bw.bean.Commodity;
import com.bw.config.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 1607c王晴
 * date 2019/2/18
 * Describe:
 */
public interface IRequest {
    @GET(HttpConfig.banner_url)
    Observable<BannerBean> getBanner();

    @GET(HttpConfig.commodity_url)
    Observable<Commodity> getcommodity();


}
