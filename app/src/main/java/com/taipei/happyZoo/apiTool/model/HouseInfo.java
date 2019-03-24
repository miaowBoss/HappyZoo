package com.taipei.happyZoo.apiTool.model;

import com.taipei.happyZoo.page.houseDetail.HouseDetailAdapter;

import java.io.Serializable;
import java.util.List;

public class HouseInfo implements Serializable {

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

        private int limit;
        private int offset;
        private int count;
        private String sort;
        private List<ResultsBean> results;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean implements HouseDetailAdapter.DetailType , Serializable {

            private String E_Pic_URL;
            private String E_Geo;
            private String E_Info;
            private String E_no;
            private String E_Category;
            private String E_Name;
            private String E_Memo;
            private int _id;
            private String E_URL;

            public String getE_Pic_URL() {
                return E_Pic_URL;
            }

            public void setE_Pic_URL(String E_Pic_URL) {
                this.E_Pic_URL = E_Pic_URL;
            }

            public String getE_Geo() {
                return E_Geo;
            }

            public void setE_Geo(String E_Geo) {
                this.E_Geo = E_Geo;
            }

            public String getE_Info() {
                return E_Info;
            }

            public void setE_Info(String E_Info) {
                this.E_Info = E_Info;
            }

            public String getE_no() {
                return E_no;
            }

            public void setE_no(String E_no) {
                this.E_no = E_no;
            }

            public String getE_Category() {
                return E_Category;
            }

            public void setE_Category(String E_Category) {
                this.E_Category = E_Category;
            }

            public String getE_Name() {
                return E_Name;
            }

            public void setE_Name(String E_Name) {
                this.E_Name = E_Name;
            }

            public String getE_Memo() {
                return E_Memo;
            }

            public void setE_Memo(String E_Memo) {
                this.E_Memo = E_Memo;
            }

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
            }

            public String getE_URL() {
                return E_URL;
            }

            public void setE_URL(String E_URL) {
                this.E_URL = E_URL;
            }

            @Override
            public int getType() {
                return 2;
            }
        }
    }
}
