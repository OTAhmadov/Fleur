/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.dao;

import com.product.web.db.DbConnect;
import com.product.web.domain.About;
import com.product.web.domain.Account;
import com.product.web.domain.CommonApply;
import com.product.web.domain.Company;
import com.product.web.domain.Contact;
import com.product.web.domain.DictionaryWrapper;
import com.product.web.domain.Event;
import com.product.web.domain.FileWrapper;
import com.product.web.domain.Modules;
import com.product.web.domain.MultilanguageString;
import com.product.web.domain.OperationResponse;
import com.product.web.domain.Product;
import com.product.web.domain.Promotation;
import com.product.web.domain.Roles;
import com.product.web.domain.Wishlist;
import com.product.web.enums.ResultCode;
import com.product.web.form.AboutForm;
import com.product.web.form.AccountForm;
import com.product.web.form.CommonApplyForm;
import com.product.web.form.ContactForm;
import com.product.web.form.DictionaryWrapperForm;
import com.product.web.form.EventForm;
import com.product.web.form.FileWrapperForm;
import com.product.web.form.LoginForm;
import com.product.web.form.ProductForm;
import com.product.web.form.PromotationForm;
import com.product.web.form.RoleForm;
import com.product.web.form.WishlistForm;
import com.product.web.util.Crypto;
import com.sun.tracing.Probe;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author otahmadov
 */
@Repository
public class AdminDao implements IAdminDao {
    private static final Logger log = Logger.getLogger(AdminDao.class);

    @Autowired
    private DbConnect dbConnect;
    
