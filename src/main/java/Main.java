public class Main {
    public static void main(String[] args) {

        FtpClient ftpClient =  new FtpClient();
        ftpClient.connect("127.0.0.1", 21);
        ftpClient.retrieveFileStream("test.txt" );
    }
}
