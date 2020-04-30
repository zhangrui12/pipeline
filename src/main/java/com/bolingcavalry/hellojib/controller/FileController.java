package com.bolingcavalry.hellojib.controller;

import com.bolingcavalry.hellojib.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author liaohua1
 * @date 2020/4/30 15:32
 */
@RestController
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/download")
    public String hello(){
        try {
            new FileUtils().write("uums.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success" ;
    }

    @RequestMapping("/read")
    public String read() {
        String content = new FileUtils().readFileByLines("/whale/file/preset_" + "SENSECITY" + ".sql");
        return content;
    }
}
