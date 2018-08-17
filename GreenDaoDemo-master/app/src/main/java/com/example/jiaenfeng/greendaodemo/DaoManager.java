package com.example.jiaenfeng.greendaodemo;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by jiaenfeng on 17/9/30.
 */

public class DaoManager {
    private static final String DB_NAME = "db_test";
    private volatile static DaoManager manager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private DaoManager() {
    }
    public static DaoManager getInstance() {
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (manager == null) {
                    manager = new DaoManager();
                }
            }
        }
        return manager;
    }
    public DaoSession getDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
        }
        if (daoSession == null) {
            synchronized (DaoManager.class) {
                if (daoMaster == null) {
                    daoMaster = getDaoMaster();
                }
                daoSession = daoMaster.newSession();
            }
        }
        return daoSession;
    }
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
            // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(MyApplication.mMyApplication, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
            //daoMaster = new DaoMaster(helper.getEncryptedWritableDb("123")); //加密 uuid
        }
        return daoMaster;
    }
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }
    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }
    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }
}
