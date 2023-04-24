package com.kemia.myapplication.DataYT;

import android.provider.BaseColumns;

public class DBContractyt {
    private DBContractyt() {}
    public static class YTEntry implements BaseColumns {
        public static final String TABLE_NAME = "YEU_THICH";

        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_TITLE = "TIEU_DE";
        public static final String COLUMN_NAME_DUONG_DAN = "DUONG_DAN";
        public static final String COLUMN_NAME_DC_ANH = "DC_ANH";
        public static final String COLUMN_NAME_MO_TA = "MO_TA";
        public static final String COLUMN_NHAN_BUTTON = "NHAN_NUT";
        public static final String COLUMN_NAME_IMG = "IMG";
    }

}
