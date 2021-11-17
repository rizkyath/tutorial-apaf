package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.rest.TourGuideDetail;
import reactor.core.publisher.Mono;

public interface TourGuideRestService {
    Mono<String> predictUmur(Long noTourGuide);
    Mono<TourGuideDetail> postGuide();
}
