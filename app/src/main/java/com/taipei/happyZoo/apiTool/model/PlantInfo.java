package com.taipei.happyZoo.apiTool.model;

import com.google.gson.annotations.SerializedName;
import com.taipei.happyZoo.page.houseDetail.HouseDetailAdapter;

import java.io.Serializable;
import java.util.List;

public class PlantInfo {

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

        public static class ResultsBean implements HouseDetailAdapter.DetailType, Serializable {

            private String F_Name_Latin;
            private String F_pdf02_ALT;
            private String F_Location;
            private String F_pdf01_ALT;
            private String F_Summary;
            private String F_Pic01_URL;
            private String F_pdf02_URL;
            private String F_Pic02_URL;
            private String F_Keywords;
            private String F_Code;
            private String F_Geo;
            private String F_Pic03_URL;
            private String F_Voice01_ALT;
            private String F_AlsoKnown;
            private String F_Voice02_ALT;
            private String F_Name_Ch;
            private String F_Pic04_ALT;
            private String F_Name_En;
            private String F_Brief;
            private String F_Pic04_URL;
            private String F_Voice01_URL;
            private String F_Feature;
            private String F_Pic02_ALT;
            private String F_Family;
            private String F_Voice03_ALT;
            @SerializedName("F_Function&Application")
            private String _$F_FunctionApplication141; // FIXME check this code
            private String F_Voice02_URL;
            private String F_Pic03_ALT;
            private String F_Pic01_ALT;
            private String F_CID;
            private String F_pdf01_URL;
            private String F_Vedio_URL;
            private String F_Genus;
            private String F_Voice03_URL;
            private String F_Update;
            private int _id;

            public String getF_Name_Latin() {
                return F_Name_Latin;
            }

            public void setF_Name_Latin(String F_Name_Latin) {
                this.F_Name_Latin = F_Name_Latin;
            }

            public String getF_pdf02_ALT() {
                return F_pdf02_ALT;
            }

            public void setF_pdf02_ALT(String F_pdf02_ALT) {
                this.F_pdf02_ALT = F_pdf02_ALT;
            }

            public String getF_Location() {
                return F_Location;
            }

            public void setF_Location(String F_Location) {
                this.F_Location = F_Location;
            }

            public String getF_pdf01_ALT() {
                return F_pdf01_ALT;
            }

            public void setF_pdf01_ALT(String F_pdf01_ALT) {
                this.F_pdf01_ALT = F_pdf01_ALT;
            }

            public String getF_Summary() {
                return F_Summary;
            }

            public void setF_Summary(String F_Summary) {
                this.F_Summary = F_Summary;
            }

            public String getF_Pic01_URL() {
                return F_Pic01_URL;
            }

            public void setF_Pic01_URL(String F_Pic01_URL) {
                this.F_Pic01_URL = F_Pic01_URL;
            }

            public String getF_pdf02_URL() {
                return F_pdf02_URL;
            }

            public void setF_pdf02_URL(String F_pdf02_URL) {
                this.F_pdf02_URL = F_pdf02_URL;
            }

            public String getF_Pic02_URL() {
                return F_Pic02_URL;
            }

            public void setF_Pic02_URL(String F_Pic02_URL) {
                this.F_Pic02_URL = F_Pic02_URL;
            }

            public String getF_Keywords() {
                return F_Keywords;
            }

            public void setF_Keywords(String F_Keywords) {
                this.F_Keywords = F_Keywords;
            }

            public String getF_Code() {
                return F_Code;
            }

            public void setF_Code(String F_Code) {
                this.F_Code = F_Code;
            }

            public String getF_Geo() {
                return F_Geo;
            }

            public void setF_Geo(String F_Geo) {
                this.F_Geo = F_Geo;
            }

            public String getF_Pic03_URL() {
                return F_Pic03_URL;
            }

            public void setF_Pic03_URL(String F_Pic03_URL) {
                this.F_Pic03_URL = F_Pic03_URL;
            }

            public String getF_Voice01_ALT() {
                return F_Voice01_ALT;
            }

            public void setF_Voice01_ALT(String F_Voice01_ALT) {
                this.F_Voice01_ALT = F_Voice01_ALT;
            }

            public String getF_AlsoKnown() {
                return F_AlsoKnown;
            }

            public void setF_AlsoKnown(String F_AlsoKnown) {
                this.F_AlsoKnown = F_AlsoKnown;
            }

