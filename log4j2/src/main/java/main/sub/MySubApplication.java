package main.sub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySubApplication {
    private final Logger logger = LogManager.getLogger();

    public void doBusinessLogic() {
        this.logger.debug("デバッグテスト");
        this.logger.info("情報テスト\r\n複数行も表示できるかな");
        this.logger.warn("警告テスト");
        this.logger.error("エラーテスト");
        this.logger.fatal("致命テスト");
    }
}
