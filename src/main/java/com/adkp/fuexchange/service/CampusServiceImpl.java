package com.adkp.fuexchange.service;

import com.adkp.fuexchange.pojo.Campus;
import com.adkp.fuexchange.repository.CampusRepository;
import com.adkp.fuexchange.request.CampusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampusServiceImpl implements CampusService {
    private final CampusRepository campusRepository;

    @Autowired
    public CampusServiceImpl(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    public Campus addCampus(CampusRequest campusRequest) {
        return campusRepository.save(new Campus(campusRequest.getCampusName(), campusRequest.getImgUrl()));
    }
}
