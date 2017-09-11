package za.co.moronicgeek.san;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreeResource {

    @RequestMapping(method = RequestMethod.GET, path = "/three")
    public String three(){
        return "3";
    }
}
