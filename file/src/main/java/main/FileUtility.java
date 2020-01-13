package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FilenameUtils;

/**
 * ファイル操作機能を提供するクラス.
 *
 */
public final class FileUtility {
    public static final String DATE_FORMAT = "_yyyyMMdd";

    /**
     * 日付フォーマットに {@link DATE_FORMAT} を指定して, ファイルを日付付きの名前にリネームする.
     * @param sourceFilepath リネーム対象ファイルのパス
     * @param destDirpath リネーム後のファイルの配置先ディレクトリ
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void renameFileWithDateExpression(final String sourceFilepath, final String destDirpath) throws FileNotFoundException, IOException {
        renameFileWithDateExpression(sourceFilepath, destDirpath, DATE_FORMAT);
    }

    /**
     * ファイルを日付付きの名前にリネームする.
     * @param sourceFilepath リネーム対象ファイルのパス
     * @param destDirpath リネーム後のファイルの配置先ディレクトリ
     * @param dateFormat 日付フォーマット
     * @throws FileNotFoundException リネーム対象ファイルが存在しない場合
     * @throws IOException リネーム後のファイルの配置先ディレクトリ作成に失敗した場合
     */
    public static void renameFileWithDateExpression(final String sourceFilepath, final String destDirpath, final String dateFormat) throws FileNotFoundException, IOException {
        if (sourceFilepath == null) {
            throw new FileNotFoundException("リネーム対象ファイルが指定されていません。リネーム対象ファイル：" + sourceFilepath);
        }

        File sourceFile = new File(sourceFilepath);
        if (!sourceFile.exists()) {
            throw new FileNotFoundException("リネーム対象ファイルが存在しません。リネーム対象ファイル：" + sourceFilepath);
        }

        if (destDirpath == null) {
            throw new FileNotFoundException("リネーム後のファイルの配置先ディレクトリが指定されていません。配置先ディレクトリ：" + destDirpath);
        }

        // ファイル名分解
        String filename = sourceFile.getName();
        String extention = FilenameUtils.getExtension(filename);
        String fileNameWithoutExtension = FilenameUtils.removeExtension(filename);

        // リネーム後配置先作成
        File destDir = new File(destDirpath);
        if (!destDir.exists()) {
            if (!destDir.mkdirs()) {
                throw new IOException("リネーム後のファイルの配置先ディレクトリを作成できませんでした。リネーム後の配置先ディレクトリ：" + destDirpath);
            }
        }

        // リネーム後ファイルパス生成
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat == null ? DATE_FORMAT : dateFormat);
        String dateExpression = sdf.format(cal.getTime());
        File destFile = new File(destDirpath + "/" + fileNameWithoutExtension + dateExpression + "." + extention);

        // リネーム
        if (!sourceFile.renameTo(destFile)) {
            throw new IOException("リネームに失敗しました。リネーム対象ファイル：" + sourceFilepath + ", リネーム後のファイル：" + destFile.getPath());
        }
    }

    private FileUtility() {
    }
}
