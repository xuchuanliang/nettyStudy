package com.snail.nettystudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyStudyApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(Collections.EMPTY_LIST.contains("111"));
    }

}

