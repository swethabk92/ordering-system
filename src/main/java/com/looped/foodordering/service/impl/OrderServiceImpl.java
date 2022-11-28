package com.looped.foodordering.service.impl;

import com.looped.foodordering.model.Cart;
import com.looped.foodordering.model.Menu;
import com.looped.foodordering.model.Order;
import com.looped.foodordering.model.OrderDetail;
import com.looped.foodordering.repository.CartRepository;
import com.looped.foodordering.repository.MenuRepository;
import com.looped.foodordering.repository.OrderDetailRepository;
import com.looped.foodordering.service.IOrderService;
import com.looped.foodordering.vo.OrderDetailVo;
import com.looped.foodordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: looped
 * DateTime: 2022/11/28 15:56
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void saveOrder(Order order) {
		List<Cart> carts = cartRepository.findAll();
		Double total = carts.stream()
				.map(cart -> cart.getPrice().multiply(BigDecimal.valueOf(cart.getNum())))
				.mapToDouble(b -> b.doubleValue())
				.sum();

		order.setTotal(total);
		order.setCreateTime(LocalDateTime.now());
		this.orderRepository.save(order);

		List<OrderDetail> orderDetails = carts.stream().map(cart -> {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(order.getId());
			orderDetail.setNum(cart.getNum());
			orderDetail.setMenuId(cart.getMenuId());
			return orderDetail;
		}).collect(Collectors.toList());

		orderDetailRepository.saveAll(orderDetails);

		cartRepository.deleteAll();
	}

	@Override
	public Order getOrderById(long id) {
		Optional<Order> optional = orderRepository.findById(id);
		Order employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Order not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteOrderById(long id) {
		this.orderRepository.deleteById(id);
	}

	@Override
	public Page<Order> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.orderRepository.findAll(pageable);
	}
	@Override
	public List<OrderDetailVo> getOrderDetailByOrderId(long orderId) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(orderId);
		List<OrderDetail> orderDetails = orderDetailRepository.findAll(Example.of(orderDetail));
		return orderDetails.stream().map(od -> {
			Optional<Menu> menuOptl = menuRepository.findById(od.getMenuId());
			OrderDetailVo orderDetailVo = null;
			if(menuOptl.isPresent()) {
				orderDetailVo = new OrderDetailVo();
				orderDetailVo.setId(od.getId());
				orderDetailVo.setName(menuOptl.get().getName());
				orderDetailVo.setDescription(menuOptl.get().getDescription());
				orderDetailVo.setPrice(menuOptl.get().getPrice());
				orderDetailVo.setNum(od.getNum());
			}
			return orderDetailVo;
		}).filter(odv -> odv != null).collect(Collectors.toList());
	}
}
