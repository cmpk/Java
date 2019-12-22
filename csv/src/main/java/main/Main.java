package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import csv.CSVReader;
import csv.ICSVEntity;
import validation.ValidationUtility;
import validation.seq.CheckSequence;

/**
 * お世話になったサイト.
 *
 * Ref: https://qiita.com/5zm/items/89b7198cab74f2d0f4a1
 *
 */
public final class Main {
    public static void main(final String[] args) {
        Validator validator = ValidationUtility.getValidator();
        List<ICSVEntity> list = null;
        try {
            list = read();
            list.forEach(entity -> {
                Set<ConstraintViolation<ICSVEntity>> violations = validator.validate(entity, CheckSequence.class);
                int errorCount = violations.size();
                System.out.println("----------");
                System.out.println("validate error count : " + errorCount);
                if (errorCount > 0) {
                    printErrors(violations);
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

    /**
     * CheckStyle warning 対策用メソッド.
     *
     * @return
     * @throws FileNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static List<ICSVEntity> read() throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        return new CSVReader().read((Class) Data.class, "files/data.csv");
    }
}
