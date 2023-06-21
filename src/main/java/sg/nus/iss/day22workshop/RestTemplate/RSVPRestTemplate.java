package sg.nus.iss.day22workshop.RestTemplate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.nus.iss.day22workshop.model.RSVP;

@Service
public class RSVPRestTemplate {
    
    private static final String RSVP_ENDPOINT_URL = "http://localhost:4000/api/rsvps";

    private static RestTemplate restTemplate = new RestTemplate();

    public List<RSVP> getRSVPS(){
        ResponseEntity<List<RSVP>> results = restTemplate.exchange(RSVP_ENDPOINT_URL,HttpMethod.GET, new HttpEntity<String>("parameters"), new ParameterizedTypeReference<List<RSVP>>() {
        });
        return results.getBody();
    }

    public RSVP findById(int id){
        ResponseEntity<RSVP> result = restTemplate.getForEntity("http://localhost:4000/api/" + id, RSVP.class);
        return result.getBody();
      }

    public Boolean createRSVP(RSVP rsvp){
        ResponseEntity<Boolean> result = restTemplate.postForEntity(RSVP_ENDPOINT_URL, rsvp, Boolean.class);

        return result.getBody();
    }

    // public Boolean updateRSVP(RSVP rsvp){
    //    ResponseEntity<Boolean> results = restTemplate.exchange(RSVP_ENDPOINT_URL,HttpMethod.PUT, new HttpEntity<String>("parameters"), Boolean.class);

    //    return results.getBody();
    // }
}
