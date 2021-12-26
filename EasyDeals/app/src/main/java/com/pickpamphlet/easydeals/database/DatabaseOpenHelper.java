package com.pickpamphlet.easydeals.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.pickpamphlet.easydeals.utilis.DatabaseContext;

import java.io.File;


/**
 * Created by panshul on 19/9/17.
 */

class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "easydeals.db";


    public static final String TABLE1 = "form1";
    public static final String TABLE2 = "form2";
    private static final String KEY_ID = "id";

    private static final String GP1 = "gp1";
    private static final String GP2 = "gp2";
    private static final String GP3 = "gp3";
    private static final String SD = "sd";
    public static final String DATE = "date";

    // ADMIN COLOUMN NAMES
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String LOCALITY = "locality";
    public static final String DEMAND = "demand";
    public static final String AREA = "area";
    public static final String DESC = "desc";


    public static final int VERSION = 1;

    public DatabaseOpenHelper(final Context context) {
        super(new DatabaseContext(context), DATABASE, null, VERSION);
    }

/*

    public DatabaseOpenHelper(final Context context) {
        super(context, Environment.getExternalStorageDirectory() + File.separator
        + FILE_DIR + File.separator + BACKUP_DIR + File.separator + DATABASE, null, VERSION);
    }

*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE form2(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, dealer TEXT, name TEXT, locality TEXT, budget TEXT, " +
                "area TEXT, desc TEXT, gp1 TEXT, gp2 TEXT, sd TEXT);");

        db.execSQL("CREATE TABLE form1(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, dealer TEXT, name TEXT, address TEXT, locality TEXT, " +
                "demand TEXT, area TEXT, desc TEXT, gp1 TEXT, gp2 TEXT, sd TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}



