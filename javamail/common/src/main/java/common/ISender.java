package common;

import javax.mail.Address;
import javax.mail.MessagingException;

public interface ISender {
    void send(MailProperties mailProperties, Address[] toList, String subject, String body) throws MessagingException;
}
