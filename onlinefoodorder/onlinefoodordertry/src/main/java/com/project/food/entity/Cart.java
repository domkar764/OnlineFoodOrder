package com.project.food.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "Cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int id;
	
	@Column(name = "quantity")
	private int quantity;
	 @ManyToOne
	 @JoinColumn(name = "product_id") // Establish a many-to-one relationship with the Product entity
	    private Product product;

	
	 @ManyToOne
	 @JoinColumn(name = "id")
	 private User user;

	 
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
		
	}


	public Cart(int id, int quantity, Product product,  User user) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	

	


}	