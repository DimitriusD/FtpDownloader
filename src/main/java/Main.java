import java.nio.file.Path;

public class Main {

    private static String fineName = "test.txt";

    public static void main(String[] args) {

        Path path = CommonUtils.fileLocation(fineName);

        FtpClient ftpClient =  new FtpClient();

        ftpClient.connect("127.0.0.1", 21);
        ftpClient.login("anonymous", "anonymous");
        ftpClient.retrieveFileStream("test.txt" , path);
    }
}
