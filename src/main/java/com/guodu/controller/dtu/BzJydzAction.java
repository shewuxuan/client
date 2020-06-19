package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzJydzServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzJydz")
public class BzJydzAction {

    @Autowired
    private BzJydzServiceImpl bzJydzServiceImpl;

}
