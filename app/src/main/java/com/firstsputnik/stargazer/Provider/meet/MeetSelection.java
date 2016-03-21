package com.firstsputnik.stargazer.Provider.meet;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.firstsputnik.stargazer.Provider.base.AbstractSelection;

/**
 * Selection for the {@code meet} table.
 */
public class MeetSelection extends AbstractSelection<MeetSelection> {
    @Override
    protected Uri baseUri() {
        return MeetColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MeetCursor} object, which is positioned before the first entry, or null.
     */
    public MeetCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MeetCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MeetCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MeetCursor} object, which is positioned before the first entry, or null.
     */
    public MeetCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MeetCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MeetCursor query(Context context) {
        return query(context, null);
    }


    public MeetSelection id(long... value) {
        addEquals("meet." + MeetColumns._ID, toObjectArray(value));
        return this;
    }

    public MeetSelection idNot(long... value) {
        addNotEquals("meet." + MeetColumns._ID, toObjectArray(value));
        return this;
    }

    public MeetSelection orderById(boolean desc) {
        orderBy("meet." + MeetColumns._ID, desc);
        return this;
    }

    public MeetSelection orderById() {
        return orderById(false);
    }

    public MeetSelection datetime(Date... value) {
        addEquals(MeetColumns.DATETIME, value);
        return this;
    }

    public MeetSelection datetimeNot(Date... value) {
        addNotEquals(MeetColumns.DATETIME, value);
        return this;
    }

    public MeetSelection datetime(long... value) {
        addEquals(MeetColumns.DATETIME, toObjectArray(value));
        return this;
    }

    public MeetSelection datetimeAfter(Date value) {
        addGreaterThan(MeetColumns.DATETIME, value);
        return this;
    }

    public MeetSelection datetimeAfterEq(Date value) {
        addGreaterThanOrEquals(MeetColumns.DATETIME, value);
        return this;
    }

    public MeetSelection datetimeBefore(Date value) {
        addLessThan(MeetColumns.DATETIME, value);
        return this;
    }

    public MeetSelection datetimeBeforeEq(Date value) {
        addLessThanOrEquals(MeetColumns.DATETIME, value);
        return this;
    }

    public MeetSelection orderByDatetime(boolean desc) {
        orderBy(MeetColumns.DATETIME, desc);
        return this;
    }

    public MeetSelection orderByDatetime() {
        orderBy(MeetColumns.DATETIME, false);
        return this;
    }
}
