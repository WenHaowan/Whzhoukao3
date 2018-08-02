package as.bwei.com.whzhoukao3.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HP on 2018/7/23.
 */

public class XlistUtils {

    private static XlistUtils xlistUtils;
    private Xlister xlister;

    public static XlistUtils getlist(){
        if (xlistUtils==null){
            xlistUtils = new XlistUtils();
        }
        return xlistUtils;
    }
    public void getdata(String url){
        MyAsktaxsk data = new MyAsktaxsk();
        data.execute(url);
    }
    public class MyAsktaxsk extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            try {
                URL url = new URL(path);
                HttpURLConnection Connection = (HttpURLConnection) url.openConnection();
                Connection.setRequestMethod("GET");
                Connection.setReadTimeout(5000);
                Connection.setConnectTimeout(5000);
                if (Connection.getResponseCode() == 200){
                    InputStream inputStream = Connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] by = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(by)) != -1){
                        bos.write(by,0,len);
                    }
                    inputStream.close();
                    bos.close();
                    String json = bos.toString();
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("aaa",s);
            xlister.getjson(s);
        }
    }
    public interface Xlister{
        public void getjson(String json);
    }
    public void SetXlistr(Xlister xlister){
        this.xlister = xlister;
    }
}
