package com.firstsputnik.stargazer.Provider.meet;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firstsputnik.stargazer.Provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code meet} table.
 */
public class MeetCursor extends AbstractCursor implements MeetModel {
    public MeetCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MeetColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * date and time
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDatetime() {
        Date res = getDateOrNull(MeetColumns.DATETIME);
        if (res == null)
            throw new NullPointerException("The value of 'datetime' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
