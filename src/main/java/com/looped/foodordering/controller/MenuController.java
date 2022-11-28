package com.looped.foodordering.controller;

import com.looped.foodordering.service.IMenuService;
import com.looped.foodordering.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: looped
 * DateTime: 2022/11/28 15:35
 */
@Controller
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@GetMapping({"/", "/menu"})
	public String viewHomePage(Model model) {

		List<Menu> listMenu = menuService.getAllMenus();

		model.addAttribute("listMenu", listMenu);

		return "index";
	}

}
