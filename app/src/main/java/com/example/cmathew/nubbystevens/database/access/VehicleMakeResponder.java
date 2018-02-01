package com.example.cmathew.nubbystevens.database.access;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.example.cmathew.nubbystevens.entity.VehicleMake;

public class VehicleMakeResponder implements Func1<Cursor, VehicleMake> {
    @Nullable
    @Override
    public TaskStart call(Cursor cursor) {
        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeParser();
        int startedAtIndex = cursor.getColumnIndexOrThrow(TaskStartContract.TaskStartEntry.COLUMN_NAME_STARTED_AT);
        String startedAtString = cursor.getString(startedAtIndex);
        if (startedAtString == null) {
            return null;
        }

        DateTime startedAt = fmt.parseDateTime(startedAtString);

        int taskIdIndex = cursor.getColumnIndexOrThrow(TaskStartEntry.COLUMN_NAME_TASK_ID);
        int taskId = cursor.getInt(taskIdIndex);

        RecordedLocationResponder locationResponder = new RecordedLocationResponder();
        RecordedLocation location = locationResponder.call(cursor);

        return new TaskStart(startedAt, location, taskId);
    }
}