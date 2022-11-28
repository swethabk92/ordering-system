package com.looped.foodordering.service.impl;

import com.looped.foodordering.model.Menu;
import com.looped.foodordering.repository.MenuRepository;
import com.looped.foodordering.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: looped
 * DateTime: 2022/11/28 15:56
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public List<Menu> getAllMenus() {
		return menuRepository.findAll();
	}

}
