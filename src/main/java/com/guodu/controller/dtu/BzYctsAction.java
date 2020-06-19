package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzYctsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzYcts")
public class BzYctsAction {

    @Autowired
    private BzYctsServiceImpl bzYctsServiceImpl;
}
