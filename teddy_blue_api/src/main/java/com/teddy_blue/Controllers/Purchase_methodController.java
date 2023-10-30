package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.Purchase_method;
import com.teddy_blue.Services.Purchase_methodService;

@RestController
@RequestMapping("/purchase_methods")
public class Purchase_methodController {

    private final Purchase_methodService purchase_methodService;

    public Purchase_methodController(
	    Purchase_methodService purchase_methodService) {
	this.purchase_methodService = purchase_methodService;
    }

    @GetMapping
    public List<Purchase_method> getAllPurchase_methodS() {
	return purchase_methodService
		.getAllPurchase_methods();
    }
}
