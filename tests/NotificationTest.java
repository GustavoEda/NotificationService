import gateway.EmailProvider;
import models.Email;
import models.EmailType;
import org.junit.Test;
import service.Notification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationTest {

    static Email defaultNewsEmail() {
        return new Email(UUID.randomUUID().toString(), EmailType.NEWS, "default", LocalDateTime.now());
    }

    static Email defaultMarketingEmail() {
        return new Email(UUID.randomUUID().toString(), EmailType.MARKETING, "default", LocalDateTime.now());
    }

    private Notification service;
    private EmailProvider emailProvider;
    private Map<String, List<Email>> userNotifications;

    @Test
    public void testSendBelowRateLimit() {
        emailProvider = new EmailProvider();
        userNotifications = new HashMap<>();
        service = new Notification(userNotifications);

        String userId = "user1";
        Email email = defaultNewsEmail();

        service.send(userId, email, emailProvider);

        assertEquals(1, userNotifications.get(userId).size());
    }

    @Test
    public void testSendAboveRateLimit() {
        emailProvider = new EmailProvider();
        userNotifications = new HashMap<>();
        service = new Notification(userNotifications);

        String userId = "user1";
        Email email = defaultNewsEmail();

        service.send(userId, email, emailProvider);
        service.send(userId, email, emailProvider);

        assertEquals(1, userNotifications.get(userId).size());
    }

    @Test
    public void testSendWithinTimeWindow() {
        emailProvider = new EmailProvider();
        userNotifications = new HashMap<>();
        service = new Notification(userNotifications);

        String userId = "user1";
        Email email = defaultMarketingEmail();

        service.send(userId, email, emailProvider);
        service.send(userId, email, emailProvider);

        assertEquals(2, userNotifications.get(userId).size());
    }
}
