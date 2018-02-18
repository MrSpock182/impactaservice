package br.com.studiotrek.impactaservice.base.teste;

import org.springframework.web.bind.annotation.*;

@RestController
public class TesteController {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@RequestBody @PathVariable("id") int id) {
        return "funcionou" + id;
    }

}
