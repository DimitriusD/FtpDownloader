import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {

            FtpClient ftpClient =  new FtpClient();
            ftpClient.connection(21, "127.0.0.1");
            ftpClient.retrieveFile("test.txt" );

    }
}
