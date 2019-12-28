package common;

import javax.mail.Address;
import javax.mail.MessagingException;

public interface ISender {
    /**
     * メールを送信する.
     *
     * @param mailServer @see MailServer
     * @param toList メールの TO に指定するアドレス.
     * @param subject メールの件名.
     * @param body メール本文.
     * @throws MessagingException メール作成および送信時に発生.
     */
    void send(MailServer mailServer, Address[] toList, String subject, String body) throws MessagingException;

    /**
     * メールを送信する.
     *
     * @param mailServer @see MailServer
     * @param toList メールの TO に指定するアドレス.
     * @param ccList メールの CC に指定するアドレス.
     * @param subject メールの件名.
     * @param body メール本文.
     * @throws MessagingException メール作成および送信時に発生.
     */
    void send(MailServer mailServer, Address[] toList, Address[] ccList, String subject, String body) throws MessagingException;

    /**
     * メールを送信する.
     *
     * @param mailServer @see MailServer
     * @param toList メールの TO に指定するアドレス.
     * @param ccList メールの CC に指定するアドレス.
     * @param bccList メールの BCC に指定するアドレス.
     * @param subject メールの件名.
     * @param body メール本文.
     * @throws MessagingException メール作成および送信時に発生.
     */
    void send(MailServer mailServer, Address[] toList, Address[] ccList, Address[] bccList, String subject, String body) throws MessagingException;
}
