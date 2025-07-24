package com.cear.showcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller principal da aplicação CEAR Showcase
 */
@Controller
public class HomeController {

    /**
     * Página inicial do showcase
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "CEAR Showcase");
        model.addAttribute("activeRoute", "home");
        return "pages/home";
    }

    /**
     * Teste simples para verificar se controllers estão funcionando
     */
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Controller funcionando! ✅";
    }

    /**
     * Health check para verificar se aplicação está rodando
     */
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK - Showcase running";
    }
} 