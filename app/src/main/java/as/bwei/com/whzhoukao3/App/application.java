package as.bwei.com.whzhoukao3.App;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by HP on 2018/7/23.
 */

public class application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration Configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(Configuration);
    }
}
