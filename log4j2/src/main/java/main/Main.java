package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import common.conf.Log4j2Attributes;

/**
 * Apache log4j 2 �T���v���R�[�h.
 */
public final class Main {

    public static void main(final String[] args) {
        System.out.println("Start");

        // log4j 2 �ݒ���m�F���Ă݂�
        try {
            Log4j2Attributes logConf = Log4j2Attributes.newInstance("conf/log4j2.xml");
            logConf.getFileList().forEach(file -> {
                System.out.println(file.getName() + ": " + file.getFileName());
            });
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO �����������ꂽ catch �u���b�N
            e.printStackTrace();
        }

        // ���O�o��
        MyApplication app = new MyApplication();
        app.doBusinessLogic();


        System.out.println("End");
    }

    private Main() {
        // �������Ȃ�
    }
}
