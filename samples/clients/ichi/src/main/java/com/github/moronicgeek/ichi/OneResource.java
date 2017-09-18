package com.github.moronicgeek.ichi;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class OneResource {

    @RequestMapping(path = "/one", method = RequestMethod.GET)
    public int one(){
        return 1;
    }

}
