package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyApplication {
    private final Logger logger = LogManager.getLogger();

    public final void doBusinessLogic() {
        long start = System.currentTimeMillis();

        this.logger.debug("デバッグテスト");
        this.logger.info("情報テスト\r\n複数行も表示できるかな");
        this.logger.warn("警告テスト");
        occurError();
        this.logger.fatal("致命テスト");

        long end = System.currentTimeMillis();
        this.logger.trace("実行時間：" + (end - start)  + "ms");
    }

    private boolean occurError() {
        boolean[] array = {};
        try {
            return array[0];
        } catch (Exception e) {
            this.logger.error("エラーテスト", e);
        }
        return true;
    }
}
