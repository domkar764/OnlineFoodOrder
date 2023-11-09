package com.project.food.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.food.entity.Cart;
import com.project.food.entity.Order;
import com.project.food.entity.User;
import com.project.food.repo.CartRepo;
import com.project.food.repo.UserRepo;
import com.project.food.service.CartService;
import com.project.food.service.OrderService;
import com.project.food.service.UserService;

@Controller
@RequestMapping("/user")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	// Create an order
	@GetMapping("/placeOrder")
	public String placeOrder(Principal principal, Model model, @RequestParam("totalAmount") double totalAmount) {
		String username = principal.getName();
		User user = userRepo.findByEmail(username);

		// double totalAmount = calculateTotalAmountFromCart(user);

		Order order = orderService.createOrder(user, totalAmount);
		cartRepo.deleteById(user.getId());

		// You can add additional logic here if needed

		return "redirect:/user/orders ";
	}

	// Retrieve orders for a user
	@GetMapping("/orders")
	public String viewOrders(Principal principal, Model model) {
		String username = principal.getName();
		User user = userRepo.findByEmail(username);

		List<Order> orders = orderService.getOrdersByUser(user);

		model.addAttribute("orders", orders);

		return "orders";
	}

}
