package common;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * GMail �𗘗p�������[�����M�N���X.
 *
 * �O������F�Ώ�Google�A�J�E���g�ɂĈ��S���̒Ⴂ�A�v���ւ̃A�N�Z�X��L���ɂ��Ă��邱��.
 * [Google �A�J�E���g���Ǘ�] > [�Z�L�����e�B] > [���S���̒Ⴂ�A�v���̃A�N�Z�X]
 *
 */
public class GoogleSender implements ISender {
    @Override
    public void send(MailProperties mailProperties, Address[] toList, String subject, String body) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", mailProperties.getHost());
        props.put("mail.smtp.port", mailProperties.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.connectiontimeout", mailProperties.getConnectionTimeout());
        props.put("mail.smtp.timeout", mailProperties.getTimeout());

        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }
        });

        MimeMessage message = new MimeMessage(session);

        message.setFrom(mailProperties.getFrom());
        message.setReplyTo(new Address[] { new InternetAddress(mailProperties.getFrom()) });
        message.setRecipients(Message.RecipientType.TO, toList);

        message.setSubject(subject, mailProperties.getCharset());
        message.setText(body, mailProperties.getCharset());

        message.setHeader("Content-Transfer-Encoding", mailProperties.getEncoding());

        Transport.send(message);
    }
}
