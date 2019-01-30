/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.controller;

import com.product.web.domain.About;
import com.product.web.domain.Achievement;
import com.product.web.domain.Career;
import com.product.web.domain.Contact;
import com.product.web.domain.Event;
import com.product.web.domain.FileWrapper;
import com.product.web.domain.OperationResponse;
import com.product.web.domain.Promotation;
import com.product.web.domain.Service;
import com.product.web.enums.ResultCode;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author otahmadov
 */
@Controller
public class MainController extends SkeletonController {
    private static final Logger log = Logger.getLogger(MainController.class);
    
    @GetMapping("/index")
    protected String showHomePage(Model model) {
//        ProductForm form = new ProductForm();
//        form.setPriority(1);
//        form.setPageCount(6);
//        List<Product> favoriteProducts = service.getProductList(form);
//        model.addAttribute("favorite", favoriteProducts);
//        List<FileWrapper> fileList = service.getOtherFile();
//        model.addAttribute("titleImage", fileList);
//         model.addAttribute("pagename", "index");
            
            UUID uuid1 = UUID.randomUUID();
            UUID uuid2 = UUID.randomUUID();
            String guid = uuid1.toString().replaceAll("-", "") + uuid2.toString().replaceAll("-", "");
            OperationResponse operationResponse = service.createDefaultSession(guid);
            if(operationResponse.getCode().equals(ResultCode.OK)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("ssid", guid);
            }
            
        return "redirect:/admin";
//        return "index";
    }
    
    @GetMapping("/about")
    protected String showAboutPage(Model model) {
        //bu hissede About sehifesinin asagisindaki statistik melumatlarin hansilar olacagi deqiqlesdirilib gonderilecek
        About about = service.getAbout();
        model.addAttribute("pagename", "about");
        model.addAttribute("about", about);
        return "about";
    }
    
    @GetMapping("/contact")
    protected String showContactPage(Model model) {
        List<Contact> contacts = service.getContactList();
        model.addAttribute("pagename", "contact");
        model.addAttribute("contacts", contacts);
        return "contact";
    }
    
    @GetMapping("/promotion")
    protected String showPromotionPage(Model model) {
        List<Promotation> list = service.getPromotationListForCommon();
        model.addAttribute("pagename", "promotion");
        model.addAttribute("promotion", list);
        return "promotion";
    }
    
    @GetMapping("/event")
    protected String showAchievementPage(Model model) {
        List<Event> list = service.getEventListForCommon();
        model.addAttribute("pagename", "achievement");
        model.addAttribute("promotion", list);
        return "achievement";
    }
    
    @GetMapping("/corporative")
    protected String showCorporativePage(Model model) {
        model.addAttribute("pagename", "corporative");
        return "corporative";
    }
    
    @GetMapping("/service")
    protected String showServicePage(Model model) {
        
       model.addAttribute("pagename", "service");
        return "service";
    }
    
    @GetMapping("/event/{id:\\d+}")
    protected String showAchievementDetailsPage(Model model,
                                            @PathVariable int id) {
        Event achievement = service.getEventDetails(id);
        model.addAttribute("pagename", "event");
        model.addAttribute("event", achievement);
        return "event_details";
    }
}
