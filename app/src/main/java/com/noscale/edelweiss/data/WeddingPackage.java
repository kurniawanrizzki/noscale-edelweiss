package com.noscale.edelweiss.data;

import com.noscale.edelweiss.wp.TypeBuffet;

/**
 * TODO: Add class header description
 * Created by kurniawanrizzki on 15/08/20.
 */
public class WeddingPackage {

    private int id;

    private String name;

    private String price;

    private String totalBuffet;

    private TypeBuffet typeBuffet;

    private String detail;

    private String bonus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalBuffet() {
        return totalBuffet;
    }

    public void setTotalBuffet(String totalBuffet) {
        this.totalBuffet = totalBuffet;
    }

    public TypeBuffet getTypeBuffet() {
        return typeBuffet;
    }

    public void setTypeBuffet(TypeBuffet typeBuffet) {
        this.typeBuffet = typeBuffet;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
}
