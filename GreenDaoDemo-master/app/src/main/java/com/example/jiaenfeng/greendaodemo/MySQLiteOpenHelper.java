package com.example.jiaenfeng.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * greendao升级帮助类
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
       // MigrationHelper.migrate(db, UserDao.class);
    }
}