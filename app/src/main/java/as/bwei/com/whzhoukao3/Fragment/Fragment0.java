package as.bwei.com.whzhoukao3.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bwei.imageloaderlibrary.utils.ImageLoaderUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import as.bwei.com.whzhoukao3.Adapter.MyAdapter;
import as.bwei.com.whzhoukao3.Adapter.WenAdapter;
import as.bwei.com.whzhoukao3.R;
import as.bwei.com.whzhoukao3.Utils.XlistUtils;
import as.bwei.com.whzhoukao3.bean.News;
import as.bwei.com.whzhoukao3.bean.newss;
import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * Created by HP on 2018/7/23.
 */

public class Fragment0 extends Fragment{

    private List<as.bwei.com.whzhoukao3.bean.newss.DataBean> list1 = new ArrayList<>();
    String path = "http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=15&page=";
    private int num = 1;
    private View view;
    private ViewPager vp;
    private RadioGroup rg;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String json = (String) msg.obj;
                Gson gson = new Gson();
                News news = gson.fromJson(json, News.class);
                List<News.BannerBean> banner = news.getBanner();
                for (int i=0;i<banner.size(); i++){
                    String image_url = banner.get(i).getImage_url();
                    ImageView imageView = new ImageView(getActivity());
                    DisplayImageOptions options = ImageLoaderUtils.getOptions();
                    ImageLoader.getInstance().displayImage(image_url,imageView,options);
                    list.add(imageView);
                }
                MyAdapter adapter = new MyAdapter(getActivity(),list);
                vp.setAdapter(adapter);
            }else if (msg.what == 1){
                int i = vp.getCurrentItem();
                vp.setCurrentItem(++i);
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };
    private ArrayList<ImageView> list;
    private XListView x_listview;
    private WenAdapter adapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment0, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vp = (ViewPager) view.findViewById(R.id.vp);
        rg = (RadioGroup) view.findViewById(R.id.radio_group);
        list = new ArrayList<>();
        getdataimg();

        initfrom();
        getdatafrom();
        //设置按钮同步
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position%list.size()).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getdatafrom() {
        String url = path+num;
        XlistUtils utils = XlistUtils.getlist();
        utils.getdata(url);
        //接口回调
        utils.SetXlistr(new XlistUtils.Xlister() {
            @Override
            public void getjson(String jsonn) {
                //开始
                Gson gsonn = new Gson();
                newss newss = gsonn.fromJson(jsonn, newss.class);
                List<as.bwei.com.whzhoukao3.bean.newss.DataBean> data1 = newss.getData();
                if (num == 1){
                    list1.clear();
                }
                //添加
                list1.addAll(data1);
                //刷新适配器
                adapter1.notifyDataSetChanged();
                if (num == 1){
                    x_listview.stopRefresh();
                }else{
                    x_listview.stopLoadMore();
                }
            }
        });
    }

    private void initfrom() {
        x_listview = (XListView) view.findViewById(R.id.x_listview);
        //可以刷新
        x_listview.setPullRefreshEnable(true);
        //可以加载
        x_listview.setPullLoadEnable(true);
        //设置监听方法
        x_listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {//刷新
                num = 1;
                getdatafrom();
            }

            @Override
            public void onLoadMore() {//加载
                num += 1;
                Log.i("sss",num+"");
                getdatafrom();
            }
        });
        adapter1 = new WenAdapter(getActivity(),list1);
        x_listview.setAdapter(adapter1);
    }

    private void getdataimg() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String path ="http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=banner";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200){
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] by = new byte[1024];
                        int len = 0;
                        while ((len = inputStream.read(by)) !=-1){
                            bos.write(by,0,len);
                        }
                        inputStream.close();
                        bos.close();
                        String json = bos.toString();
                        Message message = new Message();
                        message.what = 0;
                        message.obj = json;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    @Override
    public void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }
}
