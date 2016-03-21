package com.firstsputnik.stargazer.Provider.meet;

import com.firstsputnik.stargazer.Provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * meet dates.
 */
public interface MeetModel extends BaseModel {

    /**
     * date and time
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDatetime();
}
