package db.oracle;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * SQL*Loader の Windows の終了コード.
 * @see <a href="https://docs.oracle.com/cd/E57425_01/121/SUTIL/GUID-FDC9B490-7C23-4DEF-B826-9FDAEAF0FA64.htm">終了コードによる結果の検査と表示</a>
 *
 */
public enum SQLLoaderExitCode {
    SUCCESS(new Integer[] {0}, "すべての列が正常にロードされました。"),
    FAIL    (new Integer[] {1}, "コマンドラインのエラーまたは構文エラーか、Oracleエラーが発生しました。"),
    WARN    (new Integer[] {2}, "すべての行または一部の行が拒否または破棄されたか、ロードが中断されました。"),
    FATAL   (new Integer[] {3,4}, "ファイルのオープンやクローズ、メモリ領域確保などで、OS関連のエラーが発生しました。")
    ;

    @Getter
    private final Integer[] exitCodes; // int[] だと Arrays.asList().contains() が期待どおりの結果を返さない
    @Getter
    private final String message;

    public static SQLLoaderExitCode getExitCode(final int exitCode) {
        SQLLoaderExitCode[] exitCodeEnums = SQLLoaderExitCode.values();
        for (SQLLoaderExitCode exitCodeEnum : exitCodeEnums) {
            List<Integer> exitCodeList = Arrays.asList(exitCodeEnum.getExitCodes());
            if (exitCodeList.contains(exitCode)) {
                return exitCodeEnum;
            }
        }
        return null;
    }

    private SQLLoaderExitCode(Integer[] exitCodes, String message) {
        this.exitCodes = exitCodes;
        this.message = message;
    }
}
