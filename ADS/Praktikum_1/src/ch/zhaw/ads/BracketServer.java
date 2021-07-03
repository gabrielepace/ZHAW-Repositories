package ch.zhaw.ads;

/**
 * ADS FS2019 
 * Praktikum 1
 * Aufgabe 3 - Klammertester
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class BracketServer implements CommandExecutor {

    ListStack bracketStack;


    // Bsp. (3+4)-{4-4}
    public boolean checkBrackets(String arg) {
        bracketStack = new ListStack();

        for (int i = 0; i < arg.length(); i++) {
            switch (String.valueOf(arg.charAt(i))) {
                case "{":
                    bracketStack.push("{");
                    break;
                case "(":
                    bracketStack.push("(");
                    break;
                case "[":
                    bracketStack.push("[");
                    break;
                case "<":
                    if (i + 1 < arg.length() && arg.charAt(i + 1) == '*') {
                        bracketStack.push("<*");
                        i += 1;
                    } else bracketStack.push("<");
                    break;
                case ")":
                    if (bracketStack.peek().equals("(")) {
                        bracketStack.pop();
                    } else return false;
                    break;
                case "]":
                    if (bracketStack.peek().equals("[")) {
                        bracketStack.pop();
                    } else return false;
                    break;
                case "}":
                    if (bracketStack.peek().equals("{")) {
                        bracketStack.pop();
                    } else return false;
                    break;
                case "*":
                    if (i + 1 < arg.length() && arg.charAt(i + 1) == '>' && bracketStack.peek().equals("<*")) {
                        bracketStack.pop();
                        i += 1;
                    } else if(arg.charAt(i + 1) == '>') return false;
                    break;
                case ">":
                    if (bracketStack.peek().equals("<")) {
                        bracketStack.pop();
                    } else return false;
                    break;
                default:
                    break;
            }
        }
        return bracketStack.isEmpty();
    }

    @Override
    public String execute(String command) throws Exception {
        String rechnung = "[ (3 +3)· 35 >+3]· {3 +2}";
        if (checkBrackets(rechnung)) {
            System.out.println("Alles Korrekt!");
        }
        return "Perfekt!";
    }
}