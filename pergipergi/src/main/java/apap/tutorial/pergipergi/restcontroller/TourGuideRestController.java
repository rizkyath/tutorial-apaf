package apap.tutorial.pergipergi.restcontroller;

import apap.tutorial.pergipergi.service.TourGuideRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class TourGuideRestController {
    @Autowired
    private TourGuideRestService tourGuideRestService;

    @GetMapping(value="/tour/umur/{noTourGuide}")
    private Mono<String> getUmurTourGuide(@PathVariable("noTourGuide") Long noTourGuide) {
        try {
            return tourGuideRestService.predictUmur(noTourGuide);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Tour Guide " + String.valueOf(noTourGuide) + " Not Found."
            );
        }
    }
}
