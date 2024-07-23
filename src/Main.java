import gateway.EmailProvider;
import models.Email;
import models.EmailType;
import service.Notification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Notification service = new Notification(new HashMap<>());
        EmailProvider emailProvider = new EmailProvider();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the user-id: ");
            String user = scanner.nextLine();
            if (Objects.equals(user, "exit")) {
                return;
            }

            System.out.println("Enter the email type: ");
            String emailType = scanner.nextLine();

            service.send(user, new Email(UUID.randomUUID().toString(), EmailType.valueOf(emailType.toUpperCase()), "default", LocalDateTime.now()), emailProvider);
        }
    }
}