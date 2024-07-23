import gateway.EmailProvider;
import models.Email;
import models.EmailType;
import service.Notification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Main {

    static Email defaultNewsEmail() {
        return new Email(UUID.randomUUID().toString(), EmailType.NEWS, "default", LocalDateTime.now());
    }

    static Email defaultStatusEmail() {
        return new Email(UUID.randomUUID().toString(), EmailType.STATUS, "default", LocalDateTime.now());
    }

    static Email defaultMarketingEmail() {
        return new Email(UUID.randomUUID().toString(), EmailType.MARKETING, "default", LocalDateTime.now());
    }

    public static void main(String[] args) {
        Notification service = new Notification(new HashMap<>());
        EmailProvider emailProvider = new EmailProvider();

        String user1 = UUID.randomUUID().toString();
        service.send(user1, defaultNewsEmail(), emailProvider);
        service.send(user1, defaultNewsEmail(), emailProvider);
    }
}