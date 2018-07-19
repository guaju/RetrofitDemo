package com.guaju.retrofitdemo.bean;

import java.util.List;

/**
 * Created by guaju on 2018/7/19.
 */

public class TestBean {


    /**
     * code : 200
     * massage : 请求成功
     * data : [{"id":"4","img":"upload/2018/07/03/20180703165545949.jpg","goods_id":"848"},{"id":"2","img":"upload/2018/07/18/20180718103918554.jpg","goods_id":"773"},{"id":"13","img":"upload/2018/06/27/20180627104840180.jpg","goods_id":"770"}]
     */

    private int code;
    private String massage;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * img : upload/2018/07/03/20180703165545949.jpg
         * goods_id : 848
         */

        private String id;
        private String img;
        private String goods_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "code=" + code +
                ", massage='" + massage + '\'' +
                ", data=" + data +
                '}';
    }
}
