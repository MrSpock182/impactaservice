package br.com.studiotrek.impactaservice.login.controller;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.login.regra.LoginRegra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@RestController
public class LoginController {

    @RequestMapping(value = "/login/{rm}/{senha}", method = RequestMethod.GET)
    public ResponseEntity<Login> get(@RequestBody @PathVariable("rm") String rm, @PathVariable("senha") String senha) {
        try {
            Login login = new Login();
            login = new LoginRegra(login).request(rm, senha);
            return new ResponseEntity<>(login, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(new Login(ex.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Login(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/hora", method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        try {
            Calendar c = new GregorianCalendar();
            c.setTimeZone(TimeZone.getTimeZone("GMT-3"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
            String strDate = sdf.format(c.getTime());

            return new ResponseEntity<>(strDate, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
