package hac.ex4.listeners;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main listeners
 */
@Component
@WebListener
public class SessionListenerCounter implements HttpSessionListener {
    /**
     * store currently active sessions
     */
    private final AtomicInteger activeSessions;

    /**
     * Main listeners to create sessions
     */
    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }


    /**
     * Main listeners  In charge of creating the session
     *
     * @param event htmp event
     */
    public void sessionCreated(final HttpSessionEvent event) {

        activeSessions.incrementAndGet();
        System.out.println("SessionListenerCounter +++ Total active session are " + activeSessions.get());
    }


    /**
     * Main listeners in charge of destroying the session
     *
     * @param event to destroy  a session
     */
    public void sessionDestroyed(final HttpSessionEvent event) {

        activeSessions.decrementAndGet();
        System.out.println("SessionListenerCounter --- Total active session are " + activeSessions.get());
    }
}
