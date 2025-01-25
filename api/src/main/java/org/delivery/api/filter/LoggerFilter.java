package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /** Pre-filter **/
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        /** Post-filter **/
        // Request log
        var requestHeaderValues = new StringBuilder();
        req.getHeaderNames().asIterator().forEachRemaining((String key) -> {
            var value = req.getHeader(key);
            requestHeaderValues.append("[").append(key).append(" : ").append(value).append("] ");
        });
        var requestBody = new String(req.getContentAsByteArray());

        // Response log
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach((String key) -> {
            var value = res.getHeader(key);
            responseHeaderValues.append("[").append(key).append(" : ").append(value).append("] ");
        });
        var responseBody = new String(res.getContentAsByteArray());

        // Logging
        var uri = req.getRequestURI();
        var method = req.getMethod();
        log.info(">> [{}] {} | header: {}, body: {}", method, uri, requestHeaderValues, requestBody);
        log.info("<< [{}] {} | header: {}, body: {}", method, uri, responseHeaderValues, responseBody);

        res.copyBodyToResponse();
    }
}
