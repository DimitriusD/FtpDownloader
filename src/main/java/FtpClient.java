import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class FtpClient extends FTP {

    public void login(String user, String pass){
        if(user != null && pass != null){
            user(user);
            pass(pass);
        }
    }

    public void retrieveFileStream(String fileName,  Path path ){
        if(fileName != null){
            System.out.println("start read file");
            Socket socket =  _openDataConnection("RETR", fileName);
            byte[] byteArray =  new byte[1024];
            FileOutputStream fileOutputStream;
            BufferedOutputStream bufferedOutputStream;
            if(socket != null){
                try{
                    InputStream inputStream =  socket.getInputStream();
                    File file = path.toFile();
                    if(file.getParentFile().mkdirs() && file.createNewFile()){
                        fileOutputStream =  new FileOutputStream(file);
                        bufferedOutputStream =  new BufferedOutputStream(fileOutputStream);
                        int bytesRead = 0;
                        while((bytesRead = inputStream.read(byteArray)) != -1){
                            bufferedOutputStream.write(byteArray, 0, bytesRead);
                            System.out.println(new String(byteArray, 0, bytesRead));
                        }
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
