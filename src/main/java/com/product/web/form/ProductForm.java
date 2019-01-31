/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.form;

import lombok.Data;

/**
 *
 * @author otahmadov
 */
@Data
public class ProductForm {
    private int id;
    private int categoryId;
    private String price;
    private String sale;
    private String titleAz;
    private String titleEn;
    private String titleRu;
    private String descriptionAz;
    private String descriptionEn;
    private String descriptionRu;
    private int count;
    private Integer[] fileId;
    private int page = 1;
    private int pageCount = 50;
    private int type;
    private int favorite;
    
}
