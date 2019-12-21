package main;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import common.BaseTemplate;
import common.GoogleSender;
import common.ISender;
import common.MailProperties;

public class MyApplication {
    public void doBusinessLogic() {
        BaseTemplate template = new MyTemplate();
        String body = template.build();
        System.out.println("=== メール本文 ===");
        System.out.println(body);

        Address[] toAddresses;
        try {
            toAddresses = new Address[] {new InternetAddress("xxxxx@xxxxx")};
            send(toAddresses, "テストほげ", body);
        } catch (AddressException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    public void send(Address[] toAddresses, String subject, String body) {
        MailProperties mailProperties = new MailProperties();
        mailProperties.setFrom("xxxxx@gmail.com");
        mailProperties.setUsername("xxxxx@gmail.com");
        mailProperties.setPassword("xxxxx");
        mailProperties.setCharset("UTF-8");
        mailProperties.setEncoding("base64");
        mailProperties.setHost("smtp.gmail.com");
        mailProperties.setPort("587");
        ISender sender = new GoogleSender();
        try {
            sender.send(mailProperties, toAddresses, subject, body);
        } catch (AddressException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
