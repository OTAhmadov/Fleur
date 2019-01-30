/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.domain;

import lombok.Data;

/**
 *
 * @author otahamdov
 */
@Data
public class Wishlist {
    private int id;
    private Account account;
    private Product product;

    public Wishlist(int id, Account account, Product product) {
        this.id = id;
        this.account = account;
        this.product = product;
    }
}
