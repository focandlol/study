package sec.kkm.method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.method.AuthorizationManagerAfterMethodInterceptor;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(prePostEnabled = false)
public class MethodSecurityConfig {

//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public Advisor preAuthorize() {
//        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(new MyPreAuthorizationManager());
//    }
//
//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public Advisor postAuthorize() {
//        return AuthorizationManagerAfterMethodInterceptor.postAuthorize(new MyPostAuthorizationManager());
//    }
//
//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public Advisor pointCutAdvisor(){
//        AspectJExpressionPointcut pattern = new AspectJExpressionPointcut();
//        pattern.setExpression("execution(* sec.kkm.DateService.getUser(..))");
//        AuthorityAuthorizationManager<MethodInvocation> manager = AuthorityAuthorizationManager.hasRole("USER");
//
//        return new AuthorizationManagerBeforeMethodInterceptor(pattern,manager);
//    }
//
//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public Advisor pointCutAdvisor2(){
//        AspectJExpressionPointcut pattern = new AspectJExpressionPointcut();
//        pattern.setExpression("execution(* sec.kkm.DateService.getUser(..))");
//
//        AspectJExpressionPointcut pattern2 = new AspectJExpressionPointcut();
//        pattern2.setExpression("execution(* sec.kkm.DateService.getOwner(..))");
//
//        ComposablePointcut composablePointcut = new ComposablePointcut((Pointcut) pattern);
//        composablePointcut.union((Pointcut) pattern2);
//
//        AuthorityAuthorizationManager<MethodInvocation> manager = AuthorityAuthorizationManager.hasRole("USER");
//
//        return new AuthorizationManagerBeforeMethodInterceptor(composablePointcut,manager);
//    }

    @Bean
    public MethodInterceptor customMethodInterceptor() {
        AuthorizationManager<MethodInvocation> authorizationManager = AuthenticatedAuthorizationManager.authenticated();
        return new CustomMethodInterceptor(authorizationManager);
    }

    @Bean
    public Pointcut pointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sec.kkm.DateService.*(..))");
        return pointcut;
    }

    @Bean
    public Advisor serviceAdvisor() {
        return new DefaultPointcutAdvisor(pointcut(), customMethodInterceptor());
    }
}
