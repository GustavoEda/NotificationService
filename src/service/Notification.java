package service;

import gateway.EmailProvider;
import models.Email;
import models.EmailType;
import models.RateLimit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Notification {

    Map<String, List<Email>> userNotifications;
    Map<EmailType, RateLimit> rates = RateLimit.RATE_LIMIT;

    public void send(String userId, Email email, EmailProvider emailProvider){
        RateLimit limit = rates.get(email.getType());
        userNotifications.computeIfAbsent(userId, k -> new ArrayList<>());
        List<Email> notifications = userNotifications.get(userId);

        List<Email> filteredEmails = notifications.stream()
                .filter(e -> (e.getType() == email.getType() && e.getProducedAt().isAfter(email.getProducedAt().minusSeconds(limit.windowInSeconds))))
                .collect(Collectors.toList());

        if (filteredEmails.size() < limit.maxCount) {
            emailProvider.send(userId, email);
            notifications.add(email);
        } else {
            System.out.println("logging");
        }
    }

    public Notification(Map<String, List<Email>> userNotifications){
        this.userNotifications = userNotifications;
    }
}
