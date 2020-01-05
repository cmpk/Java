package gmail;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.ISender;
import common.MailServer;

public class GMailSenderTest {
    public static final String PROPERTIES_FILEPATH = "src/test/resources/mail.properties";
    public static final String PROPERTIES_CHARSET = "SJIS";

    @Test
    @DisplayName("����n")
    public final void testPositive() {
        ISender sender = new GMailSender();
        try {
            TestProperties mailProperties = new TestProperties(PROPERTIES_FILEPATH, PROPERTIES_CHARSET, Mode.NORMAL);
            sender.send((MailServer)mailProperties, mailProperties.getTo(), getSubject(), getBody());
        } catch (MessagingException | IOException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("�z�X�g�s���ȏꍇ�ɁAException����������")
    public final void testNegative_InvalidHostname() {
        ISender sender = new GMailSender();
        try {
            TestProperties mailProperties = new TestProperties(PROPERTIES_FILEPATH, PROPERTIES_CHARSET, Mode.INVALID_HOST);
            sender.send((MailServer)mailProperties, mailProperties.getTo(), getSubject(), getBody());
        } catch (AddressException | IOException e) {
            fail(e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("�p�X���[�h�s���ȏꍇ�ɁAException����������")
    public final void testNegative_InvalidPassword() {
        ISender sender = new GMailSender();
        try {
            TestProperties mailProperties = new TestProperties(PROPERTIES_FILEPATH, PROPERTIES_CHARSET, Mode.INVALID_PASSWORD);
            sender.send((MailServer)mailProperties, mailProperties.getTo(), getSubject(), getBody());
        } catch (AddressException | IOException e) {
            fail(e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getBody() {
        return
                "javamail �P�̃e�X�g���瑗�M���ꂽ���[���ł��B\r\n" +
                "�P�̃e�X�g�F" + getMethodName() + "\r\n" +
                "���s�����F" + Calendar.getInstance().getTime();
    }

    private String getSubject() {
        return "javamail UT : from " + getMethodName() + " : " + Calendar.getInstance().getTime();
    }

    /**
     * ���s���\�b�h�����擾����.
     *
     * @return
     */
    private String getMethodName() {
        // �X�^�b�N�g���[�X����{���\�b�h���Ăяo���Ă��郁�\�b�h���Ăяo���Ă��郁�\�b�h�����擾����
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    private enum Mode {
        NORMAL,
        INVALID_HOST,
        INVALID_PASSWORD
    };

    private class TestProperties extends GMailProperties {
        private final Mode mode;

        public TestProperties(String filepath, String charsetName, Mode mode) throws FileNotFoundException, IOException {
            super(filepath, charsetName);
            this.mode = mode;
        }

        @Override
        public final String getHost() {
            if (Mode.INVALID_HOST == this.mode) {
                return "test";
            }
            return super.getHost();
        }

        @Override
        public final String getPassword() {
            if (Mode.INVALID_PASSWORD == this.mode) {
                return "test";
            }
            return super.getPassword();
        }

        public final Address[] getTo() throws AddressException {
            Address[] toAddresses = new Address[] {new InternetAddress(this.properties.getPropertyString("to"))};
            return toAddresses;
        }
    }
}
