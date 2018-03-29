package org.kevin.OwnBlog.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Kevin.Z on 2018/3/28.
 */
@Configuration
public class WebFilter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/addBlog","");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyFilter implements Filter {
        @Override
        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) sRequest;
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("login");
            if (obj != null && (boolean) obj) {
                filterChain.doFilter(sRequest, sResponse);
            } else {
                HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) sResponse);
                System.err.println("no login");
                wrapper.sendRedirect("/login");
            }
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }
    }
}
