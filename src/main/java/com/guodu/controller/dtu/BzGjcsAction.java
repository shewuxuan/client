package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzGjcsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzGjcs")
public class BzGjcsAction {

    @Autowired
    private BzGjcsServiceImpl bzGjcsServiceImpl;

}
