package com.example.user.myapplication.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class AlarmReminderContract {

    public static final String CONTENT_AUTHORITY = "com.example.user.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MEDICINES = "reminder-path";

    private AlarmReminderContract() {
    }

    public static String getColumnString(Cursor cursor, String columnName) {

        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static final class AlarmReminderEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MEDICINES);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEDICINES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEDICINES;

        public final static String TABLE_NAME = "medicines";

        public final static String _ID = BaseColumns._ID;

        public static final String KEY_TITLE = "title";
        public static final String KEY_DATE = "date";
        public static final String KEY_TIME = "time";
        public static final String KEY_REPEAT = "repeat";
        public static final String KEY_REPEAT_NO = "repeat_no";
        public static final String KEY_REPEAT_TYPE = "repeat_type";
        public static final String KEY_ACTIVE = "active";

    }

}
