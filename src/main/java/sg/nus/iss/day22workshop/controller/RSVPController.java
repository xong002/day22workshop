package sg.nus.iss.day22workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.day22workshop.model.RSVP;
import sg.nus.iss.day22workshop.repository.RSVPRepo;

@RestController
@RequestMapping("/api")
public class RSVPController {
    
    @Autowired
    RSVPRepo repo;

    @GetMapping("/rsvps")
    public ResponseEntity<List<RSVP>> getAll(){
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = repo.findAll();

        if(rsvps.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } return new ResponseEntity<List<RSVP>>(rsvps, HttpStatus.OK);
    }
    
    @GetMapping("/rsvp/{id}")
    public ResponseEntity<RSVP> getById(@PathVariable int id){
        RSVP rsvp = new RSVP();
        rsvp = repo.findById(id);

        if(rsvp == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } return new ResponseEntity<RSVP>(rsvp, HttpStatus.OK);
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<Integer> getCount(){
        int count = 0;
        count = repo.count();
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @PostMapping("/rsvps")
    public ResponseEntity<Boolean> saveRSVP(@RequestBody RSVP rsvp){
        Boolean saved = false;
        saved = repo.save(rsvp);
        if(saved == true){
            return new ResponseEntity<Boolean>(saved, HttpStatus.OK);
        } return new ResponseEntity<Boolean>(saved, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
