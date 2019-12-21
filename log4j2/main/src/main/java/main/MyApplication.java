package main;

import common.BaseLogger;

public class MyApplication extends BaseLogger {
    public void doBusinessLogic() {
        this.logger.trace("トレーステスト");
        this.logger.debug("デバッグテスト");
        this.logger.info("情報テスト");
        this.logger.warn("警告テスト");
        this.logger.error("エラーテスト");
        this.logger.fatal("致命テスト");
    }
}
