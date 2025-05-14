import java.util.List;

public class EmailNotificationSystem {
    private final EmailService emailService;

    public EmailNotificationSystem(EmailService emailService) {
        this.emailService = emailService;
    }

    // Send a single email
    public void notifyUser(String emailAddress, String message) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        emailService.sendEmail(emailAddress, message);
    }

    public void notifyUsersBatch(List<String> emails, String message) {
        if(emails == null || emails.isEmpty()) {
            throw new IllegalArgumentException("Emails list cannot be null or empty");
        }

        for (String email : emails) {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email address cannot be null or empty");
            }
            emailService.sendEmail(email, message);
        }
    }
}
