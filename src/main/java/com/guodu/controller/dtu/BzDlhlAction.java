package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzDlhlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzDlhl")
public class BzDlhlAction {

    @Autowired
    private BzDlhlServiceImpl bzDlhlServiceImpl;
}
