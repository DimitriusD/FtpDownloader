import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {

    public static Path fileLocation(String file){
        Path filePath;
        String os =  System.getProperty("os.name");
        if(os.startsWith("Linux")){
            filePath = Paths.get("/var/tem/" + file);
        }else if(os.startsWith("Windows")){
            filePath =  Paths.get("C://SystemLog" + file);
        }else{
            filePath = Paths.get("/var/tem/"+ file);
        }
        return filePath;
    }
}
