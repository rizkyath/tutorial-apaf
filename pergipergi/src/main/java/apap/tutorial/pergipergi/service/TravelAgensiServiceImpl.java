package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelAgensiServiceImpl implements TravelAgensiService {
    private List<TravelAgensiModel> listAgensi;

    public TravelAgensiServiceImpl(){
        listAgensi = new ArrayList<>();
    }

    @Override
    public void addAgensi(TravelAgensiModel travelAgensiModel){
        listAgensi.add(travelAgensiModel);
    }

    @Override
    public List<TravelAgensiModel> getListAgensi(){
        return listAgensi;
    }

    @Override
    public TravelAgensiModel getAgensiByidAgensi(String idAgensi){
        for (TravelAgensiModel travelAgensiModel : getListAgensi()){
            if (idAgensi.equals(travelAgensiModel.getIdAgensi())){
                return travelAgensiModel;
            }
        }
        return null;
    }

    @Override
    public void updateIdAgensi(String id_old, String id_new){
        TravelAgensiModel agensi = getAgensiByidAgensi(id_old);
        agensi.setIdAgensi(id_new);
    }

    @Override
    public void removeByIdAgensi(String idAgensi){
        for (int i=0; i<listAgensi.size(); i++){
            if (idAgensi.equals(listAgensi.get(i).getIdAgensi())){
                listAgensi.remove(i);
            }
        }
    }
}
