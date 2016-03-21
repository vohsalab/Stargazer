package com.firstsputnik.stargazer.Provider.meet;

import android.net.Uri;
import android.provider.BaseColumns;

import com.firstsputnik.stargazer.Provider.IssMeetProvider;
import com.firstsputnik.stargazer.Provider.meet.MeetColumns;

/**
 * meet dates.
 */
public class MeetColumns implements BaseColumns {
    public static final String TABLE_NAME = "meet";
    public static final Uri CONTENT_URI = Uri.parse(IssMeetProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * date and time
     */
    public static final String DATETIME = "datetime";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            DATETIME
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(DATETIME) || c.contains("." + DATETIME)) return true;
        }
        return false;
    }

}
