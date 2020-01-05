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
    @DisplayName("正常系")
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
    @DisplayName("ホスト不正な場合に、Exceptionが発生する")
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
    @DisplayName("パスワード不正な場合に、Exceptionが発生する")
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
                "javamail 単体テストから送信されたメールです。\r\n" +
                "単体テスト：" + getMethodName() + "\r\n" +
                "実行日時：" + Calendar.getInstance().getTime();
    }

    private String getSubject() {
        return "javamail UT : from " + getMethodName() + " : " + Calendar.getInstance().getTime();
    }

    /**
     * 実行メソッド名を取得する.
     *
     * @return
     */
    private String getMethodName() {
        // スタックトレースから本メソッドを呼び出しているメソッドを呼び出しているメソッド名を取得する
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