            public String getF_Voice02_ALT() {
                return F_Voice02_ALT;
            }

            public void setF_Voice02_ALT(String F_Voice02_ALT) {
                this.F_Voice02_ALT = F_Voice02_ALT;
            }

            public String getF_Name_Ch() {
                return F_Name_Ch;
            }

            public void setF_Name_Ch(String F_Name_Ch) {
                this.F_Name_Ch = F_Name_Ch;
            }

            public String getF_Pic04_ALT() {
                return F_Pic04_ALT;
            }

            public void setF_Pic04_ALT(String F_Pic04_ALT) {
                this.F_Pic04_ALT = F_Pic04_ALT;
            }

            public String getF_Name_En() {
                return F_Name_En;
            }

            public void setF_Name_En(String F_Name_En) {
                this.F_Name_En = F_Name_En;
            }

            public String getF_Brief() {
                return F_Brief;
            }

            public void setF_Brief(String F_Brief) {
                this.F_Brief = F_Brief;
            }

            public String getF_Pic04_URL() {
                return F_Pic04_URL;
            }

            public void setF_Pic04_URL(String F_Pic04_URL) {
                this.F_Pic04_URL = F_Pic04_URL;
            }

            public String getF_Voice01_URL() {
                return F_Voice01_URL;
            }

            public void setF_Voice01_URL(String F_Voice01_URL) {
                this.F_Voice01_URL = F_Voice01_URL;
            }

            public String getF_Feature() {
                return F_Feature;
            }

            public void setF_Feature(String F_Feature) {
                this.F_Feature = F_Feature;
            }

            public String getF_Pic02_ALT() {
                return F_Pic02_ALT;
            }

            public void setF_Pic02_ALT(String F_Pic02_ALT) {
                this.F_Pic02_ALT = F_Pic02_ALT;
            }

            public String getF_Family() {
                return F_Family;
            }

            public void setF_Family(String F_Family) {
                this.F_Family = F_Family;
            }

            public String getF_Voice03_ALT() {
                return F_Voice03_ALT;
            }

            public void setF_Voice03_ALT(String F_Voice03_ALT) {
                this.F_Voice03_ALT = F_Voice03_ALT;
            }

            public String get_$F_FunctionApplication141() {
                return _$F_FunctionApplication141;
            }

            public void set_$F_FunctionApplication141(String _$F_FunctionApplication141) {
                this._$F_FunctionApplication141 = _$F_FunctionApplication141;
            }

            public String getF_Voice02_URL() {
                return F_Voice02_URL;
            }

            public void setF_Voice02_URL(String F_Voice02_URL) {
                this.F_Voice02_URL = F_Voice02_URL;
            }

            public String getF_Pic03_ALT() {
                return F_Pic03_ALT;
            }

            public void setF_Pic03_ALT(String F_Pic03_ALT) {
                this.F_Pic03_ALT = F_Pic03_ALT;
            }

            public String getF_Pic01_ALT() {
                return F_Pic01_ALT;
            }

            public void setF_Pic01_ALT(String F_Pic01_ALT) {
                this.F_Pic01_ALT = F_Pic01_ALT;
            }

            public String getF_CID() {
                return F_CID;
            }

            public void setF_CID(String F_CID) {
                this.F_CID = F_CID;
            }

            public String getF_pdf01_URL() {
                return F_pdf01_URL;
            }

            public void setF_pdf01_URL(String F_pdf01_URL) {
                this.F_pdf01_URL = F_pdf01_URL;
            }

            public String getF_Vedio_URL() {
                return F_Vedio_URL;
            }

            public void setF_Vedio_URL(String F_Vedio_URL) {
                this.F_Vedio_URL = F_Vedio_URL;
            }

            public String getF_Genus() {
                return F_Genus;
            }

            public void setF_Genus(String F_Genus) {
                this.F_Genus = F_Genus;
            }

            public String getF_Voice03_URL() {
                return F_Voice03_URL;
            }

            public void setF_Voice03_URL(String F_Voice03_URL) {
                this.F_Voice03_URL = F_Voice03_URL;
            }

            public String getF_Update() {
                return F_Update;
            }

            public void setF_Update(String F_Update) {
                this.F_Update = F_Update;
            }

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
            }

            @Override
            public int getType() {
                return 1;
            }
        }
    }
}
