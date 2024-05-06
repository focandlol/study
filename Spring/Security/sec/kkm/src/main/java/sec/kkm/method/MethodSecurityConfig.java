package sec.kkm.method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
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
}
