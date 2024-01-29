package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.SharingMethod;
import com.teddy_blue.Services.SharingMethodService;

@RestController
@RequestMapping("/sharingMethods")
public class SharingMethodController {

    @Autowired
    private SharingMethodService sharingMethodService;

    @GetMapping
    public List<SharingMethod> getAllSharingMethods() {
        return sharingMethodService.findAll();
    }
}

