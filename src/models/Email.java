package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Email {
    private String emailId;
    private EmailType emailType;
    private String message;
    private LocalDateTime producedAt;

    public Email(String emailId, EmailType emailType, String message, LocalDateTime producedAt) {
        this.emailId = emailId;
        this.emailType = emailType;
        this.message = message;
        this.producedAt = producedAt;
    }

    public EmailType getType(){
        return this.emailType;
    }

    public LocalDateTime getProducedAt(){
        return this.producedAt;
    }
}
