package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzYktsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzYkts")
public class BzYktsAction {

    @Autowired
    private BzYktsServiceImpl bzYktsServiceImpl;

}
