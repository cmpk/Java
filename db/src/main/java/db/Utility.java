package db;

import java.util.Calendar;

public final class Utility {
    /**
     * {@link java.util.Date}を{@link java.sql.Date}に変換する.
     * お世話になったサイト：
     * @see <a href="https://higayasuo.hatenablog.com/entry/20090219/1235020303">java.util.Dateをjava.sql.Dateにきちんと変換する方法</a>
     * @param utilDate
     * @return
     */
    public static java.sql.Date toSQLDate(final java.util.Date utilDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    private Utility() {
    }
}
