import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpClient extends FTP {

    public void login(String user, String pass){
        if(user != null && pass != null){
            user(user);
            pass(pass);
        }
    }

    public void retrieveFileStream(String fileName){
        if(fileName != null){
            System.out.println("start read file");
            Socket socket =  _openDataConnection("RETR", fileName);
            byte[] byteArray =  new byte[1024];
            FileOutputStream fileOutputStream;
            BufferedOutputStream bufferedOutputStream;
            if(socket != null){
                try{
                    InputStream inputStream =  socket.getInputStream();
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
        }
     }

    private Socket _openDataConnection(String command, String ard) {
        Socket socket = null;
        try {
              ServerSocket serverSocket = _serverSocketFactory.createServerSocket(0);
              port(getLocalAdress(), serverSocket.getLocalPort());
              sendMessage(command, ard);
              socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
