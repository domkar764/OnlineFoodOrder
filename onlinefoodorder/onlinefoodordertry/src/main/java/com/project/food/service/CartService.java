package com.project.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.food.entity.Cart;
import com.project.food.entity.Product;
import com.project.food.entity.User;
import com.project.food.repo.CartRepo;
import com.project.food.repo.UserRepo;

@Service
public class CartService {

//	 public void savecart(Cart cart) {
//		 cartRepo.save(cart);
//	 }
//	public List<Cart> getCart() {
//		// TODO Auto-generated method stub
//		return cartRepo.findAll();
//	}

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private UserRepo userRepo;

	public List<Cart> listCartItems(User user) {
		return cartRepo.findByUser(user);
	}

	public void addToCart(String username, Product selectedProduct, int productId) {
		// Fetch the user
		User user = userRepo.findByEmail(username);

		// Create a CartItem
		Cart cartItem = new Cart();
		cartItem.setProduct(selectedProduct);
		cartItem.setUser(user);
		cartItem.setQuantity(1); // Set the quantity as needed

		// Add the item to the shopping cart
		cartRepo.save(cartItem);
	}

	public void deleteById(Integer productId) {
		// TODO Auto-generated method stub

	}

	public Cart getcartById(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

}