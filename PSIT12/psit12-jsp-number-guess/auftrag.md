# Auftrag NumberGuessBean

1. Installieren Sie die Entwicklungsumgebung gemäss [Readme](README.md)
1. Lassen Sie die Applikation auf dem Tomcat-Server laufen 
    * direkt von Eclipse aus
    * über die Kommandozeile
1. Betrachten Sie den Code (es hat zwei relevante Dateien: eine Java-Datei im src-Verzeichnis und ein index.jsp in WebContent) und versuchen Sie zu ermitteln:
    1. Wo ist die "Business-Logik", d.h. die eigentliche Funktionalität?
    1. Ist das vom Tomcat-Server generierte Resultat HTML oder Text? 
    1. In der Methode setGuess im NumberGuessBean wird der Text "higher" verwendet, der dann auch auf der Webseite angezeigt wird, wenn die geratene Zahl zu klein ist. Wo wird dieser Text in der JSP-Datei index.jsp abgerufen?
    1. Welche Funktion im Java-Code erhält die Eingabe vom Browser?
    1. Mit dem setProperty im JSP wird die Eingabe an den Java-Code übermittelt. Wenn Sie statt "*" ein "Guess" eintragen, funktioniert es dann noch immer? Was ist mit "Geraten"?
    
1. Lassen Sie direkt in Eclipse die JUnit-Tests laufen. Führen Sie dazu "Run As" auf der Java-Datei TestNumberGuessBean.java aus.

1. Was passiert wenn Sie mit zwei unterschiedlichen Browsern das Spiel gleichzeitig spielen? Haben beide Browser dasselbe Spiel aktiv oder sind es zwei getrennt Spiele?

# Optionale Aufgaben 

1. Wie können Sie eine CSS-Datei hinzufügen?
1. Können Sie einen weiteren JUnit-Tests zum Test der Funktionalität schreiben?
