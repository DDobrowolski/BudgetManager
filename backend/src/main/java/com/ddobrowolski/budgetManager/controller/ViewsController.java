package com.ddobrowolski.budgetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewsController {

	@RequestMapping({ "/**/{[path:[^\\.]*}" })
    public String index() {
        return "forward:/index.html";
    }
}
