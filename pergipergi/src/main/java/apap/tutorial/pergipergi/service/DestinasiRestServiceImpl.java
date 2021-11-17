package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.repository.DestinasiDB;
import apap.tutorial.pergipergi.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DestinasiRestServiceImpl implements DestinasiRestService {
    private final WebClient webClient;

    @Autowired
    private DestinasiDB destinasiDB;

    public DestinasiRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.agensiUrl).build();
    }

    @Override
    public DestinasiModel createDestinasi(DestinasiModel destinasi){
        return destinasiDB.save(destinasi);
    };

    @Override
    public DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel destinasiUpdate){
        DestinasiModel destinasi = destinasiDB.getDestinasiModelByNoDestinasi(noDestinasi);
        destinasi.setNegaraDestinasi(destinasiUpdate.getNegaraDestinasi());
        destinasi.setIsBebasVisa(destinasiUpdate.getIsBebasVisa());
        destinasi.setListTravelAgensi(destinasiUpdate.getListTravelAgensi());

        return destinasiDB.save(destinasi);
    }

    @Override
    public DestinasiModel getDestinasiByNoDestinasi(Long noDestinasi){
        return destinasiDB.getDestinasiModelByNoDestinasi(noDestinasi);
    }

    @Override
    public List<DestinasiModel> retrieveListDestinasi(){
        return destinasiDB.findAll();
    }

    @Override
    public void deleteDestinasi(Long noDestinasi){
        DestinasiModel destinasi = destinasiDB.getDestinasiModelByNoDestinasi(noDestinasi);
        destinasiDB.delete(destinasi);
    }

    @Override
    public Mono<String> getStatus(Long noDestinasi) {
        return this.webClient.get().uri("/rest/destinasi/" + noDestinasi + "/status")
                .retrieve().bodyToMono(String.class);
    }

}
