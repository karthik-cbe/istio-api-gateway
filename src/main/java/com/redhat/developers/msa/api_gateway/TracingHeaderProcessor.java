package com.redhat.developers.msa.api_gateway;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
public class TracingHeaderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);

        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuffer headerMsg = new StringBuffer("{");

        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            if(headerValue != null){
                headerMsg.append(String.format("{\"%s\":\"%s\"}",headerName,headerValue));
                headerMsg.append(",");
                log.info("Adding Header {} with value {}",headerName,headerValue);
                exchange.getIn().setHeader(headerName,headerValue);
            }
        }
        headerMsg.append("}");
        log.info("Request Headers:{}",headerMsg);
    }


}
