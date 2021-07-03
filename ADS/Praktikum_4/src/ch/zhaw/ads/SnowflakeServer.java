package ch.zhaw.ads;

import ch.zhaw.ads.CommandExecutor;

/**
 * ADS FS2019 
 * 
 * Praktikum 4 Aufgabe 2 – Koch'sche Schneeflocke
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class SnowflakeServer implements CommandExecutor {
	Turtle turtle;

	@Override
	public String execute(String stufe) throws Exception {
		turtle = new Turtle();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			schneeflocke(Integer.valueOf(stufe), 0.7);
			turtle.turn(-120);
			buffer.append(turtle.getTrace());
		}
		return buffer.toString();
	}

	void schneeflocke(int stufe, double dist) {
		if (stufe == 0) {
			turtle.move(dist);
		} else {
			stufe--;
			dist = dist / 3;
			schneeflocke(stufe, dist);
			turtle.turn(60);
			schneeflocke(stufe, dist);
			turtle.turn(-120);
			schneeflocke(stufe, dist);
			turtle.turn(60);
			schneeflocke(stufe, dist);
		}
	}
}
