package br.com.studiotrek.impactaservice.heroku_wake;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HerokuWakeController {

    @RequestMapping(value = "/v2/wake", method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("Wake", HttpStatus.OK);
    }

}
