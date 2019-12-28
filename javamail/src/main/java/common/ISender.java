package common;

import javax.mail.Address;
import javax.mail.MessagingException;

public interface ISender {
    /**
     * ���[���𑗐M����.
     *
     * @param mailServer @see MailServer
     * @param toList ���[���� TO �Ɏw�肷��A�h���X.
     * @param subject ���[���̌���.
     * @param body ���[���{��.
     * @throws MessagingException ���[���쐬����ё��M���ɔ���.
     */
    void send(MailServer mailServer, Address[] toList, String subject, String body) throws MessagingException;

    /**
     * ���[���𑗐M����.
     *
     * @param mailServer @see MailServer
     * @param toList ���[���� TO �Ɏw�肷��A�h���X.
     * @param ccList ���[���� CC �Ɏw�肷��A�h���X.
     * @param subject ���[���̌���.
     * @param body ���[���{��.
     * @throws MessagingException ���[���쐬����ё��M���ɔ���.
     */
    void send(MailServer mailServer, Address[] toList, Address[] ccList, String subject, String body) throws MessagingException;

    /**
     * ���[���𑗐M����.
     *
     * @param mailServer @see MailServer
     * @param toList ���[���� TO �Ɏw�肷��A�h���X.
     * @param ccList ���[���� CC �Ɏw�肷��A�h���X.
     * @param bccList ���[���� BCC �Ɏw�肷��A�h���X.
     * @param subject ���[���̌���.
     * @param body ���[���{��.
     * @throws MessagingException ���[���쐬����ё��M���ɔ���.
     */
    void send(MailServer mailServer, Address[] toList, Address[] ccList, Address[] bccList, String subject, String body) throws MessagingException;
}
