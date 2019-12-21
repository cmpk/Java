package common;

import lombok.Getter;
import lombok.Setter;

public class MailProperties {
    @Getter @Setter
    private String from = null;

    @Getter @Setter
    private String username = null;

    @Getter @Setter
    private String password = null;

    @Getter @Setter
    private String charset = null;

    @Getter @Setter
    private String encoding = null;

    @Getter @Setter
    private String host = null;

    @Getter @Setter
    private String port = null;

    @Getter @Setter
    private int connectionTimeout = 10000;

    @Getter @Setter
    private int timeout = 10000;
}
