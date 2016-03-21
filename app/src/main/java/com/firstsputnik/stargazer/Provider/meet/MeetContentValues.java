package com.firstsputnik.stargazer.Provider.meet;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firstsputnik.stargazer.Provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code meet} table.
 */
public class MeetContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MeetColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MeetSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MeetSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * date and time
     */
    public MeetContentValues putDatetime(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("datetime must not be null");
        mContentValues.put(MeetColumns.DATETIME, value.getTime());
        return this;
    }


    public MeetContentValues putDatetime(long value) {
        mContentValues.put(MeetColumns.DATETIME, value);
        return this;
    }
}
