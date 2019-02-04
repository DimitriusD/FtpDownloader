import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BaseSocket {

    protected Socket _baseSocket;

    protected SocketFactory _socketFactory =  SocketFactory.getDefault();;

    protected ServerSocketFactory _serverSocketFactory =  ServerSocketFactory.getDefault();

    protected BufferedReader _baseSocketReader;

    protected BufferedWriter _baseSocketWriter;

    public void connect(String host, int port){
        try{
            _baseSocket =  _socketFactory.createSocket();
            _baseSocket.connect(new InetSocketAddress(host, port), 0);
            _baseSocketWriter =  new BufferedWriter(new OutputStreamWriter(_baseSocket.getOutputStream()));
            _baseSocketReader =  new BufferedReader(new InputStreamReader(_baseSocket.getInputStream()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int getLocalPort(){
        return _baseSocket.getLocalPort();
    }

    public  int getRemotePort(){
        return _baseSocket.getPort();
    }

    public InetAddress getLocalAdress(){
        return _baseSocket.getLocalAddress();
    }

    public InetAddress getRemoteAdress(){
        return _baseSocket.getInetAddress();
    }
}
