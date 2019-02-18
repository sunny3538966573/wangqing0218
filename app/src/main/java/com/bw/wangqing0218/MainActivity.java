package com.bw.wangqing0218;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.adapter.MlssAdapter;
import com.bw.adapter.PzshAdapter;
import com.bw.adapter.RxxpAdapter;
import com.bw.bean.BannerBean;
import com.bw.bean.Commodity;
import com.bw.utils.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rxxp_recy)
    RecyclerView rxxpRecy;
    @BindView(R.id.pzsh_recy)
    RecyclerView pzshRecy;
    @BindView(R.id.mlss_recy)
    RecyclerView mlssRecy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       initBannerData();
       initpzshData();
       initrxxpData();
initmlssData();


    }

    private void initmlssData() {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
        iRequest.getcommodity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Commodity>() {
                    @Override
                    public void accept(Commodity commodity) throws Exception {
                        Commodity.ResultBean result = commodity.getResult();
                        List<Commodity.ResultBean.MlssBean.CommodityListBeanXX> commodityList = result.getMlss().get(0).getCommodityList();
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                        mlssRecy.setLayoutManager(gridLayoutManager);
                        MlssAdapter mlssAdapter = new MlssAdapter(MainActivity.this, commodityList);
                        mlssRecy.setAdapter(mlssAdapter);
                    }
                });
    }

    private void initrxxpData() {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
        iRequest.getcommodity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Commodity>() {
                    @Override
                    public void accept(Commodity commodity) throws Exception {
                        Commodity.ResultBean result = commodity.getResult();
                        List<Commodity.ResultBean.RxxpBean.CommodityListBean> commodityList = result.getRxxp().get(0).getCommodityList();
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                        rxxpRecy.setLayoutManager(gridLayoutManager);
                        RxxpAdapter rxxpAdapter = new RxxpAdapter(MainActivity.this, commodityList);
                        rxxpRecy.setAdapter(rxxpAdapter);

                    }
                });
    }

    private void initpzshData() {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
        iRequest.getcommodity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Commodity>() {
                    @Override
                    public void accept(Commodity commodity) throws Exception {
                        Commodity.ResultBean result = commodity.getResult();
                        List<Commodity.ResultBean.PzshBean.CommodityListBeanX> commodityList = result.getPzsh().get(0).getCommodityList();
                        //GridLayoutManager gridLayoutManager = new Lin(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                        pzshRecy.setLayoutManager(linearLayoutManager);
                        PzshAdapter pzshAdapter = new PzshAdapter(MainActivity.this, commodityList);
                        pzshRecy.setAdapter(pzshAdapter);
                    }
                });
    }

    private void initBannerData() {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
        iRequest.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        if (bannerBean!=null){
                            List<BannerBean.ResultBean> list = bannerBean.getResult();
                            List<String> list1=new ArrayList<>();
                            for (int i = 0; i <list.size(); i++) {
                                String imageUrl = list.get(i).getImageUrl();
                                list1.add(imageUrl);
                            }
                            banner.setDelayTime(2000);
                            banner.isAutoPlay(true);
                            banner.setImageLoader(new MyLoader());
                            banner.setImages(list1)
                                    .setOnBannerListener(new OnBannerListener() {
                                        @Override
                                        public void OnBannerClick(int position) {
                                            Toast.makeText(MainActivity.this, "您点击了"+position+"张轮播图", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .start();
                        }
                    }
                });
    }

    private class MyLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load((String) path)
                    .into(imageView);
        }
    }
}
