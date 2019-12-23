package csv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public final class CSVUtility {
    /**
     * String型に変換する.
     * 引数が null の場合は、空文字に変換する.
     * @param obj String型に変換するオブジェクト
     * @return 変換した文字列
     */
    public static String toString(Object obj) {
        return Objects.toString(obj, "");
    }

    /**
     * String型に変換する.
     * 引数が null の場合は、空文字に変換する.
     * @param date String型に変換する日付オブジェクト
     * @param format フォーマット
     * @return 変換した文字列
     */
    public static String toString(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        return sdFormat.format(date);
    }

    private CSVUtility() {
    }
}
