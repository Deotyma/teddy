package com.teddy_blue.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.SharingMethod;
import com.teddy_blue.Repositories.SharingMethodRepository;

@Service
public class SharingMethodServiceImpl implements SharingMethodService {

    private final SharingMethodRepository sharingMethodRepository;

    @Autowired
    public SharingMethodServiceImpl(SharingMethodRepository sharingMethodRepository) {
        this.sharingMethodRepository = sharingMethodRepository;
    }

    @Override
    public List<SharingMethod> findAll() {
        return sharingMethodRepository.findAll();
    }

}

