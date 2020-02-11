package multipj.pj2;

import java.io.File;

import org.apache.commons.io.FileUtils;

import multipj.common.Common;

public class PJ2 {
    /**
     * 実験用に Apache Commons IO を使用する
     */
    public void useCommonsIO() {
        String currentDirPath = Common.getCurrentDirPath();
        long size = FileUtils.sizeOfDirectory(new File(currentDirPath));
        System.out.println(Common.getClassName() + "#" + Common.getMethodName() + ": Current directory size is " + size);
    }
}
