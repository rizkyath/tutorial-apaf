package apap.tutorial.pergipergi.repository;

import apap.tutorial.pergipergi.model.TourGuideModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TourGuideDb extends JpaRepository<TourGuideModel, Long> {
    TourGuideModel getByNoTourGuide(long noTourGuide);
    Long deleteTourGuideModelByNoTourGuide(long noTourGuide);
}