    @Override
    public Account doLogin(LoginForm form) {
        
        try(Connection connection = dbConnect.getPostgresConnection()) {
            connection.setAutoCommit(false);
            try(CallableStatement callableStatement = connection.prepareCall("{? = call do_login(?,?,?,?)}")) {
                callableStatement.registerOutParameter(1, Types.OTHER);
                callableStatement.setString(2, form.getUsername());
                callableStatement.setString(3, Crypto.base64Encode(form.getPassword()));
                callableStatement.setString(4, form.getIpadress());
                callableStatement.setString(5, form.getDevice());
                callableStatement.execute();
                connection.commit();
                try(ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                    if(resultSet.next()) {
                        return new Account(resultSet.getInt("id"), 
                                            resultSet.getString("user_name"), 
                                            resultSet.getString("first_name"), 
                                            resultSet.getString("last_name"), 
                                            resultSet.getString("middle_name"),
                                            resultSet.getString("user_type"),
                                            getRoleDetails(resultSet.getInt("role_id")));
                    }
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<DictionaryWrapper> getDictionaryTypeList() {
        List<DictionaryWrapper> list = new ArrayList<>();
        String query = "Select * from dictionary_type d where d.protect is null";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while(resultSet.next()) {
                list.add(new DictionaryWrapper(resultSet.getInt("id"), 
                                                new MultilanguageString(resultSet.getString("name"), 
                                                                        resultSet.getString("name"), 
                                                                        resultSet.getString("name"))));
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<DictionaryWrapper> getDictionaryList(int dicTypeId) {
        List<DictionaryWrapper> list = new ArrayList<>();
        String query = "Select * from dictionary d where d.dic_type_id = ? and d.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query)
                ) {
                preparedStatement.setInt(1, dicTypeId);
                
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while(resultSet.next()) {
                        list.add(new DictionaryWrapper(resultSet.getInt("id"), 
                                                        resultSet.getInt("dic_type_id"),
                                                new MultilanguageString(resultSet.getString("name_az"), 
                                                                        resultSet.getString("name_en"), 
                                                                        resultSet.getString("name_ru")),
                                                new MultilanguageString(resultSet.getString("about_az"), 
                                                                        resultSet.getString("about_en"), 
                                                                        resultSet.getString("about_ru")),
                                                resultSet.getInt("parent_id"),
                                                resultSet.getString("icon")));
                }
            
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<DictionaryWrapper> getDictionaryListByParent(int parentId) {
        List<DictionaryWrapper> list = new ArrayList<>();
        String query = "Select * from dictionary d where d.parent_id = ? and d.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query)
                ) {
                preparedStatement.setInt(1, parentId);
                
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while(resultSet.next()) {
                        list.add(new DictionaryWrapper(resultSet.getInt("id"), 
                                                        resultSet.getInt("dic_type_id"),
                                                new MultilanguageString(resultSet.getString("name_az"), 
                                                                        resultSet.getString("name_en"), 
                                                                        resultSet.getString("name_ru")),
                                                new MultilanguageString(resultSet.getString("about_az"), 
                                                                        resultSet.getString("about_en"), 
                                                                        resultSet.getString("about_ru")),
                                                resultSet.getInt("parent_id"),
                                                resultSet.getString("icon")));
                }
            
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public OperationResponse NDUDictionary(DictionaryWrapperForm form, int accountId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "{call ndu_dictionary(?,?,?,?,?,?,?)}";
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setInt(3, form.getParentId());
            callableStatement.setInt(4, form.getDicTypeId());
            callableStatement.setString(5, form.getNameAz());
            callableStatement.setString(6, form.getNameEn());
            callableStatement.setString(7, form.getNameRu());
            
            callableStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public List<Contact> getContactList() {
        List<Contact> list = new ArrayList<>();
        String query = "Select c.*, d.name_az, d.name_en, d.name_ru, d.icon from contacts c "
                        + "join dictionary d on d.id = c.type_id and d.active = 1 "
                        + "where c.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query);
                ResultSet resultSet = preparedStatement.executeQuery()
                ) {
                    while(resultSet.next()) {
                        list.add(new Contact(resultSet.getInt("id"), 
                                              new Company(1, null, null, null),
                                              new DictionaryWrapper(resultSet.getInt("type_id"), 
                                                                    new MultilanguageString(resultSet.getString("name_az"), 
                                                                                            resultSet.getString("name_en"), 
                                                                                            resultSet.getString("name_ru")),
                                                                    resultSet.getString("icon")),
                                              resultSet.getString("contact")));
                }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Contact getContactDetails(int id) {
        String query = "Select c.*, d.name_az, d.name_en, d.name_ru from contacts c "
                        + "join dictionary d on d.id = c.type_id and d.active = 1 "
                        + "where c.id = ? and c.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query)
                
                ) {
                preparedStatement.setInt(1, id);
            
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()) {
                        return new Contact(resultSet.getInt("id"), 
                                              new Company(1, null, null, null),
                                              new DictionaryWrapper(resultSet.getInt("type_id"), 
                                                                    new MultilanguageString(resultSet.getString("name_az"), 
                                                                                            resultSet.getString("name_en"), 
                                                                                            resultSet.getString("name_ru"))),
                                              resultSet.getString("contact"));
                    }
                }
                    
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public OperationResponse NDUContact(ContactForm form, int accountId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "{call ndu_contact(?,?,?,?)}";
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setInt(3, form.getTypeId());
            callableStatement.setString(4, form.getContact());
            
            callableStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public About getAbout() {
        String query = "Select * from about c where c.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query);
                ResultSet resultSet = preparedStatement.executeQuery()
                
                ) {
                    if(resultSet.next()) {
                        return new About(resultSet.getInt("id"), 
                                            new MultilanguageString(resultSet.getString("title_az"), 
                                                                    resultSet.getString("title_en"), 
                                                                    resultSet.getString("title_ru")),
                                            new MultilanguageString(resultSet.getString("content_az"), 
                                                                    resultSet.getString("content_en"), 
                                                                    resultSet.getString("content_ru")));
                    }
                    
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public OperationResponse NDUAbout(AboutForm form, int accountId) {
         OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
            String query = "{call ndu_about(?,?,?,?,?,?,?,?)}";
            try(Connection connection = dbConnect.getPostgresConnection();
                CallableStatement callableStatement = connection.prepareCall(query)) {
                callableStatement.setInt(1, accountId);
                callableStatement.setInt(2, form.getId());
                callableStatement.setString(3, form.getTitleAz());
                callableStatement.setString(4, form.getTitleEn());
                callableStatement.setString(5, form.getTitleRu());
                callableStatement.setString(6, form.getContentAz());
                callableStatement.setString(7, form.getContentEn());
                callableStatement.setString(8, form.getContentRu());

                callableStatement.executeUpdate();
                operationResponse.setCode(ResultCode.OK);
            } 
            catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        return operationResponse;
    }
    
    @Override
    public List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();
        String query = "Select * from accounts c where c.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query);
                ResultSet resultSet = preparedStatement.executeQuery()
                ) {
                    while(resultSet.next()) {
                        list.add(new Account(resultSet.getInt("id"), 
                                                resultSet.getString("user_name"), 
                                                resultSet.getString("first_name"), 
                                                resultSet.getString("last_name"), 
                                                resultSet.getString("middle_name"),
                                                resultSet.getString("user_type"),
                                                getRoleDetails(resultSet.getInt("role_id"))));
                }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Account getAccountDetails(int id) {
        String query = "Select * from accounts c where c.id = ? and c.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query)
                
                ) {
                preparedStatement.setInt(1, id);
            
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()) {
                        return new Account(resultSet.getInt("id"), 
                                                resultSet.getString("user_name"), 
                                                resultSet.getString("first_name"), 
                                                resultSet.getString("last_name"), 
                                                resultSet.getString("middle_name"),
                                                resultSet.getString("user_type"),
                                                getRoleDetails(resultSet.getInt("role_id")));
                    }
                }
                    
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public OperationResponse NDUAccount(AccountForm form, int accountId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        System.out.println(Crypto.base64Encode(form.getNewPassword()) + "  " + Crypto.base64Encode(form.getOldPassword()));
        String query = "{call ndu_account(?,?,?,?,?,?,?,?,?)}";
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setString(3, form.getUsername());
            callableStatement.setString(4, Crypto.base64Encode(form.getNewPassword()));
            callableStatement.setString(5, form.getOldPassword() != null && !form.getOldPassword().isEmpty() ? Crypto.base64Encode(form.getOldPassword()) : "");
            callableStatement.setString(6, form.getFname());
            callableStatement.setString(7, form.getLname());
            callableStatement.setString(8, form.getMname());
            callableStatement.setInt(9, form.getRoleId());
            
            callableStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public FileWrapper getFileByPath(String path) {
        String query = "select * from files f where f.path like '%'|| ? ||'%' and f.active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, path);
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return new FileWrapper(resultSet.getInt("id"), resultSet.getString("path"), resultSet.getString("path"), resultSet.getString("original_name"), resultSet.getString("file_type"));
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public OperationResponse addProductFile(ProductForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        
        try {
            for(int i: form.getFileId()) {
                operationResponse = addProductFileOneByOne(form.getId(), i, form.getType());
            }
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    public OperationResponse addProductFileOneByOne(int productId, int fileId, int type) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "";
        if(type == 1) query = "insert into flower_images(flower_id, file_id) values(?, ?)";
        else if(type == 2) query = "insert into gift_images(gift_id, file_id) values(?, ?)";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, fileId);
            preparedStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }
    
    @Override
    public OperationResponse addOtherFile(Account account, FileWrapperForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "insert into files(path, original_name, file_type, place_type) values(?,?,?,?)";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, form.getPath());
            preparedStatement.setString(2, form.getOriginalName());
            preparedStatement.setString(3, form.getContentType());
            preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }
    
    @Override
    public List<FileWrapper> getOtherFile() {
        List<FileWrapper> list = new ArrayList<>();
        String query = "select * from files f where f.active = 1 and f.place_type = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                String path = resultSet.getString("path").split("\\.")[0];
                    list.add(new FileWrapper(path, resultSet.getString("path"), resultSet.getString("original_name"),
                                                resultSet.getString("title_az"),
                                                resultSet.getString("title_en"),
                                                resultSet.getString("title_ru"),
                                                resultSet.getString("description_az"),
                                                resultSet.getString("description_az"),
                                                resultSet.getString("description_az")));
             
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public OperationResponse removeFile(int accountId, String path) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "update files set active = 0, update_date = now(), update_user_id = ? where path like '%'||?||'%'";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, path);
            preparedStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public OperationResponse changeFileDetails(FileWrapperForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "update files set "
                        + "title_az = ?, "
                        + "title_en = ?, "
                        + "title_ru = ?, "
                        + "description_az = ?, "
                        + "description_en = ?, "
                        + "description_ru = ?, "
                        + "update_date = now() where path like '%'||?||'%'";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, form.getTitleAz());
            preparedStatement.setString(2, form.getTitleEn());
            preparedStatement.setString(3, form.getTitleRu());
            preparedStatement.setString(4, form.getDescriptionAz());
            preparedStatement.setString(5, form.getDescriptionEn());
            preparedStatement.setString(6, form.getDescriptionRu());
            preparedStatement.setString(7, form.getPath());
            preparedStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public List<Product> getProductList(ProductForm form) {
        List<Product> list = new ArrayList<>();
        String query = "";
        if(form.getType() == 1) {
            query = "select * from v_flowers order by id desc";
        }
        else if(form.getType() == 2) {
            query = "select * from v_gifts order by id desc";
        }
            
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    list.add(new Product(resultSet.getInt("id"), 
                                         new DictionaryWrapper(resultSet.getInt("category_id"), 
                                                               new MultilanguageString(resultSet.getString("category_az"), 
                                                                                        resultSet.getString("category_en"), 
                                                                                        resultSet.getString("category_ru"))),
                                        new MultilanguageString(resultSet.getString("title_az"), 
                                                                                        resultSet.getString("title_en"), 
                                                                                        resultSet.getString("title_ru")), 
                                        new MultilanguageString(resultSet.getString("description_az"), 
                                                                                        resultSet.getString("description_en"), 
                                                                                        resultSet.getString("description_ru")), 
                                        resultSet.getString("price"), 
                                        resultSet.getString("sale"), 
                                        resultSet.getInt("count"), 
                                        this.getProductFileList(resultSet.getInt("id"), form.getType()), 
                                        resultSet.getInt("favorite")));
                }
            }
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Product getProductDetails(int productId, int type)  {
        String query = "";
        if(type == 1) {
            query = "select * from v_flowers where id = ? ";
        }
        else if(type == 2) {
            query = "select * from v_gifts where id = ?";
        }
            
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return new Product(resultSet.getInt("id"), 
                                         new DictionaryWrapper(resultSet.getInt("category_id"), 
                                                               new MultilanguageString(resultSet.getString("category_az"), 
                                                                                        resultSet.getString("category_en"), 
                                                                                        resultSet.getString("category_ru"))),
                                        new MultilanguageString(resultSet.getString("title_az"), 
                                                                                        resultSet.getString("title_en"), 
                                                                                        resultSet.getString("title_ru")), 
                                        new MultilanguageString(resultSet.getString("description_az"), 
                                                                                        resultSet.getString("description_en"), 
                                                                                        resultSet.getString("description_ru")), 
                                        resultSet.getString("price"), 
                                        resultSet.getString("sale"), 
                                        resultSet.getInt("count"), 
                                        this.getProductFileList(resultSet.getInt("id"), type), 
                                        resultSet.getInt("favorite"));
                }
            }
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    @Override
    public List<FileWrapper> getProductFileList(int productId, int type) {
        List<FileWrapper> list = new ArrayList<>();
        String query = "";
        if(type == 1) 
            query = "select * from v_flower_images f where f.flower_id = ?";
        else if(type == 2) 
            query = "select * from v_gift_images f where f.gift_id = ?";
        
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, productId);
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    String path = resultSet.getString("path").split("\\.")[0];
                    list.add(new FileWrapper(resultSet.getInt("id"), path, resultSet.getString("path"), resultSet.getString("original_name"), resultSet.getString("file_type")));
                }
            }
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
    
    @Override
    public List<DictionaryWrapper> getCategoryListWithCount() {
        List<DictionaryWrapper> list = new ArrayList<>();
        
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall("{? = call get_category_list_with_count()}")) {
            connection.setAutoCommit(false);
            callableStatement.registerOutParameter(1, Types.OTHER);
            callableStatement.execute();
            try(ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                int count = 0;
                while(resultSet.next()) {
                    count += resultSet.getInt("count_category");
                    list.add(new DictionaryWrapper(resultSet.getInt("type_id"), 
                                                   new MultilanguageString(resultSet.getString("name_az"), 
                                                                            resultSet.getString("name_en"), 
                                                                            resultSet.getString("name_ru")),
                                                    resultSet.getInt("count_category")));
                }
                DictionaryWrapper dw = new DictionaryWrapper(0, 
                                                             new MultilanguageString("Hamısı", 
                                                                            "All", 
                                                                            "Все"),
                                                              count);
                list.add(0, dw);
            }
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public int getProductCount(int typeId, String name) {
        
        String query = "select count(*) count from products p "+
                       "where p.active = 1 and p.type_id = case when ? > 0 then ? else p.type_id end "+
                       "and lower(p.name) like case when length(trim(?)) > 0 then '%' || lower(?) ||'%' else lower(p.name) end";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, typeId);
            preparedStatement.setString(3, name != null ? name : "");
            preparedStatement.setString(4, name != null ? name : "");
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count;
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0;
    }



    @Override
    public List<Event> getEventList() {
        List<Event> list = new ArrayList<>();
        String query = "select * from v_events";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    String path = resultSet.getString("file_path").trim().length() > 0 ? 
                                    resultSet.getString("file_path").split("\\.")[0] : "";
                    list.add(new Event(resultSet.getString("start_date"), 
                                             resultSet.getString("end_date"), 
                                             resultSet.getInt("id"), 
                                             resultSet.getString("title_az"), 
                                             resultSet.getString("title_en"), 
                                             resultSet.getString("title_ru"), 
                                             resultSet.getString("description_az"), 
                                             resultSet.getString("description_en"), 
                                             resultSet.getString("description_ru"), 
                                             resultSet.getInt("file_id"),
                                             path
                                             ));
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Event getEventDetails(int id) {
        List<Event> list = new ArrayList<>();
        String query = "select * from v_events a where a.id = ?";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    String path = resultSet.getString("file_path").trim().length() > 0 ? 
                                    resultSet.getString("file_path").split("\\.")[0] : "";
                    return new Event(resultSet.getString("start_date"), 
                                             resultSet.getString("end_date"), 
                                             resultSet.getInt("id"), 
                                             resultSet.getString("title_az"), 
                                             resultSet.getString("title_en"), 
                                             resultSet.getString("title_ru"), 
                                             resultSet.getString("description_az"), 
                                             resultSet.getString("description_en"), 
                                             resultSet.getString("description_ru"), 
                                             resultSet.getInt("file_id"),
                                             path
                                             );
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    
    @Override
    public List<Event> getEventListForCommon() {
        List<Event> list = new ArrayList<>();
        String query = "select * from v_events p where (to_date(p.start_date,'dd/mm/yyyy') <= to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy')) and (to_date(p.start_date,'dd/mm/yyyy') >= to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy'))";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    String path = resultSet.getString("file_path").trim().length() > 0 ? 
                                    resultSet.getString("file_path").split("\\.")[0] : "";
                    list.add(new Event(resultSet.getString("start_date"), 
                                             resultSet.getString("end_date"), 
                                             resultSet.getInt("id"), 
                                             resultSet.getString("title_az"), 
                                             resultSet.getString("title_en"), 
                                             resultSet.getString("title_ru"), 
                                             resultSet.getString("description_az"), 
                                             resultSet.getString("description_en"), 
                                             resultSet.getString("description_ru"), 
                                             resultSet.getInt("file_id"),
                                             path
                                             ));
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
    
    @Override
    public OperationResponse NDUEvent(EventForm form, int accountId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall("{call ndu_event(?,?,?,?,?,?,?,?,?,?,?)}")) {
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setString(3, form.getStartDate());
            callableStatement.setString(4, form.getEndDate());
            callableStatement.setInt(5, form.getFileId());
            callableStatement.setString(6, form.getTitleAz());
            callableStatement.setString(7, form.getTitleEn());
            callableStatement.setString(8, form.getTitleRu());
            callableStatement.setString(9, form.getDescriptionAz());
            callableStatement.setString(10, form.getDescriptionEn());
            callableStatement.setString(11, form.getDescriptionRu());
            
            callableStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }



    @Override
    public List<Promotation> getPromotationList() {
        List<Promotation> list = new ArrayList<>();
        String query = "select * from v_promotion";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    String path = resultSet.getString("file_path").trim().length() > 0 ? 
                                    resultSet.getString("file_path").split("\\.")[0] : "";
                    list.add(new Promotation(resultSet.getString("start_date"), 
                                             resultSet.getString("end_date"), 
                                             resultSet.getString("sale"), 
                                             resultSet.getInt("id"), 
                                             resultSet.getString("title_az"), 
                                             resultSet.getString("title_en"), 
                                             resultSet.getString("title_ru"), 
                                             resultSet.getString("description_az"), 
                                             resultSet.getString("description_en"), 
                                             resultSet.getString("description_ru"), 
                                             resultSet.getInt("file_id"),
                                             path
                                             ));
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Promotation> getPromotationListForCommon() {
        List<Promotation> list = new ArrayList<>();
        String query = "select * from v_promotion p where (to_date(p.start_date,'dd/mm/yyyy') <= to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy')) and (to_date(p.start_date,'dd/mm/yyyy') >= to_date(to_char(now(),'dd/mm/yyyy'),'dd/mm/yyyy'))";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    String path = resultSet.getString("file_path").trim().length() > 0 ? 
                                    resultSet.getString("file_path").split("\\.")[0] : "";
                    list.add(new Promotation(resultSet.getString("start_date"), 
                                             resultSet.getString("end_date"), 
                                            resultSet.getString("sale"), 
                                             resultSet.getInt("id"), 
                                             resultSet.getString("title_az"), 
                                             resultSet.getString("title_en"), 
                                             resultSet.getString("title_ru"), 
                                             resultSet.getString("description_az"), 
                                             resultSet.getString("description_en"), 
                                             resultSet.getString("description_ru"), 
                                             resultSet.getInt("file_id"),
                                             path
                                             ));
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Promotation getPromotationDetails(int id) {
        String query = "select * from v_promotion where id = ? ";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    String path = resultSet.getString("file_path").trim().length() > 0 ? 
                                    resultSet.getString("file_path").split("\\.")[0] : "";
                    return new Promotation(resultSet.getString("start_date"), 
                                             resultSet.getString("end_date"), 
                                             resultSet.getString("sale"), 
                                             resultSet.getInt("id"), 
                                             resultSet.getString("title_az"), 
                                             resultSet.getString("title_en"), 
                                             resultSet.getString("title_ru"), 
                                             resultSet.getString("description_az"), 
                                             resultSet.getString("description_en"), 
                                             resultSet.getString("description_ru"), 
                                             resultSet.getInt("file_id"),
                                             path
                                             );
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public OperationResponse NDUPromotation(PromotationForm form, int accountId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall("{call ndu_promotation(?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setString(3, form.getStartDate());
            callableStatement.setString(4, form.getEndDate());
            callableStatement.setInt(5, form.getFileId());
            callableStatement.setString(6, form.getTitleAz());
            callableStatement.setString(7, form.getTitleEn());
            callableStatement.setString(8, form.getTitleRu());
            callableStatement.setString(9, form.getDescriptionAz());
            callableStatement.setString(10, form.getDescriptionEn());
            callableStatement.setString(11, form.getDescriptionRu());
            callableStatement.setString(12, form.getSale());
            
            callableStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    
    
    @Override
    public OperationResponse addFile(FileWrapperForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "insert into files(path, original_name, title_az, title_en, title_ru, description_az, description_en, description_ru) " +
                        "values(?,?,?,?,?,?,?,?) RETURNING id";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, form.getPath());
            preparedStatement.setString(2, form.getOriginalName());
            preparedStatement.setString(3, form.getTitleAz());
            preparedStatement.setString(4, form.getTitleEn());
            preparedStatement.setString(5, form.getTitleRu());
            preparedStatement.setString(6, form.getDescriptionAz());
            preparedStatement.setString(7, form.getDescriptionEn());
            preparedStatement.setString(8, form.getDescriptionRu());
            
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    operationResponse.setData(resultSet.getInt(1));
                    operationResponse.setCode(ResultCode.OK);
                }
            }
            
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    
    
    @Override
    public OperationResponse insertCommonApply(CommonApplyForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall("{call insert_common_apply(?,?,?,?)}")) {
            callableStatement.setString(1, form.getName());
            callableStatement.setString(2, form.getEmail());
            callableStatement.setString(3, form.getIpAddress());
            callableStatement.setString(4, form.getDescription());
            callableStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
            
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }
    
    @Override
    public List<CommonApply> getCommonApplyList() {
        String query = "select id, name, email, description, ip_addr, to_char(create_date, 'dd/mm/yyyy') create_date from common_apply where active = 1 order by id desc";
         List<CommonApply> list = new ArrayList<>();
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    list.add( new CommonApply(resultSet.getInt("id"), 
                                             resultSet.getString("name"), 
                                             resultSet.getString("description"), 
                                             resultSet.getString("email"), 
                                             resultSet.getString("ip_addr"), 
                                             resultSet.getString("create_date")
                                             ));
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
    
    @Override
    public CommonApply getCommonApplyDetail(int id) {
        String query = "select id, name, email, description, ip_addr, to_char(create_date, 'dd/mm/yyyy') create_date from common_apply where active = 1 and id = ? order by id desc";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return new CommonApply(resultSet.getInt("id"), 
                                             resultSet.getString("name"), 
                                             resultSet.getString("description"), 
                                             resultSet.getString("email"), 
                                             resultSet.getString("ip_addr"), 
                                             resultSet.getString("create_date")
                                             );
                }
            }
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    @Override
    public OperationResponse NDURole(RoleForm form, int accountId){
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall("{call ndu_role(?,?,?,?,?,?)}")) {
            
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setString(3, form.getNameAz());
            callableStatement.setString(4, form.getNameEn());
            callableStatement.setString(5, form.getNameRu());
            callableStatement.setArray(6, connection.createArrayOf("int", form.getModuleId()));
            callableStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }
    
    @Override
    public List<Roles> getRoleList(){
        
        List<Roles> list = new ArrayList<>();
        String query = "select * from roles where active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = prepareStatement.executeQuery()) {
                while(resultSet.next()) {
                    list.add(new Roles(resultSet.getInt("id"), new MultilanguageString(resultSet.getString("name_az"), "", ""), null));
                }
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
    
    @Override
    public Roles getRoleDetails(int id){
        
        String query = "select * from roles where active = 1 and  id = ?";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setInt(1, id);
            try(ResultSet resultSet = prepareStatement.executeQuery()) {
                if(resultSet.next()) {
                    return new Roles(resultSet.getInt("id"), 
                                        new MultilanguageString(resultSet.getString("name_az"), "", ""),
                                        getModuleListByRole(id, connection));
                }
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    @Override
    public List<Modules> getModuleList(){
        List<Modules> list = new ArrayList<>();
        String query = "select * from modules where active = 1";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = prepareStatement.executeQuery()) {
                while(resultSet.next()) {
                    list.add(new Modules(resultSet.getInt("id"), 
                                         new MultilanguageString(resultSet.getString("name_az"), "", ""), 
                                         resultSet.getString("url"), 
                                         resultSet.getString("icon"), 
                                         resultSet.getString("type")));
                }
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
    
    private List<Modules> getModuleListByRole(int id, Connection connection) {
        List<Modules> list = new ArrayList<>();
        String query = "select * from v_role_access_module where role_id = ?";
        try(PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setInt(1, id);
            try(ResultSet resultSet = prepareStatement.executeQuery()) {
                while(resultSet.next()) {
                    list.add(new Modules(resultSet.getInt("module_id"), 
                                         new MultilanguageString(resultSet.getString("module_az"), "", ""), 
                                         resultSet.getString("module_url"), 
                                         resultSet.getString("module_icon"), 
                                         resultSet.getString("module_type")));
                }
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public OperationResponse nduProduct(ProductForm form, int accountId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "";
        if(form.getType() == 1)
            query = "{call ndu_flower(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        else if(form.getType() == 2)
            query = "{call ndu_gift(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(query)) {
            
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setInt(3, form.getCategoryId());
            callableStatement.setString(4, form.getTitleAz());
            callableStatement.setString(5, form.getTitleEn());
            callableStatement.setString(6, form.getTitleRu());
            callableStatement.setString(7, form.getDescriptionAz());
            callableStatement.setString(8, form.getDescriptionEn());
            callableStatement.setString(9, form.getDescriptionRu());
            callableStatement.setArray(10, connection.createArrayOf("int", form.getFileId() != null && form.getFileId().length > 0 ? form.getFileId() : new Integer[]{}));
            callableStatement.setString(11, form.getPrice());
            callableStatement.setString(12, form.getSale());
            callableStatement.setInt(13, form.getCount());
            callableStatement.setInt(14, form.getFavorite());
            callableStatement.executeUpdate();
            
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public OperationResponse NDWishlist(WishlistForm form, int accountId) {
         OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "{call nd_wishlist(?,?,?,?)}";
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, accountId);
            callableStatement.setInt(2, form.getId());
            callableStatement.setInt(3, form.getProductId());
            callableStatement.setInt(4, form.getType());
            callableStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public List<Wishlist> getWishlistByUserId(int accountId) {
        List<Wishlist> list = new ArrayList<>();
        String query = "Select * from v_wishlist c where c.user_id = ?";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query)) {
                preparedStatement.setInt(1, accountId);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                   while(resultSet.next()) {
                        list.add(new Wishlist(resultSet.getInt("id"), 
                                            null,
                                            new Product(resultSet.getInt("product_id"), 
                                                        new DictionaryWrapper(0, 
                                                                              new MultilanguageString(resultSet.getString("product_category_az"), 
                                                                                                       resultSet.getString("product_category_en"), 
                                                                                                       resultSet.getString("product_category_ru"))),
                                                       new MultilanguageString(resultSet.getString("product_title_az"), 
                                                                                                       resultSet.getString("product_title_en"), 
                                                                                                       resultSet.getString("product_title_ru")), 
                                                       new MultilanguageString(resultSet.getString("product_description_az"), 
                                                                                                       resultSet.getString("product_description_en"), 
                                                                                                       resultSet.getString("product_description_ru")), 
                                                       resultSet.getString("product_price"), 
                                                       resultSet.getString("product_sale"), 
                                                       resultSet.getInt("product_count"), 
                                                       this.getProductFileList(resultSet.getInt("product_id"), resultSet.getInt("type_id")), 
                                                       resultSet.getInt("product_favorite"))));
                    } 
                }
                    
                    
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public OperationResponse NDBasket(WishlistForm form, String ssid) {
         OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "{call nd_basket(?,?,?,?)}";
        try(Connection connection = dbConnect.getPostgresConnection();
            CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, ssid);
            callableStatement.setInt(2, form.getId());
            callableStatement.setInt(3, form.getProductId());
            callableStatement.setInt(4, form.getType());
            callableStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }

    @Override
    public List<Wishlist> getBasketBySsid(String ssid) {
        List<Wishlist> list = new ArrayList<>();
        String query = "Select * from v_basket c where trim(c.ssid) = trim(?)";
        try(Connection connection = dbConnect.getPostgresConnection();
                PreparedStatement preparedStatement = connection.prepareCall(query)) {
                preparedStatement.setString(1, ssid);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                   while(resultSet.next()) {
                        list.add(new Wishlist(resultSet.getInt("id"), 
                                            null,
                                            new Product(resultSet.getInt("product_id"), 
                                                        new DictionaryWrapper(0, 
                                                                              new MultilanguageString(resultSet.getString("product_category_az"), 
                                                                                                       resultSet.getString("product_category_en"), 
                                                                                                       resultSet.getString("product_category_ru"))),
                                                       new MultilanguageString(resultSet.getString("product_title_az"), 
                                                                                                       resultSet.getString("product_title_en"), 
                                                                                                       resultSet.getString("product_title_ru")), 
                                                       new MultilanguageString(resultSet.getString("product_description_az"), 
                                                                                                       resultSet.getString("product_description_en"), 
                                                                                                       resultSet.getString("product_description_ru")), 
                                                       resultSet.getString("product_price"), 
                                                       resultSet.getString("product_sale"), 
                                                       resultSet.getInt("product_count"), 
                                                       this.getProductFileList(resultSet.getInt("product_id"), resultSet.getInt("type_id")), 
                                                       resultSet.getInt("product_favorite"))));
                    } 
                }
                    
                    
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
    
    
    @Override
    public OperationResponse createDefaultSession(String ssid){
         OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        String query = "insert into default_user_session(ssid, creat_date) values(?,now())";
        try(Connection connection = dbConnect.getPostgresConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ssid);
            preparedStatement.executeUpdate();
            operationResponse.setCode(ResultCode.OK);
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return operationResponse;
    }
}
