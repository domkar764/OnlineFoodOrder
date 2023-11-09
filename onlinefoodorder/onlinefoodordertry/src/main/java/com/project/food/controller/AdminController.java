package com.project.food.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.food.entity.Admin;
import com.project.food.entity.Order;
import com.project.food.entity.User;
import com.project.food.service.OrderService;
import com.project.food.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String login() {
		return "home";
	}

	@GetMapping("/admindash")
	public String adminHome() {
		return "admindash";
	}

//	@GetMapping("/orders")
//	public String showAllOrders(Model model) {
//		// Retrieve orders from your service or repository
//		List<Order> orders = orderService.getAllOrders();
//
//		// Add orders to the model
//		model.addAttribute("orders", orders);
//
//		// Return the view name
//		return "allorders";
//	}
	@GetMapping("/logoutadmin")
	public String getLogoutPage(User user) {
		return "home";
	}

	@GetMapping("/allorders")
	public String viewAllOrders(Model model) {
		List<Order> allOrders = orderService.getAllOrders();
		List<Object> ordersWithUserDetails = new ArrayList<>();

		for (Order order : allOrders) {
			Map<String, Object> orderInfo = new HashMap<>();
			orderInfo.put("orderId", order.getOrderId());
			orderInfo.put("orderTotal", order.getTotalAmount());

			// Fetch user details by user ID
			User user = userService.getUserById(order.getUser().getId());
			if (user != null) {
				orderInfo.put("userName", user.getName());
				orderInfo.put("userAddress", user.getAddress());
				orderInfo.put("userContact", user.getContact());
			}

			ordersWithUserDetails.add(orderInfo);
		}

		model.addAttribute("ordersWithUserDetails", ordersWithUserDetails);
		return "allorders";
	}
}
