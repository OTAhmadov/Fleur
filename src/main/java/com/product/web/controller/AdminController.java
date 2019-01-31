/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.controller;

import com.product.web.domain.Account;
import com.product.web.domain.FileWrapper;
import com.product.web.domain.MultilanguageString;
import com.product.web.domain.OperationResponse;
import com.product.web.domain.Service;
import com.product.web.enums.ResultCode;
import com.product.web.form.AboutForm;
import com.product.web.form.AccountForm;
import com.product.web.form.AchievementForm;
import com.product.web.form.CareerForm;
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
import com.product.web.util.Crypto;
import com.product.web.util.FtpUtils;
import com.product.web.util.WebUtils;
import com.product.web.validation.FileWrapperFormValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author otahmadov
 */
@Api(description = "Admin panel ucun(AdminController)")
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends SkeletonController {
    private Logger log = Logger.getLogger(AdminController.class);
    
    @GetMapping(value = "/flower")
    protected String showAdminFlower(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/flower";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/gift")
    protected String showAdminGift(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/gift";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/common")
    protected String showAdminLog(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/common";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/dic")
    protected String showAdminDictionary(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/dictionary";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/userGroup")
    protected String showUserGroupDictionary(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/user_group";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/user")
    protected String showAdminUser(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/user";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/event")
    protected String showAdminCompany(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/event";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/promotation")
    protected String showAdminPromotation(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/promotation";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/order")
    protected String showAdminOrder(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/order";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping(value = "/commonApply")
    protected String showAdminCommonApply(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/common_apply";
            }
            
            return "redirect:/admin";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping
    protected String showLoginPage(Model model) {
        
        try {
            Account account = getSessionAccount();
            if(account != null) {
                model.addAttribute("modules", account.getRole().getModules());
                return "admin/common";
            }
            
            return "login";
        } 
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @PostMapping("/login")
    protected String doLogin(Model model,
                             LoginForm form) {
        
        try {
            
            form.setIpadress(WebUtils.getIpAddress(request));
            Account account = service.doLogin(form);
            
            if(account == null) {
                model.addAttribute("error", "Invalid username/password");
                throw new Exception("Invalid username/password. LoginForm: " + form);
            }
            
            HttpSession session = request.getSession(true);
            session.setAttribute("account", account);
            return "redirect:/admin/common";
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @PostMapping("/logout")
    protected String doLogout(Model model,
                             HttpSession session) {
        
        try {
            
            session.removeAttribute("account");
            return "redirect:/admin";
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return "redirect:/admin";
    }
    
    @ApiOperation(value = "dictionary type larin listini getirir.", notes = "Biz terefden teyin olanan typeler siyahida gelmeyecek")
    @GetMapping("/dictionary/type")
    @ResponseBody
    protected OperationResponse getDictionaryTypeList() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            operationResponse.setData(service.getDictionaryTypeList());
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    @ApiOperation(value = "dictionary lerin listini getirir dic type id ye gore. ", notes = "mes: http://104.248.176.190:8080/admin/dictionary?dicTypeId=1")
    @GetMapping("/dictionary")
    @ResponseBody
    protected OperationResponse getDictionaryTypeList(@RequestParam int dicTypeId,
                                                       @RequestParam(defaultValue = "0") String parentId) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            
            if(!parentId.equals("0")) {
                operationResponse.setData(service.getDictionaryListByParent(Integer.parseInt(parentId)));
                operationResponse.setCode(ResultCode.OK);
                return operationResponse;
            }
            operationResponse.setData(service.getDictionaryList(dicTypeId));
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "dictionary lerin add edit remove - si. ", notes = "parametr siyahisi form packagesinde var hamisinin baxarsan. istifade ucun sessiya teleb olunur, login olmaq lazimdir")
    @PostMapping("/dictionary/ndu")
    @ResponseBody
    protected OperationResponse NDUDictionary(DictionaryWrapperForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            operationResponse = service.NDUDictionary(form, account.getId());
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "contactlarin siyahisini getirir", notes = "")
    @GetMapping("/contacts")
    @ResponseBody
    protected OperationResponse getContactList() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            operationResponse.setData(service.getContactList());
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = " id ye gore contactin deteilsini getirir.", notes = "")
    @GetMapping("/contact/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getContactById(@PathVariable int id) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
//            Account account = getSessionAccount(operationResponse);
            operationResponse.setData(service.getContactDetails(id));
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "kontactlarin add edit remove si", notes = "parametr siyahisi form packagesinde var hamisinin baxarsan. istifade ucun sessiya teleb olunur, login olmaq lazimdir")
    @PostMapping("/contact/ndu")
    @ResponseBody
    protected OperationResponse NDUContact(ContactForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);  
        try {
            Account account = getSessionAccount(operationResponse);
            operationResponse = service.NDUContact(form, account.getId());
//            operationResponse = service.NDUContact(form, 1);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "about getirir.", notes = "")
    @GetMapping("/about")
    @ResponseBody
    protected OperationResponse getAbout() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            
            operationResponse.setData(service.getAbout());
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "about add edit remove si", notes = "parametr siyahisi form packagesinde var hamisinin baxarsan. istifade ucun sessiya teleb olunur, login olmaq lazimdir. eslinde about ancaq edit olunacaq.")
    @PostMapping("/about/ndu")
    @ResponseBody
    protected OperationResponse NDUAbout(AboutForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            operationResponse = service.NDUAbout(form, account.getId());
//            operationResponse = service.NDUAbout(form, 1);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "account listini getirir", notes = "istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @GetMapping("/accounts")
    @ResponseBody
    protected OperationResponse getAccountList() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            operationResponse.setData(service.getAccountList());
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "accountun id sine gore detail getirir", notes = "istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @GetMapping("/account/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getAccountById(@PathVariable int id) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            operationResponse.setData(service.getAccountDetails(id));
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "accountlarin add edit remove si", notes = "parametr siyahisi form packagesinde var hamisinin baxarsan. istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @PostMapping("/account/ndu")
    @ResponseBody
    protected OperationResponse NDUAccount(AccountForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            operationResponse = service.NDUAccount(form, account.getId());
//            operationResponse = service.NDUAccount(form, 1);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ResponseBody
    @PostMapping(value = "/product/ndu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    protected OperationResponse NDUProductFile(@RequestPart(name = "image", required = false) MultipartFile image,
                                                @RequestPart(name = "form", required = false) ProductForm form,
                                                MultipartHttpServletRequest multipartHttpServletRequest
    ) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            if(!account.getUserType().equals("ADMIN")) {
                operationResponse.setCode(ResultCode.UNAUTHORIZED);
                return operationResponse;
            }
            
            if(form.getId() == 0) {
                MultiValueMap<String, MultipartFile> requestParts = multipartHttpServletRequest.getMultiFileMap();
                List<MultipartFile> files;

                files = requestParts.get("image");

                if (files.size() > 5) {
                    throw new Exception("invalid file count");
                }

    //            FileWrapperForm[] fileWrappers = new FileWrapperForm[files.size()];
                Integer[] productImage = new Integer[files.size() + form.getFileId().length];
                int i = 0;
                for(int id: form.getFileId()) {
                    productImage[i] = id;
                    ++i;
                }

                FileWrapperFormValidator fileWrapperFormValidator = new FileWrapperFormValidator();


                for (MultipartFile multipartFile : files) {
                    if (multipartFile != null && multipartFile.getSize() > 0) {

                        OperationResponse saveFileResponse = ftpService.saveFtpFile("", multipartFile, Crypto.getGuid());
                            if(saveFileResponse.getCode() != ResultCode.OK) {
                                throw new Exception("Error upload file");
                            }

                            String fullPath = (String) saveFileResponse.getData();
                            FileWrapperForm fileWrapperForm = new FileWrapperForm();
                            fileWrapperForm.setOriginalName(image.getOriginalFilename());
                            fileWrapperForm.setPath(fullPath);
                            operationResponse = service.addFile(fileWrapperForm);
                            productImage[i] = (Integer) operationResponse.getData();
                    }
                    i = i + 1;
                }

                form.setFileId(productImage);
            }
            
            operationResponse = service.nduProduct(form,account.getId());
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ResponseBody
    @PostMapping(value = "/product/file/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    protected OperationResponse addProductFile(@RequestPart(name = "image", required = false) MultipartFile image,
                                                @RequestPart(name = "form", required = false) ProductForm form,
                                                MultipartHttpServletRequest multipartHttpServletRequest
    ) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            if(!account.getUserType().equals("ADMIN")) {
                operationResponse.setCode(ResultCode.UNAUTHORIZED);
                return operationResponse;
            }
            
            MultiValueMap<String, MultipartFile> requestParts = multipartHttpServletRequest.getMultiFileMap();
            List<MultipartFile> files;

            files = requestParts.get("image");

            if (files.size() > 5 || files.size() == 0) {
                throw new Exception("invalid file count");
            }

//            FileWrapperForm[] fileWrappers = new FileWrapperForm[files.size()];
            Integer[] productImage = new Integer[files.size()];
            int i = 0;

            FileWrapperFormValidator fileWrapperFormValidator = new FileWrapperFormValidator();


                for (MultipartFile multipartFile : files) {
                    if (multipartFile != null && multipartFile.getSize() > 0) {

                        OperationResponse saveFileResponse = ftpService.saveFtpFile("", multipartFile, Crypto.getGuid());
                            if(saveFileResponse.getCode() != ResultCode.OK) {
                                throw new Exception("Error upload file");
                            }

                            String fullPath = (String) saveFileResponse.getData();
                            FileWrapperForm fileWrapperForm = new FileWrapperForm();
                            fileWrapperForm.setOriginalName(image.getOriginalFilename());
                            fileWrapperForm.setPath(fullPath);
                            operationResponse = service.addFile(fileWrapperForm);
                            productImage[i] = (Integer) operationResponse.getData();
                    }
                    i = i + 1;
                }

                form.setFileId(productImage);
            
            operationResponse = service.addProductFile(form);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "BAsliq ucun sekiller elave edilmesi", notes = "file sayi max 5 dene qoymusam. istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @ResponseBody
    @PostMapping(value = "/other/file/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    protected OperationResponse addOtherFiles(@RequestPart(name = "image", required = false) MultipartFile image,
                                                MultipartHttpServletRequest multipartHttpServletRequest
//                                                BindingResult result
    ) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            MultiValueMap<String, MultipartFile> requestParts = multipartHttpServletRequest.getMultiFileMap();
            List<MultipartFile> files;
            
            files = requestParts.get("image");
            
            if (files.size() > 5) {
                throw new Exception("invalid file count");
            }
            
            FileWrapperForm[] fileWrappers = new FileWrapperForm[files.size()];
            
            FileWrapperFormValidator fileWrapperFormValidator = new FileWrapperFormValidator();
            int i = 0;
            
            for (MultipartFile multipartFile : files) {
                if (multipartFile != null && multipartFile.getSize() > 0) {
                    fileWrappers[i] = new FileWrapperForm();
                    fileWrappers[i].setFile(multipartFile);

                    OperationResponse saveFileResponse = ftpService.saveFtpFile("", multipartFile, Crypto.getGuid());

                    if (saveFileResponse.getCode() == ResultCode.OK) {
                        String fullPath = (String) saveFileResponse.getData();
                        fileWrappers[i].setOriginalName(multipartFile.getOriginalFilename());
                        fileWrappers[i].setPath(fullPath);
                        fileWrappers[i].setContentType(multipartFile.getContentType());
                        
                        operationResponse = service.addOtherFile(account, fileWrappers[i]);
                        
                        if(operationResponse.getCode() != ResultCode.OK) {
                            throw new Exception("Error add file");
                        }
                    }
                }
                i = i + 1;
            }
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    @ApiOperation(value = "basliq ucun sekillerin getirilmesi", notes = "file sayi max 5 dene qoymusam. istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @ResponseBody
    @GetMapping(value = "/other/files")
    protected OperationResponse getOtherFiles() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            operationResponse.setData(service.getOtherFile());
            operationResponse.setCode(ResultCode.OK);
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @ApiOperation(value = "path e gore sekli qaytarir", notes = "ftp de olmaqla yanasi bazadada path movcud olmalidir. tehlukesizlik ucun bazadan yoxlayir.")
    @GetMapping(value = "/image/{path}", produces = MediaType.IMAGE_JPEG_VALUE) // OK
    @ResponseBody
    protected byte[] getProductImage(@PathVariable String path,
                                     @RequestParam(defaultValue = "") String size) {

        try {

            FileWrapper image = service.getFileByPath(path);

            if (image != null) {
                
//                String[] splitPath  = image.getPath().split("\\.");
                
                return ftpService.downloadFtpFile(image.getPath());
                
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
    
    @ApiOperation(value = "image nin path ne gore silir", notes = "istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @PostMapping(value = "/image/{path}/remove")
    @ResponseBody
    protected OperationResponse removeProductImage(@PathVariable String path) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);

            FileWrapper image = service.getFileByPath(path);

            if (image != null) {
                operationResponse =  ftpService.removeFtpFile(image.getPath());
                
                if(operationResponse.getCode() != ResultCode.OK) {
                    throw new Exception("error remove file");
                }
                
                operationResponse = service.removeFile(account.getId(), path);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @ApiOperation(value = "image nin path ne gore silir", notes = "istifade ucun sessiya teleb olunur, login olmaq lazimdir.")
    @PostMapping(value = "/file/{path}/change/details")
    @ResponseBody
    protected OperationResponse changeFileDetails(@PathVariable String path,
                                                   FileWrapperForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                operationResponse.setCode(ResultCode.UNAUTHORIZED);
                throw new Exception("invalid User Type");
            }
            form.setPath(path);
            operationResponse = service.changeFileDetails(form);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @ApiOperation(value = "Productlarin siyahisini getirir", notes = "Filterler: typeId, companyId, name, priority, startPrice, endPrice. Default olaraq page=1 ve pageCount=50 goturulub")
    @GetMapping(value = "/products")
    @ResponseBody
    protected OperationResponse getProductList(ProductForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            
            operationResponse.setData(service.getProductList(form));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @ApiOperation(value = "Productin sekillerinin listini qaytarir", notes = "her Ehtimala yazdim lazim olsa istifade edek")
    @GetMapping(value = "/product/{id:\\d+}/files")
    @ResponseBody
    protected OperationResponse getProductFileList(@PathVariable int id,
                                                   @RequestParam(defaultValue = "") String type) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            
            operationResponse.setData(service.getProductFileList(id, Integer.parseInt(type)));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @ApiOperation(value = "Productinin id-sine gore detaillerini qaytarir", notes = "")
    @GetMapping(value = "/product/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getProductDetails(@PathVariable int id,
                                                    @RequestParam(defaultValue = "") String type) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            if(!type.equals("1") && !type.equals("2")) 
                throw new Exception("Invalid product type!!!");
            
            operationResponse.setData(service.getProductDetails(id,Integer.parseInt(type)));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    
    @ResponseBody
    @PostMapping(value = "/event/ndu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    protected OperationResponse NDUAchievement(@RequestPart(name = "image", required = false) MultipartFile image,
                                                @RequestPart(name = "form", required = false) EventForm form
    ) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            
            if (image != null && image.getSize() > 0) {
                
                OperationResponse saveFileResponse = ftpService.saveFtpFile("", image, Crypto.getGuid());
                if(saveFileResponse.getCode() != ResultCode.OK) {
                    throw new Exception("Error upload file");
                }
                
                String fullPath = (String) saveFileResponse.getData();
                FileWrapperForm fileWrapperForm = new FileWrapperForm();
                fileWrapperForm.setOriginalName(image.getOriginalFilename());
                fileWrapperForm.setPath(fullPath);
                operationResponse = service.addFile(fileWrapperForm);
                
                form.setFileId((int) operationResponse.getData());
                if(saveFileResponse.getCode() != ResultCode.OK) {
                    throw new Exception("Error upload file");
                }
                
                
            }      
            
            operationResponse = service.NDUEvent(form, account.getId());
            
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @GetMapping(value = "/events")
    @ResponseBody
    protected OperationResponse getAchievementList(EventForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getEventList());
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/event/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getAchievementDetails(EventForm form,
                                                    @PathVariable int id) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getEventDetails(id));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    
    @ResponseBody
    @PostMapping(value = "/promotation/ndu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    protected OperationResponse NDUPromotation(@RequestPart(name = "image", required = false) MultipartFile image,
                                                @RequestPart(name = "form", required = false) PromotationForm form
    ) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);
        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            
            if (image != null && image.getSize() > 0) {
                
                OperationResponse saveFileResponse = ftpService.saveFtpFile("", image, Crypto.getGuid());
                if(saveFileResponse.getCode() != ResultCode.OK) {
                    throw new Exception("Error upload file");
                }
                
                String fullPath = (String) saveFileResponse.getData();
                FileWrapperForm fileWrapperForm = new FileWrapperForm();
                fileWrapperForm.setOriginalName(image.getOriginalFilename());
                fileWrapperForm.setPath(fullPath);
                operationResponse = service.addFile(fileWrapperForm);
                form.setFileId((int) operationResponse.getData());
                if(saveFileResponse.getCode() != ResultCode.OK) {
                    throw new Exception("Error upload file");
                }
                
            }      
            
            operationResponse = service.NDUPromotation(form, account.getId());
            
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return operationResponse;
    }
    
    @GetMapping(value = "/promotations")
    @ResponseBody
    protected OperationResponse getPromotationList(PromotationForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getPromotationList());
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/promotation/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getPromotationDetails(PromotationForm form,
                                                    @PathVariable int id) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getPromotationDetails(id));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    
    
    @GetMapping(value = "/file/{path}")
    protected void downloadFile(@PathVariable String path,
                                  HttpServletResponse response) {

        // TODO: Get content type from Database instead of parsing file original name @Samir
        try {
            FileWrapper file = service.getFileByPath(path);
            if (file != null) {
                    String[] x = file.getPath().split("\\.");
                    response.setHeader("Content-Type", FtpUtils.getType(x[x.length - 1].toLowerCase()));
                    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
                
//                return ftpService.downloadFtpFile(file.getPath());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

//        return null;
    }
    
    
    @GetMapping(value = "/commonApplyes")
    @ResponseBody
    protected OperationResponse getCommonApplyList() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getCommonApplyList());
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/commonApply/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getCommonApplyDetails(@PathVariable int id) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getCommonApplyDetail(id));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @PostMapping(value = "/role/ndu")
    @ResponseBody
    protected OperationResponse nduRole(RoleForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            
            operationResponse = service.NDURole(form, account.getId());
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    
    @GetMapping(value = "/role/{id:\\d+}")
    @ResponseBody
    protected OperationResponse getRoleDetailsById(@PathVariable int id) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getRoleDetails(id));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    
    @GetMapping(value = "/roles")
    @ResponseBody
    protected OperationResponse getRoleList() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getRoleList());
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/modules")
    @ResponseBody
    protected OperationResponse getModuleList() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(service.getModuleList());
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/modulesByAccount")
    @ResponseBody
    protected OperationResponse getModuleListByAccount() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            if(!account.getUserType().equals("ADMIN")) {
                throw new Exception("User Access Denied");
            }
            operationResponse.setData(account.getRole().getModules());
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @PostMapping(value = "/wishlist/nd")
    @ResponseBody
    protected OperationResponse ndWishlist(WishlistForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            operationResponse.setData(service.NDWishlist(form, account.getId()));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/wishlists")
    @ResponseBody
    protected OperationResponse getWishlist() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            Account account = getSessionAccount(operationResponse);
            
            operationResponse.setData(service.getWishlistByUserId(account.getId()));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @PostMapping(value = "/basket/nd")
    @ResponseBody
    protected OperationResponse ndBasket(WishlistForm form) {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            String ssid = getDefaultSessionAccount(operationResponse);
            
            operationResponse.setData(service.NDBasket(form, ssid));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
    @GetMapping(value = "/baskets")
    @ResponseBody
    protected OperationResponse getBasket() {
        OperationResponse operationResponse = new OperationResponse(ResultCode.ERROR);

        try {
            String ssid = getDefaultSessionAccount(operationResponse);
            
            operationResponse.setData(service.getBasketBySsid(ssid));
            operationResponse.setCode(ResultCode.OK);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return operationResponse;
    }
    
}
