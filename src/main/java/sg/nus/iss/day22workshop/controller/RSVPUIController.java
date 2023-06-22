package sg.nus.iss.day22workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import sg.nus.iss.day22workshop.RestTemplate.RSVPRestTemplate;
import sg.nus.iss.day22workshop.model.RSVP;

@Controller
@RequestMapping("/rsvps")
public class RSVPUIController {
    
    RSVPRestTemplate restTemplate;

    public RSVPUIController(){
        restTemplate = new RSVPRestTemplate();
    }

    @GetMapping("/add")
    public String RSVPAdd(Model model){
        model.addAttribute("rsvp", new RSVP());
        return "rsvpadd";
    }

    @PostMapping("/saveRSVP")
    public String postRSVP(@ModelAttribute RSVP rsvp){
        Boolean result = restTemplate.createRSVP(rsvp);
        return "home";
    }
}
