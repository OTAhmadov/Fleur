/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.controller;

import com.product.web.domain.Account;
import com.product.web.domain.OperationResponse;
import com.product.web.enums.ResultCode;
import com.product.web.service.FtpService;
import com.product.web.service.ProductService;
import java.util.UUID;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author otahmadov
 */
@Controller
public class SkeletonController extends AbstractController {

    @Autowired
    protected HttpServletRequest request;
    
    @Autowired
    protected ProductService service;
    
    @Autowired
    protected FtpService ftpService;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected Account getSessionAccount() {
        return (Account) request.getSession().getAttribute("account");
    }
    
    protected Account getSessionAccount(OperationResponse operationResponse) throws Exception {
        Account account = getSessionAccount();
        
        if(account == null) {
            operationResponse.setCode(ResultCode.UNAUTHORIZED);
            throw new Exception("Account object in session is null.");
        }
        
        return account;
    }
    protected String getDefaultSessionAccount(OperationResponse operationResponse) throws Exception {
        String ssid = (String) request.getSession().getAttribute("ssid");
        
        if(ssid == null || ssid.trim().isEmpty()) {
            UUID uuid1 = UUID.randomUUID();
            UUID uuid2 = UUID.randomUUID();
            String guid = uuid1.toString().replaceAll("-", "") + uuid2.toString().replaceAll("-", "");
            operationResponse = service.createDefaultSession(ssid);
            if(!operationResponse.getCode().equals(ResultCode.OK)) {
                operationResponse.setCode(ResultCode.ERROR);
                throw new Exception("Sessiya yaranmadi");
            }
            
            return guid;
                
        }
        
        return ssid;
    }
}
