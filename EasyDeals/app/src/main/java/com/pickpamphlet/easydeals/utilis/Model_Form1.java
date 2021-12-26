package com.pickpamphlet.easydeals.utilis;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by panshul on 21/9/17.
 */

public class Model_Form1 implements Serializable{

    public static final int OWNER=0;
    public static final int PARTY=1;

    public int id, type;
    private String  name, address, locality, demand, budget, area, desc, gp1, gp2, gp3;
    private String  name1, locality1, budget1, area1, desc1, gp11, gp12, gp13, dealer;
    private String sd, date;
 //   private Date date;
 //   private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // constructors
    public Model_Form1() {

    }

    public Model_Form1(int id, String date, String dealer, String name, String address, String locality, String demand, String area, String desc,
                       String gp1, String gp2, String sd ) {
        this.id = id;
        this.date = date;
        this.dealer = dealer;
        this.name = name;
        this.address = address;
        this.locality = locality;
        this.demand = demand;
        this.area = area;
        this.desc = desc;
        this.gp1 = gp1;
        this.gp2 = gp2;
        this.sd = sd;
    }

    public Model_Form1(int id, String date,  String dealer, String name1, String locality1, String budget1, String area1, String desc1,
                       String gp11, String gp12, String sd) {
        this.id = id;
        this.date = date;
        this.dealer = dealer;
        this.name1 = name1;
        this.locality1 = locality1;
        this.budget1 = budget1;
        this.area1 = area1;
        this.desc1 = desc1;
        this.gp11 = gp11;
        this.gp12 = gp12;
        this.sd = sd;
    }

    // setters

    public void setDate( String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setGp1(String gp1) {
        this.gp1 = gp1;
    }

    public void setGp2(String gp2) {
        this.gp2 = gp2;
    }

    public void setGp3(String gp3) {
        this.gp3 = gp3;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

//  ###########################################################################################################

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setLocality1(String locality1) {
        this.locality1 = locality1;
    }

    public void setBudget1(String budget1) {
        this.budget1 = budget1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public void setGp11(String gp11) {
        this.gp11 = gp11;
    }

    public void setGp12(String gp12) {
        this.gp12 = gp12;
    }

    public void setGp13(String gp13) {
        this.gp13 = gp13;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getDealer() {
        return this.dealer;
    }

    public String getAddress() {
        return this.address;
    }

    public String getLocality() {
        return this.locality;
    }

    public String getDemand() {
        return this.demand;
    }

    public String getBudget() {
        return this.budget;
    }

    public String getArea() {
        return this.area;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getGp1() {
        return this.gp1;
    }

    public String getGp2() {
        return this.gp2;
    }

    public String getGp3() {
        return this.gp3;
    }

    public String getSd() {
        return this.sd;
    }


    // 3#######################################################################################################3

    public String getName1() {
        return this.name1;
    }

    public String getLocality1() {
        return this.locality1;
    }

    public String getBudget1() {
        return this.budget1;
    }

    public String getArea1() {
        return this.area1;
    }

    public String getDesc1() {
        return this.desc1;
    }


    public String getGp11() {
        return this.gp11;
    }

    public String getGp12() {
        return this.gp12;
    }

    public String getGp13() {
        return this.gp13;
    }
}
