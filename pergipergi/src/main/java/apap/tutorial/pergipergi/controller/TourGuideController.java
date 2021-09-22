package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TourGuideService;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;

@Controller
public class TourGuideController {
    @Qualifier("tourGuideServiceImpl")
    @Autowired
    private TourGuideService tourGuideService;

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @GetMapping("/tour-guide/add/{noAgensi}")
    public String addTourGuideFormPage(@PathVariable Long noAgensi, Model model){
        TourGuideModel guide = new TourGuideModel();
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        guide.setAgensi(agensi);
        model.addAttribute("guide", guide);
        return "form-add-tour-guide";
    }

    @PostMapping("/tour-guide/add")
    public String addTourGuideSubmitPage(
            @ModelAttribute TourGuideModel tourGuide,
            Model model
    ){
        tourGuideService.addTourGuide(tourGuide);
        model.addAttribute("noTourGuide", tourGuide.getNoTourGuide());
        return "add-tour-guide";
    }

    @GetMapping("/tour-guide/update/{noTourGuide}")
    public String updateTourGuideFormPage(
            @PathVariable Long noTourGuide,
            Model model
    ){
        TourGuideModel guide = tourGuideService.getByNoTourGuide(noTourGuide);
        Long noAgensi = guide.getAgensi().getNoAgensi();
        model.addAttribute("guide", guide);
        model.addAttribute("noAgensi", noAgensi);
        return "form-update-tour-guide";
    }

    @PostMapping("/tour-guide/update")
    public String updateTourGuideSubmitPage(
            @ModelAttribute TourGuideModel guide,
            Model model
    ){
        TravelAgensiModel agensi = guide.getAgensi();
        if(agensi.getWaktuBuka().compareTo(LocalTime.now()) > 0 && LocalTime.now().compareTo(agensi.getWaktuTutup()) > 0){
            TourGuideModel updatedTourGuide = tourGuideService.updateTourGuide(guide);
            model.addAttribute("noTourGuide", guide.getNoTourGuide());
            model.addAttribute("noAgensi", guide.getAgensi().getNoAgensi());
            return "update-tour-guide";
        }
        else{
            return "error-page";
        }
    }
}
