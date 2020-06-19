package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzCyjdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzCyjd")
public class BzCyjdAction {

    @Autowired
    private BzCyjdServiceImpl bzCyjdServiceImpl;
}
