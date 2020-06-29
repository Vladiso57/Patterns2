package com.Labs.Patterns.controller;

import com.Labs.Patterns.dto.Printer;
import com.Labs.Patterns.dto.User;
import com.Labs.Patterns.service.ICartService;
import com.Labs.Patterns.service.IPrintersService;
import com.Labs.Patterns.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @Autowired
    private IPrintersService iPrintersService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICartService iCartService;



    @PostMapping("/")
    public String getMainPageByManufacturer(Model model,@ModelAttribute("printer") Printer printer, @RequestParam("size") Optional<Integer> size,@RequestParam("page") Optional<Integer> page)
    {
        int currentPage=page.orElse(0);
        int pageSize=size.orElse(5);
        Page<Printer> printersPage=iPrintersService.getPrintersByManufacturer(printer.getManufacturer(), PageRequest.of(currentPage,pageSize));
        if(printersPage!=null) {
            model.addAttribute("existData",true);
            model.addAttribute("notExistInCart",true);
            model.addAttribute("printersPage", printersPage);
            int totalPages = printersPage.getTotalPages();
            int totalItems = printersPage.getTotalPages();
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalItems", totalItems - 1);
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages - 1)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        }
       else if(printer.getManufacturer().equals(""))
        {
            return "redirect:/";
        }
        else
        {
            model.addAttribute("noData",true);
            model.addAttribute("printersPage", printersPage);
            int totalPages = 1;
            int totalItems = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalItems", totalItems - 1);
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages - 1)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        return "Main";
    }

    @GetMapping("/")
    public String getMainPage(Model model,@ModelAttribute("printer") Printer printer, @ModelAttribute("printer2") Printer printer2, @RequestParam("size") Optional<Integer> size,@RequestParam("page") Optional<Integer> page)
    {
        model.addAttribute("existData",true);
        model.addAttribute("notExistInCart",true);
        int currentPage=page.orElse(0);
        int pageSize=size.orElse(5);
        Page<Printer> printersPage=iPrintersService.getAllPrinters( PageRequest.of(currentPage,pageSize));
        model.addAttribute("printersPage",printersPage);
        int totalPages=printersPage.getTotalPages();
        int totalItems=printersPage.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems-1);
        if(totalPages>0)
        {
            List<Integer> pageNumbers= IntStream.rangeClosed(1,totalPages-1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "Main";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") int id, String nickname,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        nickname = auth.getName();
        int userId = this.iUserService.getUsersId(nickname);

        boolean result = this.iCartService.isPrinterInCartExist(userId);
        if (result == true) {
            model.addAttribute("existInCart", true);
        } else {
            model.addAttribute("notExistInCart",true);
            this.iPrintersService.addToCart(userId, id);
        }
        return "redirect:/";
    }
}