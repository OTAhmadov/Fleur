/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.service;

import com.product.web.dao.AdminDao;
import com.product.web.domain.About;
import com.product.web.domain.Account;
import com.product.web.domain.Achievement;
import com.product.web.domain.Career;
import com.product.web.domain.CareerApply;
import com.product.web.domain.CommonApply;
import com.product.web.domain.Company;
import com.product.web.domain.Contact;
import com.product.web.domain.Corporative;
import com.product.web.domain.DictionaryWrapper;
import com.product.web.domain.Event;
import com.product.web.domain.FileWrapper;
import com.product.web.domain.Modules;
import com.product.web.domain.OperationResponse;
import com.product.web.domain.Product;
import com.product.web.domain.Promotation;
import com.product.web.domain.Promotation;
import com.product.web.domain.Roles;
import com.product.web.domain.Wishlist;
import com.product.web.form.AboutForm;
import com.product.web.form.AccountForm;
import com.product.web.form.AchievementForm;
import com.product.web.form.CareerApplyForm;
import com.product.web.form.CareerForm;
import com.product.web.form.CommonApplyForm;
import com.product.web.form.ContactForm;
import com.product.web.form.DictionaryWrapperForm;
import com.product.web.form.EventForm;
import com.product.web.form.FileWrapperForm;
import com.product.web.form.LoginForm;
import com.product.web.form.ProductForm;
import com.product.web.form.PromotationForm;
import com.product.web.form.RoleForm;
import com.product.web.form.ServiceForm;
import com.product.web.form.WishlistForm;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Orkhan
 */
@Service
public class ProductService implements IProductService {
    private static Logger log = Logger.getLogger(ProductService.class);

    @Autowired
    private AdminDao adminDao;
    
    @Override
    public Account doLogin(LoginForm form) {
        return this.adminDao.doLogin(form);
    }
    
    @Override
    public List<DictionaryWrapper> getDictionaryTypeList() {
        return this.adminDao.getDictionaryTypeList();
    }
    
    @Override
    public List<DictionaryWrapper> getDictionaryList(int dicTypeId){
        return this.adminDao.getDictionaryList(dicTypeId);
    }
    
    @Override
    public List<DictionaryWrapper> getDictionaryListByParent(int parentId){
        return this.adminDao.getDictionaryListByParent(parentId);
    }
    
    @Override
    public OperationResponse NDUDictionary(DictionaryWrapperForm form, int accountId){
        return this.adminDao.NDUDictionary(form, accountId);
    }
    
    @Override
    public List<Contact> getContactList() {
        return this.adminDao.getContactList();
    }
    
    @Override
    public Contact getContactDetails(int id) {
        return this.adminDao.getContactDetails(id);
    }
    
    @Override
    public OperationResponse NDUContact(ContactForm form, int accountId){
        return this.adminDao.NDUContact(form, accountId);
    }
    
    @Override
    public About getAbout() {
        return this.adminDao.getAbout();
    }
    
    @Override
    public OperationResponse NDUAbout(AboutForm form, int accountId){
        return this.adminDao.NDUAbout(form, accountId);
    }
    
    @Override
    public List<Account> getAccountList(){
        return this.adminDao.getAccountList();
    }
    
    @Override
    public Account getAccountDetails(int id) {
        return this.adminDao.getAccountDetails(id);
    }
    
    @Override
    public OperationResponse NDUAccount(AccountForm form, int accountId) {
        return this.adminDao.NDUAccount(form, accountId);
    }
    
    @Override
    public FileWrapper getFileByPath(String path) {
        return this.adminDao.getFileByPath(path);
    }
    
    @Override
    public OperationResponse removeFile(int accountId, String path) {
        return this.adminDao.removeFile(accountId, path);
    }
    
    @Override
     public OperationResponse changeFileDetails(FileWrapperForm form) {
        return this.adminDao.changeFileDetails(form);
    }
    
    
    @Override
    public OperationResponse addProductFile(ProductForm form) {
        return this.adminDao.addProductFile(form);
    }
    
