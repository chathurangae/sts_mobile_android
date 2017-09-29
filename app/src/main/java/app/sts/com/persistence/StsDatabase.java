package app.sts.com.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = StsDatabase.NAME, version = StsDatabase.VERSION)
public class StsDatabase {
    static final String NAME = "STS_DB";
    static final int VERSION = 1;
}
