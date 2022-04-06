package com.naveen.ReddyPOS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.ReddyPOS.model.Products;
import com.naveen.ReddyPOS.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@GetMapping("")
	public List<Products> getInventory() {
		return inventoryService.getInventory();
	}

}
