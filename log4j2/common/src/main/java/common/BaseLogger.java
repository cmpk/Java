package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 * ロギング共通実装.
 *
 * 利用方法
 * 設定ファイルとその内容については、{@see <a href="https://logging.apache.org/log4j/2.x/manual/configuration.html">Apache log4j 2 Configuration</a>} を参照のこと。
 *
 */
public abstract class BaseLogger {
    protected final Logger logger = LogManager.getLogger();
}
