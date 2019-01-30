/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.domain;

import java.util.List;
import lombok.Data;

/**
 *
 * @author otahmadov
 */
@Data
public class Product {
    private int id;
    private DictionaryWrapper category;
    private MultilanguageString title;
    private MultilanguageString description;
    private String price;
    private String sale;
    private int count;
    private List<FileWrapper> images;
    private int favorite;
    public Product() {
    }

    public Product(int id, DictionaryWrapper category, MultilanguageString title, MultilanguageString description, String price, String sale, int count, List<FileWrapper> images, int favorite) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.count = count;
        this.images = images;
        this.favorite = favorite;
    }
    
}
