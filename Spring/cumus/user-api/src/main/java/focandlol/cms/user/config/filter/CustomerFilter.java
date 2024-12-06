package focandlol.cms.user.config.filter;

import focandlol.cms.user.service.CustomerService;
import focandlol.config.JwtAuthenticationProvider;
import focandlol.domain.common.UserVo;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = request.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)) {
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        customerService.findByIdAndEmail(vo.getId(),vo.getEmail())
                .orElseThrow(() -> new ServletException("Invalid access"));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
