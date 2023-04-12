package com.kemia.myapplication.Data;

import android.provider.BaseColumns;

public class DBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DBContract() {}

    /* Inner class that defines the table contents */
    public static class LSEntry implements BaseColumns {
        public static final String TABLE_NAME = "LICH_SU";

        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_TITLE = "TIEU_DE";
        public static final String COLUMN_NAME_DUONG_DAN = "DUONG_DAN";
        public static final String COLUMN_NAME_DC_ANH = "DC_ANH";
        public static final String COLUMN_NAME_MO_TA = "MO_TA";
        public static final String COLUMN_NAME_TG_NHAN = "TG_NHAN";
        public static final String COLUMN_NAME_IMG = "IMG";
    }
}
