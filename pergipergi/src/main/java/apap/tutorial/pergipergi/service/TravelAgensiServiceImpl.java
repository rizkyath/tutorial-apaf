package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.TravelAgensiDb;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelAgensiServiceImpl implements TravelAgensiService {

    @Autowired
    TravelAgensiDb travelAgensiDb;

    @Override
    public void addAgensi(TravelAgensiModel travelAgensiModel){
        travelAgensiDb.save(travelAgensiModel);
    }

    @Override
    public List<TravelAgensiModel> getListAgensi(){
        return travelAgensiDb.findAllByOrderByNamaAgensiAsc();
    }

    @Override
    public TravelAgensiModel getAgensiByNoAgensi(Long noAgensi){
        Optional<TravelAgensiModel> agensi = travelAgensiDb.findByNoAgensi(noAgensi);
        if(agensi.isPresent()) return agensi.get();
        else return null;
    }

    @Override
    public TravelAgensiModel updateAgensi(TravelAgensiModel travelAgensi) {
        travelAgensiDb.save(travelAgensi);
        return travelAgensi;
    }

    @Override
    public TravelAgensiModel deleteByNoAgensi(Long noAgensi) {
        TravelAgensiModel deletedAgensi = getAgensiByNoAgensi(noAgensi);
        travelAgensiDb.deleteTravelAgensiModelByNoAgensi(noAgensi);
        return deletedAgensi;
    }

    @Override
    public boolean isTutup(TravelAgensiModel agensi){
        LocalTime waktuBuka = agensi.getWaktuBuka();
        LocalTime waktuTutup = agensi.getWaktuTutup();
        LocalTime sekarang = LocalTime.now();
        if (sekarang.compareTo(waktuBuka) < 0 || sekarang.compareTo(waktuTutup) > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean delAvail(TravelAgensiModel agensi){
        List<TourGuideModel> listGuide = agensi.getListTourGuide();
        if (isTutup(agensi) && listGuide.size() == 0)
            return true;
        else
            return false;
    }
}
