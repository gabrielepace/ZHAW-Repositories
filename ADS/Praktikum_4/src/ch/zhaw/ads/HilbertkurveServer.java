package ch.zhaw.ads;

import ch.zhaw.ads.CommandExecutor;

/**
 * ADS FS2019
 * 
 * Praktikum 4 Aufgabe 3 – Hilbertkurve
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class HilbertkurveServer implements CommandExecutor {

	Turtle turtle;

	@Override
	public String execute(String command) throws Exception {
		int depth = Integer.parseInt(command);
		double dist = 0.8 / (Math.pow(2, depth + 1) - 1);
		turtle = new Turtle(0.1, 0.1);
		hilbert(depth, dist, -90);
		return turtle.getTrace();
	}

	private void hilbert(int depth, double dist, double angle) {
		if (depth >= 0) {
			turtle.turn(-angle); // draw recursive
			hilbert(depth - 1, dist, -angle);
			turtle.move(dist);
			turtle.turn(angle); // draw recursive
			hilbert(depth - 1, dist, angle);
			turtle.move(dist); // draw recursive
			hilbert(depth - 1, dist, angle);
			turtle.turn(angle);
			turtle.move(dist); // draw recursive
			hilbert(depth - 1, dist, -angle);
			turtle.turn(-angle);
		}
	}
}