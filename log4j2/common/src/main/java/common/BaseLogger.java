package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 * ���M���O���ʎ���.
 *
 * ���p���@
 * �ݒ�t�@�C���Ƃ��̓��e�ɂ��ẮA{@see <a href="https://logging.apache.org/log4j/2.x/manual/configuration.html">Apache log4j 2 Configuration</a>} ���Q�Ƃ̂��ƁB
 *
 */
public abstract class BaseLogger {
    protected final Logger logger = LogManager.getLogger();
}
