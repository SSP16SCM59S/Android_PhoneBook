package com.cs442.shash5259.Assignment5;

/**
 * Created by shash on 25-02-2016.
 */
public class Order {

    private String id;
    private String id1;


    public Order(String id, String cost)
    {
        this.id = id;
        this.id1 = cost;

    }

    public String getId()
    {
        return id;
    }

    public String getItems()
    {
        return id1;
    }



}
