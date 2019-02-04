public class FTP extends BaseSocket {

    public static final int DEFAULT_PORT =  21;

    public static final int DEFAULT_DATA_PORT =  20;

    public static final String NETASCII_EOL = "\r\n";

    private void _send(String command){
        try{
            _baseSocketWriter.write(command);
            _baseSocketWriter.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String _buildCommandMessage(String command, String arg){
        StringBuilder commandBuilder= new StringBuilder();
        commandBuilder.append(command);
        if(arg != null){
            commandBuilder.append(' ');
            commandBuilder.append(arg);
        }
        commandBuilder.append(NETASCII_EOL);
        return  commandBuilder.toString();
    }

    public void pass(String pass){
        String command = _buildCommandMessage("PASS", pass);
        _send(command);
    }

    public void user(String userName){
        String command = _buildCommandMessage("USER", userName);
        _send(command);
    }

    public void retr(String fileName){
        String command = _buildCommandMessage("RETR", fileName);
        _send(command);
    }
}
