package main;

public final class Main {

    public static void main(final String[] args) {
        HTTPClient client = new HTTPClient();

        try {
            client.download("http://ftp.riken.jp/net/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.5.10-bin.zip", "work/test.zip");
        } catch (HTTPClientException e) {
            e.printStackTrace();
        }
    }

    private Main() {
    }
}
