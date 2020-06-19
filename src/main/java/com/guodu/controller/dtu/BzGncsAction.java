package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzGncsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzGncs")
public class BzGncsAction {

    @Autowired
    private BzGncsServiceImpl bzGncsServiceImpl;

}
