import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class EmailNotificationSystemTest {

    // Task A
    @Test
    void testNotifyUserValidInput() {
        // Arrange
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        String testEmail = "user@test.com";
        String testMessage = "Test message";

        emailNotificationSystem.notifyUser(testEmail, testMessage);

        verify(mockEmailService).sendEmail(testEmail, testMessage);
    }

    @Test
    void testNotifyUserNullEmail() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        assertThrows(IllegalArgumentException.class, () -> {
            emailNotificationSystem.notifyUser(null, "Test message");
        });
    }

    @Test
    void testNotifyUserEmptyEmail() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        assertThrows(IllegalArgumentException.class, () -> {
            emailNotificationSystem.notifyUser("", "Test message");
        });
    }

    // Task B
    @Test
    void testBatchNotifyUser() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        String testMessage = "Test message";
        List<String> testEmails = List.of("user1@test.com", "user2@test.com", "user3@test.com");

        emailNotificationSystem.notifyUsersBatch(testEmails, testMessage);
        for (String email : testEmails) {
            verify(mockEmailService).sendEmail(email, testMessage);
        }
    }
    
    @Test
    void testBatchNotifyUserNullList() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        assertThrows(IllegalArgumentException.class, () -> {
            emailNotificationSystem.notifyUsersBatch(null, "Test message");
        });
    }
    
    @Test
    void testBatchNotifyUserEmptyList() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        assertThrows(IllegalArgumentException.class, () -> {
            emailNotificationSystem.notifyUsersBatch(List.of(), "Test message");
        });
    }
    
    @Test
    void testBatchNotifyUserNullEmail() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        String testMessage = "Test message";
        List<String> testEmails = new ArrayList<>();
        testEmails.add("user1@test.com");
        testEmails.add(null);
        testEmails.add("user3@test.com");


        assertThrows(IllegalArgumentException.class, () -> {
            emailNotificationSystem.notifyUsersBatch(testEmails, testMessage);
        });
    }

    @Test
    void testBatchNotifyUserEmptyEmail() {
        EmailService mockEmailService = mock(EmailService.class);
        EmailNotificationSystem emailNotificationSystem = new EmailNotificationSystem(mockEmailService);

        String testMessage = "Test message";
        List<String> testEmails = List.of("user1@test.com", "", "user3@test.com");

        assertThrows(IllegalArgumentException.class, () -> {
            emailNotificationSystem.notifyUsersBatch(testEmails, testMessage);
        });
    }

}
