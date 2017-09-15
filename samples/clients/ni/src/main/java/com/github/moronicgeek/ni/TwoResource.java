package com.github.moronicgeek.ni;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/two")
public class TwoResource {

    @RequestMapping(method = RequestMethod.GET, path = "/two")
    public String two(){
        return "2";
    }
}
