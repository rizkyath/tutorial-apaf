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
        return tourGuideDb.getTourGuideModelByNoTourGuide(noTourGuide);
    }

    @Override
    public TourGuideModel updateTourGuide(TourGuideModel guide){
        tourGuideDb.save(guide);
        return guide;
    }

    @Override
    public TourGuideModel deleteTourGuide(TourGuideModel guide){
        Long noTourGuide = guide.getNoTourGuide();
        tourGuideDb.deleteTourGuideModelByNoTourGuide(noTourGuide);
        return guide;
    }

}