    @Override
    public OperationResponse addOtherFile(Account account, FileWrapperForm form) {
        return this.adminDao.addOtherFile(account, form);
    }
    
    @Override
    public List<FileWrapper> getOtherFile() {
        return this.adminDao.getOtherFile();
    }
    
    @Override
    public List<Product> getProductList(ProductForm form) {
        return this.adminDao.getProductList(form);
    }
    
    @Override
    public Product getProductDetails(int productId, int type) {
        return this.adminDao.getProductDetails(productId, type);
    }
    
    @Override
    public List<FileWrapper> getProductFileList(int productId, int type) {
        return this.adminDao.getProductFileList(productId, type);
    }
    
    @Override
    public OperationResponse nduProduct(ProductForm form, int accountId) {
        return this.adminDao.nduProduct(form, accountId);
    }
    
    @Override
    public List<DictionaryWrapper> getCategoryListWithCount() {
        return this.adminDao.getCategoryListWithCount();
    }

    @Override
    public int getProductCount(int typeId, String name) {
        return this.adminDao.getProductCount(typeId, name);
    }

    @Override
    public List<Event> getEventList() {
        return this.adminDao.getEventList();
    }

    @Override
    public Event getEventDetails(int id) {
        return this.adminDao.getEventDetails(id);
    }

    @Override
    public List<Event> getEventListForCommon() {
        return this.adminDao.getEventListForCommon();
    }

    @Override
    public OperationResponse NDUEvent(EventForm form, int accountId) {
        return this.adminDao.NDUEvent(form, accountId);
    }
    
    @Override
    public List<Promotation> getPromotationList() {
        return this.adminDao.getPromotationList();
    }
    
    @Override
    public List<Promotation> getPromotationListForCommon() {
        return this.adminDao.getPromotationListForCommon();
    }
    
    @Override
    public Promotation getPromotationDetails(int id) {
        return this.adminDao.getPromotationDetails(id);
    }

    @Override
    public OperationResponse NDUPromotation(PromotationForm form, int accountId) {
        return this.adminDao.NDUPromotation(form, accountId);
    }

    @Override
    public OperationResponse addFile(FileWrapperForm form) {
        return this.adminDao.addFile(form);
    }

    @Override
    public OperationResponse insertCommonApply(CommonApplyForm form){
        return this.adminDao.insertCommonApply(form);
    }

    @Override
    public List<CommonApply> getCommonApplyList()  {
        return this.adminDao.getCommonApplyList();
    }

    @Override
    public CommonApply getCommonApplyDetail(int id) {
        return this.adminDao.getCommonApplyDetail(id);
    }

    @Override
    public OperationResponse NDURole(RoleForm form, int accountId) {
        return this.adminDao.NDURole(form, accountId);
    }

    @Override
    public List<Roles> getRoleList() {
        return this.adminDao.getRoleList();
    }

    @Override
    public Roles getRoleDetails(int id) {
        return this.adminDao.getRoleDetails(id);
    }

    @Override
    public List<Modules> getModuleList() {
        return this.adminDao.getModuleList();
    }

    @Override
    public OperationResponse NDWishlist(WishlistForm form, int accountId){
        return this.adminDao.NDWishlist(form, accountId);
    }

    @Override
    public List<Wishlist> getWishlistByUserId(int accountId) {
        return this.adminDao.getWishlistByUserId(accountId);
    }

    @Override
    public OperationResponse NDBasket(WishlistForm form, String ssid){
        return this.adminDao.NDBasket(form, ssid);
    }

    @Override
    public List<Wishlist> getBasketBySsid(String ssid) {
        return this.adminDao.getBasketBySsid(ssid);
    }

    @Override
    public OperationResponse createDefaultSession(String ssid) {
        return this.adminDao.createDefaultSession(ssid);
    }
    
}
