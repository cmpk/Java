package main.sub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySubApplication {
    private final Logger logger = LogManager.getLogger();

    public void doBusinessLogic() {
        this.logger.debug("�f�o�b�O�e�X�g");
        this.logger.info("���e�X�g\r\n�����s���\���ł��邩��");
        this.logger.warn("�x���e�X�g");
        this.logger.error("�G���[�e�X�g");
        this.logger.fatal("�v���e�X�g");
    }
}
