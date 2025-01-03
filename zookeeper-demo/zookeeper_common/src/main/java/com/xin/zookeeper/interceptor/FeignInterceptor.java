package com.xin.zookeeper.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        //传递令牌
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null){

            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

            if (request != null){

                Enumeration<String> headerNames = request.getHeaderNames();

                while (headerNames.hasMoreElements()){

                    String headerName = headerNames.nextElement();

                    if ("token".equals(headerName)){

                        String headerValue = request.getHeader(headerName);

                        //传递token
                        requestTemplate.header(headerName,headerValue);
                    }
                }
            }
        }
    }
}
