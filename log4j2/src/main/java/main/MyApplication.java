package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyApplication {
    private final Logger logger = LogManager.getLogger();

    public final void doBusinessLogic() {
        long start = System.currentTimeMillis();

        this.logger.debug("�f�o�b�O�e�X�g");
        this.logger.info("���e�X�g\r\n�����s���\���ł��邩��");
        this.logger.warn("�x���e�X�g");
        occurError();
        this.logger.fatal("�v���e�X�g");

        long end = System.currentTimeMillis();
        this.logger.trace("���s���ԁF" + (end - start)  + "ms");
    }

    private boolean occurError() {
        boolean[] array = {};
        try {
            return array[0];
        } catch (Exception e) {
            this.logger.error("�G���[�e�X�g", e);
        }
        return true;
    }
}
