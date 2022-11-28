package com.looped.foodordering.controller;

import java.util.List;

import com.looped.foodordering.model.Order;
import com.looped.foodordering.service.IOrderService;
import com.looped.foodordering.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * User: looped
 * DateTime: 2022/11/28 15:35
 */
@Controller
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping("/orders")
	public String viewHomePage(Model model) {
		return findPaginated(1, "createTime", "desc", model);
	}

	@PostMapping("/saveOrder")
	public String saveOrder(@ModelAttribute("order") Order order) {
		orderService.saveOrder(order);
		return "redirect:/";
	}

	@GetMapping("/orders/{orderId}/detail")
	public String getOrderDetail(@PathVariable Long orderId, Model model) {
		List<OrderDetailVo> orderDetailVos = orderService.getOrderDetailByOrderId(orderId);

		model.addAttribute("orderDetails", orderDetailVos);

		return "order_detail";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 5;

		Page<Order> page = orderService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Order> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listOrders", listEmployees);
		return "order_list";
	}
}
