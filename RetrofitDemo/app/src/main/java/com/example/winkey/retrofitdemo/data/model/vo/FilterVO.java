package com.example.winkey.retrofitdemo.data.model.vo;

import java.util.List;
import java.util.Vector;

/**
 * Created by Winkey on 2017/7/17.
 */

public class FilterVO {

    public List<SectionsBean> Sections;

    public static class SectionsBean implements Cloneable {

        public String name;
        public String key;
        public boolean isRadio;
        public List<ValuesBean> values;

        public static class ValuesBean implements Cloneable {

            public String name;
            public Object value;
            public boolean isSelected;

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

        @Override
        public Object clone() {
            SectionsBean o = null;
            try {
                o = (SectionsBean) super.clone();
                o.values = new Vector<>();
                for (int i = 0; i < values.size(); i++) {
                    o.values.add((ValuesBean) values.get(i).clone());
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return o;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        FilterVO vo = null;
        vo = (FilterVO) super.clone();
        vo.Sections = new Vector<>();
        for (int i = 0; i < Sections.size(); i++) {
            vo.Sections.add((SectionsBean) Sections.get(i).clone());
        }
        return vo;
    }
}
