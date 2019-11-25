package com.pandax.litemall;

import com.pandax.litemall.utils.FileUploadUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LitemallApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        System.out.println(FileUploadUtils.getRandomFileName());
        System.out.println(FileUploadUtils.getFilePath());
    }
}
