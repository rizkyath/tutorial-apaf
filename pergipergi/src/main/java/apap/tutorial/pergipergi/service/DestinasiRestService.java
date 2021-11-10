package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.rest.AgensiDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DestinasiRestService {
    DestinasiModel createDestinasi(DestinasiModel destinasi);
    DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel destinasi);
    DestinasiModel getDestinasiByNoDestinasi(Long noDestinasi);
    List<DestinasiModel> retrieveListDestinasi();
    void deleteDestinasi(Long noDestinasi);
    Mono<String> getStatus(Long noDestinasi);
}
