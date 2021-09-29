package apap.tutorial.pergipergi.service;


import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
@Transactional
public class TourGuideServiceImpl implements TourGuideService {
    @Autowired
    TourGuideDb tourGuideDb;

    @Override
    public void addTourGuide(TourGuideModel tourGuide) {
        tourGuideDb.save(tourGuide);
    }

    @Override
    public TourGuideModel getByNoTourGuide(Long noTourGuide){
        return tourGuideDb.getByNoTourGuide(noTourGuide);
    }

    @Override
    public TourGuideModel updateTourGuide(TourGuideModel guide){
        tourGuideDb.save(guide);
        return guide;
    }

    @Override
    public TourGuideModel deleteTourGuideByNoTourGuide(Long noGuide){
        TourGuideModel deletedTourGuide = getByNoTourGuide(noGuide);
        tourGuideDb.deleteTourGuideModelByNoTourGuide(noGuide);
        return deletedTourGuide;
    }
}
