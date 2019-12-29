package db.oracle;

import lombok.Getter;

/**
 * SQL*Loader の Windows の終了コード.
 * @see <a href="https://docs.oracle.com/cd/E57425_01/121/SUTIL/GUID-FDC9B490-7C23-4DEF-B826-9FDAEAF0FA64.htm">終了コードによる結果の検査と表示</a>
 *
 */
public enum SQLLoaderExitCodeWindows {
    SUCCESS(0, "すべての列が正常にロードされました。"),
    FAIL    (1, "コマンドラインのエラーまたは構文エラーか、Oracleエラーが発生しました。"),
    WARN    (2, "すべての行または一部の行が拒否または破棄されたか、ロードが中断されました。"),
    FATAL   (4, "ファイルのオープンやクローズ、メモリ領域確保などで、OS関連のエラーが発生しました。")
    ;

    @Getter
    private final int exitCode;
    @Getter
    private final String message;

    public static SQLLoaderExitCodeWindows getExitCode(final int exitCode) {
        SQLLoaderExitCodeWindows[] exitCodeEnums = SQLLoaderExitCodeWindows.values();
        for (SQLLoaderExitCodeWindows exitCodeEnum : exitCodeEnums) {
            if (exitCodeEnum.getExitCode() == exitCode) {
                return exitCodeEnum;
            }
        }
        return null;
    }

    private SQLLoaderExitCodeWindows(int exitCode, String message) {
        this.exitCode = exitCode;
        this.message = message;
    }
}
