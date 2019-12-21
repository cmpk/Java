package main;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import csv.CSVEntity;
import csv.CSVReader;
import csv.CSVValidator;

/**
 * お世話になったサイト.
 *
 * Ref:
 * https://qiita.com/5zm/items/89b7198cab74f2d0f4a1
 *
 */
public final class Main {
    public static void main(final String[] args) {
        Validator validator = CSVValidator.getValidator();
        CSVReader reader = new CSVReader();
        List<CSVEntity> list = null;
        try {
            list = reader.read((Class)Data.class, "files/data.csv");
            list.forEach(entity -> {
                Set<ConstraintViolation<CSVEntity>> constraintViolations = validator.validate(entity);
                int errorCount = constraintViolations.size();
                System.out.println("----------");
                System.out.println("validate error count : " + errorCount);
                if (errorCount > 0) {
                    printErrors(constraintViolations);
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return;
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return;
        }

        System.out.println("==========");
        list.forEach(entity -> {
            Data data = (Data) entity;
            System.out.println(data.getDate() + ", " + data.getTitle() + ", " + data.getCount() + ", " + data.getNotes());
        });
    }

    private static <T> void printErrors(final Set<ConstraintViolation<T>> constraintViolations) {
        for (ConstraintViolation<T> violation : constraintViolations) {
            System.out.println("MessageTemplate : " + violation.getMessageTemplate());
            System.out.println("Message : " + violation.getMessage());
            System.out.println("PropertyPath : " + violation.getPropertyPath());
        }
    }

    private Main() {
    }
}
