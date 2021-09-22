package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import apap.tutorial.pergipergi.service.TravelAgensiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TravelAgensiController {
    @Autowired
    private TravelAgensiService travelAgensiService = new TravelAgensiServiceImpl();

    //Routing URL
    @RequestMapping("agensi/add")
    public String addAgensi(
        @RequestParam(value = "idAgensi", required = true) String idAgensi,
        @RequestParam(value = "namaAgensi", required = true) String namaAgensi,
        @RequestParam(value = "alamat", required = true) String alamat,
        @RequestParam(value = "noTelepon", required = true) String noTelepon,
        Model model
    ){
        //Membuat Objek TravelAgensiModel
        TravelAgensiModel agensi = new TravelAgensiModel(idAgensi, namaAgensi, alamat, noTelepon);
        //Memanggil Servis addAgensi
        travelAgensiService.addAgensi(agensi);
        //Add var id Agensi ke 'idAgensi' untuk dirender di thymeleaf
        model.addAttribute("idAgensi", idAgensi);

        return "add-agensi";
    }

    @RequestMapping("agensi/viewall")
    public String listAgensi(Model model){
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);
        return "viewall-agensi";
    }

    @RequestMapping("agensi/view")
    public String detailAgensi(
            @RequestParam(value= "idAgensi") String idAgensi,
            Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByidAgensi(idAgensi);
        model.addAttribute("agensi", agensi);
        return "view-agensi";
    }

    @RequestMapping("agensi/view/nama-agensi/{namaAgensi}")
    public String detailAgensiPV(
            @PathVariable(value = "namaAgensi") String namaAgensi,
            Model model
    ){
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        List<TravelAgensiModel> listAgensiByName = new ArrayList<>();
        for (TravelAgensiModel agensi : listAgensi){
            if (agensi.getNamaAgensi().equals(namaAgensi)){
                listAgensiByName.add(agensi);
            }
        }
        model.addAttribute("listAgensi", listAgensiByName);
        model.addAttribute("namaAgensi, namaAgensi");

        return "viewall-agensi-byname";
    }

    @RequestMapping("agensi/update/{idAgensi}/id-agensi/{newIdAgensi}")
    public String updateIdAgensi(
            @PathVariable(value="idAgensi") String idAgensi,
            @PathVariable(value="newIdAgensi") String newIdAgensi,
            Model model
    ){
        travelAgensiService.updateIdAgensi(idAgensi, newIdAgensi);
        TravelAgensiModel agensi = travelAgensiService.getAgensiByidAgensi(newIdAgensi);
        model.addAttribute("agensi", agensi);
        return "view-agensi";
    }

    @RequestMapping("agensi/delete/id-agensi/{idAgensi}")
    public String deleteAgensi(
            @PathVariable(value = "idAgensi") String idAgensi,
            Model model
    ){
        travelAgensiService.removeByIdAgensi(idAgensi);
        return "viewall-agensi";
    }

}
