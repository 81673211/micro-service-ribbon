package com.fred.springcloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 *
 * <b>Description:.</b><br> 
 * @author <b>sil.zhou</b>
 * <br><b>ClassName:</b> 
 * <br><b>Date:</b> 2019/4/11 21:12
 */
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorCame")
    public String hi(String name) {
        return restTemplate.getForObject("http://service-provider/hi?name=" + name, String.class);
    }

    public String errorCame(String name) {
        return String.format("hi, %s, error from hystrix!", name);
    }
}
