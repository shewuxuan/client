package com.guodu.controller.dtu;

import com.guodu.service.impl.dtu.BzKggServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bzKgg")
public class BzKggAction {

    @Autowired
    private BzKggServiceImpl bzKggServiceImpl;
}
