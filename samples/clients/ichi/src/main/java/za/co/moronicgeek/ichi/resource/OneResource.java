package za.co.moronicgeek.ichi.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/one")
public class OneResource {

    @RequestMapping(method = RequestMethod.GET, path = "/one")
    public String one(){
        return "1";
    }
}
