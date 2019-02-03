import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FTP {

    private static final int _defaultPort =  21;

    private int port;

    private String host;

    protected Socket socket;

    protected BufferedReader socketBufferedReader;

    protected BufferedWriter socketBufferedWriter;

    public void connection(int port, String host){
        this.port = port;
        this.host =  host;
    }

    InetAddress getActiveHost() {
        try{
            return host != null && !host.isEmpty() ? InetAddress.getByName(host) : getDefaultHost();
        }catch (UnknownHostException ex){
            return null;
        }

    }

    int getActivePort() {
        return port != 0 ? port :  _defaultPort;
    }


    /**
     *
     * @return null at first tine, in future was added default sources for download
     */
    private InetAddress getDefaultHost() {
        return null;
    }


}
