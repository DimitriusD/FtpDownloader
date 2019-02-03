import java.io.*;
import java.net.Socket;
public class FtpClient extends FTP {

    public static final String NETASCII_EOL = "\r\n";

    public boolean retrieveFile(String fileName){

        _openConnection();
        String retr = buildMessage("RETR", fileName);
        sendLine(buildMessage("USER", "anonymous"));
        sendLine(buildMessage("PASS", "anonymous"));
        sendLine(retr);
        byte[] byteArray =  new byte[1024];
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;

        if(socket != null){
            try{
                inputStream =  socket.getInputStream();
                fileOutputStream =  new FileOutputStream(fileName);
                bufferedOutputStream =  new BufferedOutputStream(fileOutputStream);
                int bytesRead = 0;
                while((bytesRead = inputStream.read(byteArray)) != -1){
                    bufferedOutputStream.write(byteArray, 0, bytesRead);
                    System.out.println(new String(byteArray, 0, bytesRead));
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return true;
     }

    private void sendLine(String line){
        try {
            socketBufferedWriter.write(line + "\r\n");
            socketBufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private String buildMessage(String command, String arg){
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(command);
        if(arg != null){
            stringBuilder.append(' ');
            stringBuilder.append(arg);
        }
        stringBuilder.append(NETASCII_EOL);
        return stringBuilder.toString();
    }

    private void _openConnection() {
        if(socket == null){
            try{
                socket =  new Socket(getActiveHost(), getActivePort());
                socketBufferedReader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socketBufferedWriter =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
