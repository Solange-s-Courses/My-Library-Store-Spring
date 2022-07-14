package hac.ex4;

import hac.ex4.Beans.Cart;
import hac.ex4.listeners.SessionListenerCounter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/**
 * In charge of the servlets
 */
@Configuration
public class BeanConfiguration {

    /**
     * for the listeners of the beans
     *
     * @return listeners
     */
    @Bean
    public ServletListenerRegistrationBean<SessionListenerCounter> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListenerCounter> listenerRegBean = new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListenerCounter());
        return listenerRegBean;
    }

    /**
     * session cart
     *
     * @return the cart
     */
    @Bean
    @SessionScope
    public Cart sessionBeanCart() {
        return new Cart();
    }
}
