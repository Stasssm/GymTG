package com.example.gym.model;

import java.util.UUID;

/**
 * Created by Stas on 22.07.2014.
 */
public class TrainExercise {

    private String name ;
    private String day ;
    private int quantity;
    private int quantity_in_one;
    private long _id ;

    public TrainExercise(String name, String day, int quant, int quant_in_one) {
        this.name = name;
        this.day = day;
        this.quantity = quant;
        this.quantity_in_one = quant_in_one;
        _id = UUID.randomUUID().getMostSignificantBits();
    }


    public TrainExercise(String name, String day, int quant, int quant_in_one,long _id) {
        this.name = name;
        this.day = day;
        this.quantity = quant;
        this.quantity_in_one = quant_in_one;
        this._id = _id;
    }

    public TrainExercise(String name, String day) {
        this.day = day;
        this.name = name;
        quantity = 0 ;
        quantity_in_one = 0;
        _id = UUID.randomUUID().getMostSignificantBits();
    }

    public long get_id() {
        return _id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity_in_one() {
        return quantity_in_one;
    }

    public void setQuantity_in_one(int quantity_in_one) {
        this.quantity_in_one = quantity_in_one;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
