package com.ltr.service;

import com.ltr.dao.DesignDao;
import com.ltr.mapper.Mapper;
import com.ltr.model.Design;
import com.ltr.repository.DesignRepository;
import org.springframework.stereotype.Service;

@Service
public class DesignService {
    private final DesignRepository designRepository;

    public DesignService(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    public Design saveDesign(DesignDao designDao){
        Design design = Mapper.mapToDesign(designDao);
        designRepository.save(design);
        return design;
    }

    public DesignDao getDesignById(Long id){
        Design design = designRepository.findById(id).orElseThrow(() -> new RuntimeException("Design not found for id: "+id));
        return Mapper.mapToDesignDao(design);
    }
}
