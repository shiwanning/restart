package com.restart.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restart.Exception.BaseException;
import com.restart.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "errorFilter", urlPatterns = "/*")
public class ErrorHandlerFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        try {
            filterChain.doFilter(request, res);
        } catch (BaseException e) {
            LOGGER.error(e.getErrorCode());
            BaseResponse baseResponse = new BaseResponse(false);
            baseResponse.setErrorMessage(e.getErrorCode());

            settingResponse(res);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(res.getWriter(), baseResponse);

            res.flushBuffer();

        } catch (Exception e) {

            LOGGER.error(e.getMessage());

            BaseResponse baseResponse = new BaseResponse(false);
            baseResponse.setErrorMessage(e.getMessage());

            settingResponse(res);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(res.getWriter(), baseResponse);

            res.flushBuffer();

        }



    }

    @Override
    public void destroy() {

    }

    private void settingResponse(HttpServletResponse res){
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
