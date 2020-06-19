package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzWgpzServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzWgpz")
public class BzWgpzAction {

    @Autowired
    private BzWgpzServiceImpl bzWgpzServiceImpl;
}
