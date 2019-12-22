package main;

import common.BaseLogger;

public class MyApplication extends BaseLogger {
    public void doBusinessLogic() {
        this.logger.trace("トレーステスト");
        this.logger.debug("デバッグテスト");
        this.logger.info("情報テスト");
        this.logger.warn("警告テスト");
        occurError();
        this.logger.fatal("致命テスト");
    }

    private boolean occurError( ) {
        boolean[] array = {};
        try {
            return array[0];
        } catch(Exception e) {
            this.logger.error("エラーテスト", e);
        }
        return true;
    }
}
