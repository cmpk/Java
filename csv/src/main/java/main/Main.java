package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import csv.CSVReader;
import csv.CSVWriter;
import csv.ICSVRecord;
import csv.ICSVRecords;
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
        // CSVを読み込む
        ICSVRecords csvRecords = null;
        try {
            csvRecords = read();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // CSV内容のバリデート
        Validator validator = ValidationUtility.getValidator();
        for (ICSVRecord record : csvRecords) {
            Set<ConstraintViolation<ICSVRecord>> violations = validator.validate(record, CheckSequence.class);
            int errorCount = violations.size();
            System.out.println("----------");
            System.out.println("validate error count : " + errorCount);
            if (errorCount > 0) {
                printErrors(violations);
            }
        }

        // CSVを書き込む
        try {
            new CSVWriter().write(csvRecords, "work/out.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private static ICSVRecords read() throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        return new CSVReader().read((Class) DataRecords.class, "files/data.csv");
    }
}
