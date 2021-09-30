package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import apap.tutorial.pergipergi.service.DestinasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TravelAgensiController {

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @Qualifier("destinasiServiceImpl")
    @Autowired
    private DestinasiService destinasiService;

    @GetMapping("/agensi/add")
    public String addAgensiFormPage(Model model){
        model.addAttribute("agensi", new TravelAgensiModel());
        model.addAttribute("listDestinasi", destinasiService.getListDestinasi());

        return "form-add-agensi";
    }

    @PostMapping(value="/agensi/add", params = {"save-data"})
    public String addAgensiSubmitPage(
            @ModelAttribute TravelAgensiModel agensi,
            Model model
    ){
        travelAgensiService.addAgensi(agensi);
        model.addAttribute("noAgensi", agensi.getNoAgensi());
        return "add-agensi";
    }

    @PostMapping(value = "/agensi/add", params = {"tambah-row"})
    public String addAgensiTambahRow(
            @ModelAttribute TravelAgensiModel agensi,
            Model model
    ){
        for (DestinasiModel dest : agensi.getListDestinasi()){
            System.out.println(dest.getNegaraDestinasi());
        }
        model.addAttribute("agensi", agensi);
        model.addAttribute("listDestinasi", destinasiService.getListDestinasi());
        return "form-add-agensi";
    }

//    @PostMapping(value="/agensi/add", params = {"hapus-row"})
//    public String addAgensiHapusRow(
//            @ModelAttribute TravelAgensiModel agensi,
//            Model model
//    ){
//
//        model.addAttribute("agensi", agensi);
//        model.addAttribute("listDestinasi", destinasiService.getListDestinasi());
//        return "form-add agensi";
//    }

    @GetMapping("/agensi/viewall")
    public String listAgensi(Model model) {
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);
        return "viewall-agensi";
    }

    @GetMapping("/agensi/view")
    public String viewDetailAgensiPage(
            @RequestParam(value = "noAgensi") Long noAgensi,
            Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        List<TourGuideModel> listTourGuide = agensi.getListTourGuide();
        List<DestinasiModel> listDestinasi = agensi.getListDestinasi();

        model.addAttribute("agensi", agensi);
        model.addAttribute("listTourGuide", listTourGuide);
        model.addAttribute("listDestinasi", listDestinasi);

        return "view-agensi";
    }

    @GetMapping("/agensi/update/{noAgensi}")
    public String updateAgensiFormPage(
            @PathVariable Long noAgensi,
            Model model
    ){
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        model.addAttribute("agensi", agensi);
        return "form-update-agensi";
    }

    @PostMapping("/agensi/update")
    public String updateAgensiSubmitPage(
            @ModelAttribute TravelAgensiModel agensi,
            Model model
    ){
        TravelAgensiModel updatedAgensi = travelAgensiService.updateAgensi(agensi);
        model.addAttribute("noAgensi", updatedAgensi.getNoAgensi());
        return "update-agensi";
    }

    @RequestMapping("/agensi/delete/{noAgensi}")
    public String deleteAgensi(
            @PathVariable(value = "noAgensi") Long noAgensi,
            Model model
    ){
        TravelAgensiModel toBeDeletedAgensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (travelAgensiService.delAvail(toBeDeletedAgensi)){
            travelAgensiService.deleteByNoAgensi(noAgensi);
            model.addAttribute("noAgensi", noAgensi);
            return "delete-agensi";
        } else {
            String errorMsg = "Agensi masih buka, delete Agensi tidak berhasil";
            model.addAttribute("errorMsg", errorMsg);
            return "error-page";
        }

    }
}
