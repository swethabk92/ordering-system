package com.looped.foodordering.service.impl;

import com.looped.foodordering.model.Cart;
import com.looped.foodordering.model.Menu;
import com.looped.foodordering.repository.CartRepository;
import com.looped.foodordering.repository.MenuRepository;
import com.looped.foodordering.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User: looped
 * DateTime: 2022/11/28 15:56
 */
@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public void saveCartItem(Long menuId) {
		Cart query = new Cart();
		query.setMenuId(menuId);
		List<Cart> carts = cartRepository.findAll(Example.of(query));
		if(carts.isEmpty()) {
			Optional<Menu> menuOptl = menuRepository.findById(menuId);
			if(menuOptl.isPresent()) {
				Cart cart = new Cart();
				cart.setMenuId(menuId);
				cart.setNum(1L);
				cart.setName(menuOptl.get().getName());
				cart.setDescription(menuOptl.get().getDescription());
				cart.setPrice(menuOptl.get().getPrice());
				cartRepository.save(cart);
			}
		} else {
			Cart cart = carts.get(0);
			cart.setNum(cart.getNum() + 1);
			cartRepository.save(cart);
		}
	}

	@Override
	public void saveCart(Cart cart) {
		this.cartRepository.save(cart);
	}

	@Override
	public void deleteCartById(long id) {
		this.cartRepository.deleteById(id);
	}

}
