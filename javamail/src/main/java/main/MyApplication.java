package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import common.BaseTemplate;
import common.ISender;
import common.MailServer;
import freemarker.template.TemplateException;
import gmail.GMailProperties;
import gmail.GMailSender;

public final class MyApplication {
    public void doBusinessLogic() {
        BaseTemplate template = new MyTemplate();
        String body = null;
        try {
            body = template.build("./conf", "body.ftl");
            System.out.println("=== メール本文 ===");
            System.out.println(body);
        } catch (TemplateException | IOException e1) {
            e1.printStackTrace();
        }

        Address[] toAddresses;
        try {
            toAddresses = new Address[] {new InternetAddress("xxxxx@xxxxx")};
            send(toAddresses, "テストほげ", body);
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    public void send(final Address[] toAddresses, final String subject, final String body) {

        ISender sender = new GMailSender();
        try {
            MailServer mailProperties = new GMailProperties("conf/mail.properties", "SJIS");
            sender.send(mailProperties, toAddresses, subject, body);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
