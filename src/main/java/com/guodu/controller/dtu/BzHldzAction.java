package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzHldzServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzHldz")
public class BzHldzAction {

    @Autowired
    private BzHldzServiceImpl bzHldzServiceImpl;

}
