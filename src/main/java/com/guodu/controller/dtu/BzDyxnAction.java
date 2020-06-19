package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzDyxnServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("bzDyxn")
public class BzDyxnAction {

    @Autowired
    private BzDyxnServiceImpl bzDyxnServiceImpl;
}
