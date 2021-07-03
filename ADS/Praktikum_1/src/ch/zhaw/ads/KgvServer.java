package ch.zhaw.ads;

/**
 * ADS FS2019
 * Praktikum 1
 * Aufgabe 1 – Kleinstes gemeinsames Vielfaches (kgV)
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class KgvServer implements CommandExecutor {

    public int ggt(int a, int b)
    {
        if (a > b)
        {
            return ggt(a-b,b);
        }
        else if (a < b)
        {
            return ggt(a,b-a);
        }
        else
        {
            return a;
        }
    }

    /**
     * @param a
     * @param b
     * @return kgv
     */
    public int kgv(int a, int b) {
        int calculation = a * b;

        if(calculation < 0) {
            calculation = calculation *(-1);
        }

        return calculation / ggt(a,b);
    }

    /**
     * @param command
     * @return Resultat
     */

    public String execute(String command) {
        String[] numbers = command.split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);

        return Integer.toString(kgv(a,b));
    }
}
