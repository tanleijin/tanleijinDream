package com.tan.dream.common.generator.service.impl;

import com.tan.dream.common.generator.service.GeneratorService;
import com.tan.dream.common.generator.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;

/**
 * @Author: tanleijin
 * @description ()
 * @Date:2018/5/9 15:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorServiceImplTest {

    @Autowired
    GeneratorServiceImpl generatorService;

    @Test
    public void generatorCode() throws Exception {

        String[] tables = new String[]{"sys_user"};
        byte[] bytes = generatorService.generatorCode(tables);

        OutputStream out = new FileOutputStream("c:/test.zip");

        IOUtils.write(bytes, out);

    }

}