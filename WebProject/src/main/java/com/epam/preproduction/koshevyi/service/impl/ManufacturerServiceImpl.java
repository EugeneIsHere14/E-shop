package com.epam.preproduction.koshevyi.service.impl;

import com.epam.preproduction.koshevyi.db.TransactionManager;
import com.epam.preproduction.koshevyi.repository.ManufacturerRepository;
import com.epam.preproduction.koshevyi.service.ManufacturerService;

import java.util.List;

public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;
    private TransactionManager transactionManager;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, TransactionManager transactionManager) {
        this.manufacturerRepository = manufacturerRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public List<String> getAllManufacturers() {
        return transactionManager.performTransaction(connection -> manufacturerRepository.getManufacturersList(connection));
    }
}