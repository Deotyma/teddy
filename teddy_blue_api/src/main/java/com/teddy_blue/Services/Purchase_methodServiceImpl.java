package com.teddy_blue.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Purchase_method;
import com.teddy_blue.Repositories.Purchase_methodRepository;

@Service

public class Purchase_methodServiceImpl
	implements Purchase_methodService {

    private final Purchase_methodRepository purchase_methodRepository;

    public Purchase_methodServiceImpl(
	    Purchase_methodRepository purchase_methodRepository) {
	this.purchase_methodRepository = purchase_methodRepository;
    }

    @Override
    public List<Purchase_method> getAllPurchase_methods() {
	return purchase_methodRepository.findAll();
    }

}
