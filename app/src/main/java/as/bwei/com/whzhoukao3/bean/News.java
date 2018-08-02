package as.bwei.com.whzhoukao3.bean;

import java.util.List;

/**
 * Created by HP on 2018/7/23.
 */

public class News {

    private List<BannerBean> banner;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * desc : 写小说。短篇小说集《小镇忧郁青年的十八种死法》。
         * image_url : http://image.wufazhuce.com/FuPgOcbGDD9__fyuCdPBXb5pbA1C
         */

        private String desc;
        private String image_url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
