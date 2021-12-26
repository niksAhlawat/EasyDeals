package com.pickpamphlet.easydeals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import com.pickpamphlet.easydeals.utilis.Model_Form1;
import com.pickpamphlet.easydeals.utilis.Model_Form1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by panshul on 19/9/17.
 */


public class DatabaseAccess {
    private SQLiteDatabase database;
    private DatabaseOpenHelper openHelper;
    private static volatile DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void view() {
        this.database = openHelper.getReadableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void save(Model_Form1 model_form1) {
        ContentValues values = new ContentValues();
        values.put("date", model_form1.getDate());
        values.put("name", model_form1.getName());
        values.put("address", model_form1.getAddress());
        values.put("locality", model_form1.getLocality());
        values.put("demand", model_form1.getDemand());
        values.put("area", model_form1.getArea());
        values.put("desc", model_form1.getDesc());
        values.put("dealer", model_form1.getDealer());
        values.put("gp1", model_form1.getGp1());
        values.put("gp2", model_form1.getGp2());
        values.put("sd", model_form1.getSd());

        database.insert(DatabaseOpenHelper.TABLE1, null, values);
    }

    public void save1(Model_Form1 model_form1) {
        ContentValues values = new ContentValues();
        values.put("date", model_form1.getDate());
        values.put("name", model_form1.getName1());
        values.put("locality", model_form1.getLocality1());
        values.put("budget", model_form1.getBudget1());
        values.put("area", model_form1.getArea1());
        values.put("desc", model_form1.getDesc1());
        values.put("dealer", model_form1.getDealer());
        values.put("gp1", model_form1.getGp11());
        values.put("gp2", model_form1.getGp12());
        values.put("sd", model_form1.getSd());


        database.insert(DatabaseOpenHelper.TABLE2, null, values);
    }

    public void update(Model_Form1 model_form1) {
        ContentValues values = new ContentValues();
        values.put("date", model_form1.getDate());
        values.put("name", model_form1.getName());
        values.put("address", model_form1.getAddress());
        values.put("locality", model_form1.getLocality());
        values.put("demand", model_form1.getDemand());
  //      values.put("budget1", model_form1.getBudget());
        values.put("dealer", model_form1.getDealer());
        values.put("area", model_form1.getArea());
        values.put("desc", model_form1.getDesc());
        values.put("gp1", model_form1.getGp1());
        values.put("gp2", model_form1.getGp2());
        values.put("sd", model_form1.getSd());

        String id = Long.toString(model_form1.getId());
        database.update(DatabaseOpenHelper.TABLE1, values, "id = ?", new String[]{id});
    }

    public void updateparty(Model_Form1 model_form1) {
        ContentValues values = new ContentValues();
        values.put("date", model_form1.getDate());
        values.put("name", model_form1.getName1());
        values.put("locality", model_form1.getLocality1());
        values.put("budget", model_form1.getBudget1());
        values.put("area", model_form1.getArea1());
        values.put("desc", model_form1.getDesc1());
        values.put("dealer", model_form1.getDealer());
        values.put("gp1", model_form1.getGp11());
        values.put("gp2", model_form1.getGp12());
        values.put("sd", model_form1.getSd());
        String id = Long.toString(model_form1.getId());
        database.update(DatabaseOpenHelper.TABLE2, values, "id = ?", new String[]{id});
    }


    public void delete(Model_Form1 model_form1) {
        String ID = Long.toString(model_form1.getId());
        database.delete(DatabaseOpenHelper.TABLE1, "id = ?", new String[]{ID});
    }

    public void deleteparty(Model_Form1 model_form1) {
        String ID = Long.toString(model_form1.getId());
        database.delete(DatabaseOpenHelper.TABLE2, "id = ?", new String[]{ID});
    }

    public List<Model_Form1> getAllModel_Form3( String table1, String table2) {

        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        String selectQuery1 = "SELECT  * from " + table1 + " ORDER BY id DESC";
        String selectQuery2 = "SELECT  * from " + table2 + " ORDER BY id DESC";
        if (table1 == "form1") {
            Cursor cursor = database.rawQuery(selectQuery1, null);
            model_forms1 = parseCursor(cursor);
            cursor.close();
        } else if (table2 == "form2d"){
            Cursor cursor1 = database.rawQuery(selectQuery2, null);
            model_forms1 = parseCursor1(cursor1);
            cursor1.close();
        }
        return model_forms1;
    }

    public List<Model_Form1> parseCursor(Cursor cursor)
    {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Model_Form1 model = new Model_Form1();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setLocality(cursor.getString(2));
                model.setDemand(cursor.getString(3));
                model.setArea(cursor.getString(4));
                model.setDesc(cursor.getString(6));
                model.setGp1(cursor.getString(7));
                model.setGp2(cursor.getString(8));
                model.setGp3(cursor.getString(9));
                model_forms1.add(model);

                cursor.moveToNext();
            }
        cursor.close();
        return model_forms1;
    }

