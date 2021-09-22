package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import java.util.List;

public interface TravelAgensiService {
    //Method untuk menambah Agensi
    void addAgensi(TravelAgensiModel travelAgensi);

    // Method untuk mendapatkan daftar agensi yang telah tersimpan
    List<TravelAgensiModel> getListAgensi();

    //Method untuk mendapatkan data agensi berdasarkan id agensi
    TravelAgensiModel getAgensiByidAgensi(String idAgensi);

    //Method untuk meng-update id agensi
    void updateIdAgensi(String s1, String s2);

    //Method untuk remove data by id agensi
    void removeByIdAgensi(String s);
}
