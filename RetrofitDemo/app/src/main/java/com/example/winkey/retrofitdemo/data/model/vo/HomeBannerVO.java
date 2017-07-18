package com.example.winkey.retrofitdemo.data.model.vo;

import java.util.List;

/**
 * Created by Winkey on 2017/7/18.
 */

public class HomeBannerVO {

    public List<ItemsBean> Items;

    public static class ItemsBean implements Cloneable{
        /**
         * CoverUrl : http://image.flm158.com/House/00000000-0000-0000-0000-000000000009/a_74411397.jpg
         * RedirectUrl : http://www.flm158.com
         * StartDateTime : 2017-04-10T00:00:00.0000000+08:00
         * EndDateTime : 2017-04-10T00:00:00.0000000+08:00
         * SkipTime : 3
         */

        public String CoverUrl;
        public String RedirectUrl;
        public String StartDateTime;
        public String EndDateTime;
        public int SkipTime;

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}