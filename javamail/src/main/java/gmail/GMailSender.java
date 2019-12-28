package gmail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import common.ISender;
import common.MailServer;

/**
 * GMail �𗘗p�������[�����M�N���X.
 *
 * �O������F�Ώ�Google�A�J�E���g�ɂĈ��S���̒Ⴂ�A�v���ւ̃A�N�Z�X��L���ɂ��Ă��邱��. <br>
 * [Google �A�J�E���g���Ǘ�] > [�Z�L�����e�B] > [���S���̒Ⴂ�A�v���̃A�N�Z�X]
 *
 */
public final class GMailSender implements ISender {
    @Override
    public void send(final MailServer mailServer, final Address[] toList, final String subject, final String body) throws MessagingException {
        send(mailServer, toList, new Address[] {}, new Address[] {}, subject, body);
    }

    @Override
    public void send(final MailServer mailServer, final Address[] toList, final Address[] ccList, final String subject, final String body) throws MessagingException {
        send(mailServer, toList, new Address[] {}, new Address[] {}, subject, body);
    }

    @Override
    public void send(final MailServer mailServer, final Address[] toList, final Address[] ccList, final Address[] bccList, final String subject, final String body) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", mailServer.getHost());
        props.put("mail.smtp.port", mailServer.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.connectiontimeout", mailServer.getConnectionTimeout());
        props.put("mail.smtp.timeout", mailServer.getTimeout());

        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailServer.getUsername(), mailServer.getPassword());
            }
        });

        MimeMessage message = new MimeMessage(session);

        GMailProperties gmailProperties = (GMailProperties) mailServer;
        message.setFrom(gmailProperties.getFrom());
        message.setReplyTo(new Address[] {new InternetAddress(gmailProperties.getReplyTo())});
        message.setRecipients(Message.RecipientType.TO, toList);
        message.setRecipients(Message.RecipientType.CC, ccList);
        message.setRecipients(Message.RecipientType.BCC, bccList);

        message.setSubject(subject, mailServer.getCharset());
        message.setText(body, mailServer.getCharset());

        message.setHeader("Content-Transfer-Encoding", mailServer.getEncoding());

        Transport.send(message);
    }
}
