package com.example.cmathew.nubbystevens.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cmathew.nubbystevens.R;

/**
 * Created by chris on 1/29/2018.
 */

public class DealershipHelper extends SQLiteOpenHelper {
    // DB Version
    private static final int DATABASE_VERSION = 1;

    public DealershipHelper(Context context) {
        super(context, context.getResources().getString(R.string.database_name), null, DATABASE_VERSION);
    }

    // Create original state, then migrate to target
    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DATABASE_VERSION);
    }

    private boolean migrationNeeded(int oldVersion, int newVersion, int migrationIndex) {
        return oldVersion < migrationIndex && newVersion >= migrationIndex;
    }

    // Apply incremental schema changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (migrationNeeded(oldVersion, newVersion, 1)) {
            //UiCheckpointDbClient.CreateTable(db);
        }
    }

}
