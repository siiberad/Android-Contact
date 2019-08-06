package com.siiberad.sproutdigital.model;

import java.util.ArrayList;
import java.util.List;

public class ContactModel {

    private float page;
    private float per_page;
    private float total;
    private float total_pages;
    private ArrayList<DataModel> data;

    public float getPage() {
        return page;
    }

    public void setPage(float page) {
        this.page = page;
    }

    public float getPer_page() {
        return per_page;
    }

    public void setPer_page(float per_page) {
        this.per_page = per_page;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(float total_pages) {
        this.total_pages = total_pages;
    }


    public ArrayList<DataModel> getData() {
        return data;
    }

    public void setData(ArrayList<DataModel> data) {
        this.data = data;
    }
}

