package main;

import common.BaseLogger;

public class MyApplication extends BaseLogger {
    public void doBusinessLogic() {
        this.logger.trace("�g���[�X�e�X�g");
        this.logger.debug("�f�o�b�O�e�X�g");
        this.logger.info("���e�X�g");
        this.logger.warn("�x���e�X�g");
        occurError();
        this.logger.fatal("�v���e�X�g");
    }

    private boolean occurError( ) {
        boolean[] array = {};
        try {
            return array[0];
        } catch(Exception e) {
            this.logger.error("�G���[�e�X�g", e);
        }
        return true;
    }
}
