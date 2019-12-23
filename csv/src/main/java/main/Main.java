package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import csv.CSVReader;
import csv.CSVWriter;
import csv.ICSVLine;
import csv.ICSVLines;
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
        ICSVLines csvLines = null;
        try {
            csvLines = read();
            for(ICSVLine line : csvLines) {
                Set<ConstraintViolation<ICSVLine>> violations = validator.validate(line, CheckSequence.class);
                int errorCount = violations.size();
                System.out.println("----------");
                System.out.println("validate error count : " + errorCount);
                if (errorCount > 0) {
                    printErrors(violations);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return;
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return;
        }

        try {
            new CSVWriter().write(csvLines, "files/out.csv");
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
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
    private static ICSVLines read() throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        return new CSVReader().read((Class) DataLines.class, "files/data.csv");
    }
}
