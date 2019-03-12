package com.aquar.myaquar_egypt.KeyAndValueClass;

public class KeyAndValueOfSearchResult {
    String category_id;
    String max_price;
    String min_price;
    String max_area;
    String min_area;
    String max_badrooms;
    String min_badrooms;
    String max_bathrooms;
    String min_bathrooms;

    public KeyAndValueOfSearchResult(String category_id, String max_price, String min_price, String max_area,
                                     String min_area, String max_badrooms, String min_badrooms, String max_bathrooms,
                                     String min_bathrooms, String locations) {
        this.category_id = category_id;
        this.max_price = max_price;
        this.min_price = min_price;
        this.max_area = max_area;
        this.min_area = min_area;
        this.max_badrooms = max_badrooms;
        this.min_badrooms = min_badrooms;
        this.max_bathrooms = max_bathrooms;
        this.min_bathrooms = min_bathrooms;
        this.locations = locations;
    }

    String locations ;



}
