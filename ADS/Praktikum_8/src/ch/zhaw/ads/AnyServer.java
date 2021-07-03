package ch.zhaw.ads;
public class AnyServer implements CommandExecutor {

   
    @Override
    public String execute(String command) {
        StringBuffer result = new StringBuffer(100);
        result.append("Die Eingabe war \"");
        result.append(command);
        result.append("\"\n");
        return result.toString();
    }
}