package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzLpjyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzLpjy")
public class BzLpjyAction {

    @Autowired
    private BzLpjyServiceImpl bzLpjyServiceImpl;
}
