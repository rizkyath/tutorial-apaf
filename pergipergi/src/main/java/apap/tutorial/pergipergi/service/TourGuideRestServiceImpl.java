package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import apap.tutorial.pergipergi.rest.Setting;
import apap.tutorial.pergipergi.rest.TourGuideDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class TourGuideRestServiceImpl implements TourGuideRestService {
    private final WebClient webClient;

    @Autowired
    private TourGuideDb tourGuideDb;

    public TourGuideRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.agifyUrl).build();
    }

    @Override
    public Mono<String> predictUmur(Long noTourGuide){
        TourGuideModel guide = tourGuideDb.getTourGuideModelByNoTourGuide(noTourGuide);
        Mono<String> str = this.webClient.get().uri("/?name=" + guide.getNamaTourGuide())
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(str);
        return str;
    }

    @Override
    public Mono<TourGuideDetail> postGuide(){
        // Belum berhasil
        return null;
    }
}
