package apap.tutorial.pergipergi.restcontroller;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.rest.AgensiDetail;
import apap.tutorial.pergipergi.service.DestinasiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class DestinasiRestController {
    @Autowired
    private DestinasiRestService destinasiRestService;

    @PostMapping(value="/destinasi")
    private DestinasiModel createDestinasi(@Valid @RequestBody DestinasiModel destinasi, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return destinasiRestService.createDestinasi(destinasi);
        }
    }

    @PutMapping(value="/destinasi/{noDestinasi}")
    private DestinasiModel updateDestinasi(@PathVariable("noDestinasi") Long noDestinasi, @RequestBody DestinasiModel destinasi) {
        try {
            return destinasiRestService.updateDestinasi(noDestinasi, destinasi);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Destinasi with No Destinasi " + String.valueOf(noDestinasi) + " Not Found."
            );
        }
    }

    @GetMapping(value="/destinasi/{noDestinasi}")
    private DestinasiModel getDestinasi(@PathVariable("noDestinasi") Long noDestinasi) {
        try {
            return destinasiRestService.getDestinasiByNoDestinasi(noDestinasi);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Destinasi " + String.valueOf(noDestinasi) + " Not Found."
            );
        }
    }

    @GetMapping(value="/list-destinasi")
    private List<DestinasiModel> retrieveListDestinasi() {
        return destinasiRestService.retrieveListDestinasi();
    }

    @DeleteMapping(value="/destinasi/{noDestinasi}")
    private ResponseEntity deleteDestinasi(@PathVariable("noDestinasi") Long noDestinasi) {
        try {
            destinasiRestService.deleteDestinasi(noDestinasi);
            return ResponseEntity.ok("Destinasi with No Destinasi " + String.valueOf(noDestinasi) + " Deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Destinasi with No Destinasi " + String.valueOf(noDestinasi) + " Not Found."
            );
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "UnsupportedOperationException"
            );
        }
    }

    @GetMapping(value="/destinasi/{noDestinasi}/status")
    private Mono<String> getStatus(@PathVariable("noDestinasi") Long noDestinasi) {
        return destinasiRestService.getStatus(noDestinasi);
    }

}
