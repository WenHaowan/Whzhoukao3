package as.bwei.com.whzhoukao3.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import as.bwei.com.whzhoukao3.R;
import as.bwei.com.whzhoukao3.bean.newss;

/**
 * Created by HP on 2018/7/23.
 */

public class WenAdapter extends BaseAdapter{
    private Context context;
    private List<as.bwei.com.whzhoukao3.bean.newss.DataBean> list;

    public WenAdapter(Context context, List<newss.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder=null;
        if (convertView == null){
            holder= new HolderView();
           convertView = View.inflate(context, R.layout.wenadapter , null);
            holder.t_title = (TextView) convertView.findViewById(R.id.t_title);
            holder.i_mage = (ImageView) convertView.findViewById(R.id.i_mage);
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        holder.t_title.setText(list.get(position).getTitle());
        //获取网络图片
        /*DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(100))
                .build();*/
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),holder.i_mage);
        return convertView;
    }
    public class HolderView{
        private TextView t_title;
        private ImageView i_mage;
    }
}
