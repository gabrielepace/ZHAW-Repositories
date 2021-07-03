package ch.zhaw.ads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ADS FS2019 
 * Praktikum 1
 * Aufgabe 4 - XML Wellformed Tester
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class XmlTesterServer implements CommandExecutor {
    private static final String REGEX = "<[^?!][^>]*>|<.>";
    private String input;
    ListStack tagStack = new ListStack();

    public boolean checkWellformed(String arg) {
        input = arg;
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(input);

        while(m.find()) {

            if(tagStack.isEmpty()) {
                tagStack.push(getTagName(m.group()));
            }
            else if(tagStack.peek().toString().equals(m.group().replace("/",""))) {
                tagStack.pop();
            } else {
                tagStack.push(getTagName(m.group()));
            }

        }

        return tagStack.isEmpty();
    }

    private String getTagName(String tag) {
        return tag.split(" |>")[0] + ">";
    }

    @Override
    public String execute(String command) throws Exception {
        if(checkWellformed(command)) {
            return "Well Done!";
        } else {
            return "Dumme Siech!";
        }
    }
}