    public List<Model_Form1> parseCursor1(Cursor cursor)
    {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            while (!cursor.isAfterLast()) {
                String dealer = cursor.getString(0);
                String name = cursor.getString(1);
                String locality = cursor.getString(2);
                String budget = cursor.getString(3);
                String area = cursor.getString(4);
                String desc = cursor.getString(5);
                String gp1 = cursor.getString(6);
                String gp2 = cursor.getString(7);
                String gp3 = cursor.getString(8);
           //     model_forms1.add(new Model_Form1(Model_Form1.PARTY, dealer, name, locality, budget, area, desc, gp1, gp2, gp3));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return model_forms1;
    }

    public List<Model_Form1> getAllModel_Form1() {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        Cursor cursor = database.rawQuery("SELECT * From form1 ORDER BY id DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String dealer = cursor.getString(2);
            String name = cursor.getString(3);
            String address = cursor.getString(4);
            String locality = cursor.getString(5);
            String demand = cursor.getString(6);
            String area = cursor.getString(7);
            String desc = cursor.getString(8);
            String gp1 = cursor.getString(9);
            String gp2 = cursor.getString(10);
            String sd = cursor.getString(11);

            model_forms1.add(new Model_Form1(id, date, dealer, name, address, locality, demand, area, desc, gp1, gp2, sd));
            cursor.moveToNext();
        }
        cursor.close();
        return model_forms1;
    }


    public List<Model_Form1> getAllModel_Form2() {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        Cursor cursor = database.rawQuery("SELECT * From form2 ORDER BY id DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String dealer = cursor.getString(2);
            String name = cursor.getString(3);
            String locality = cursor.getString(4);
            String budget = cursor.getString(5);
            String area = cursor.getString(6);
            String desc = cursor.getString(7);
            String gp1 = cursor.getString(8);
            String gp2 = cursor.getString(9);
            String sd = cursor.getString(10);
            model_forms1.add(new Model_Form1(id, date, dealer, name, locality, budget, area, desc, gp1, gp2, sd));
            cursor.moveToNext();
        }
        cursor.close();
        return model_forms1;
    }


    private void importDB() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.pickpamphlet.easydeals"
                        + "//databases//" + "easydeals.db";
                String backupDBPath = "<backup.db"; // From SD directory.
                File backupDB = new File(data, currentDBPath);
                File currentDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(backupDB).getChannel();
                FileChannel dst = new FileOutputStream(currentDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
        //        Toast.makeText(getApplicationContext(), "Import Successful!", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

    //        Toast.makeText(getApplicationContext(), "Import Failed!", Toast.LENGTH_SHORT).show();

        }
    }

    private void exportDB() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.pickpamphlet.easydeals"
                        + "//databases//" + "backup.db";
                String backupDBPath = "<destination>";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
           //     Toast.makeText(getApplicationContext(), "Backup Successful!", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

     //       Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT).show();

        }
    }

    /*
    public Cursor  getListByKeyword(String search) {
        //Open connection to read only
         String selectQuery = "SELECT * FROM form1 WHERE name LIKE '%" +search + "%' OR locality LIKE '%" +search + "%'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
}

    public List<Model_Form1> getListByKeyword( String search) {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        String selectQuery = "SELECT * FROM form1 WHERE name LIKE '%" +search + "%' OR locality LIKE '%" +search + "%'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String dealer = cursor.getString(1);
            String name = cursor.getString(2);
            String address = cursor.getString(3);
            String locality = cursor.getString(4);
            String demand = cursor.getString(5);
            String area = cursor.getString(6);
            String desc = cursor.getString(7);
            String gp1 = cursor.getString(8);
            String gp2 = cursor.getString(9);
            String sd = cursor.getString(10);

            model_forms1.add(new Model_Form1(id, dealer, name, address, locality, demand, area, desc, gp1, gp2, sd));
            cursor.moveToNext();
        }
        cursor.close();
        return model_forms1;
    }

    public List<Model_Form1> getListByKeyword2( String search) {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        String selectQuery = "SELECT * FROM form2 WHERE name LIKE '%" +search + "%' OR locality LIKE '%" +search + "%'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String dealer = cursor.getString(1);
            String name = cursor.getString(2);
            String locality = cursor.getString(3);
            String budget = cursor.getString(4);
            String area = cursor.getString(5);
            String desc = cursor.getString(6);
            String gp1 = cursor.getString(7);
            String gp2 = cursor.getString(8);
            String sd = cursor.getString(9);
            model_forms1.add(new Model_Form1(id, dealer, name, locality, budget, area, desc, gp1, gp2, sd));
            cursor.moveToNext();
        }
        cursor.close();
        return model_forms1;
    }

    public List<Model_Form1> getByProgress( int budget) {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        String selectQuery = "SELECT * FROM form1 WHERE demand LIKE '%" + budget+ "%'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String dealer = cursor.getString(1);
            String name = cursor.getString(2);
            String address = cursor.getString(3);
            String locality = cursor.getString(4);
            String demand = cursor.getString(5);
            String area = cursor.getString(6);
            String desc = cursor.getString(7);
            String gp1 = cursor.getString(8);
            String gp2 = cursor.getString(9);
            String sd = cursor.getString(10);

            model_forms1.add(new Model_Form1(id, dealer, name, address, locality, demand, area, desc, gp1, gp2, sd));
            cursor.moveToNext();
        }
        cursor.close();
        return model_forms1;
    }

    public List<Model_Form1> getByProgress2( int search) {
        List<Model_Form1> model_forms1 = new ArrayList<Model_Form1>();
        String selectQuery = "SELECT * FROM form2 WHERE budget LIKE '%" +search + "%'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String dealer = cursor.getString(1);
            String name = cursor.getString(2);
            String locality = cursor.getString(3);
            String budget = cursor.getString(4);
            String area = cursor.getString(5);
            String desc = cursor.getString(6);
            String gp1 = cursor.getString(7);
            String gp2 = cursor.getString(8);
            String sd = cursor.getString(9);
            model_forms1.add(new Model_Form1(id, dealer, name, locality, budget, area, desc, gp1, gp2, sd));
            cursor.moveToNext();
        }
        cursor.close();
        return model_forms1;
    }
*/
}

