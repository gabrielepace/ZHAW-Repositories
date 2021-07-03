public class TrafficLightOperation {
	public static void main(String[] args) {
		TrafficLight[] trafficLights = new TrafficLight[7];
		Car[] cars = new Car[20];
		for (int i = 0; i < trafficLights.length; i++)
			trafficLights[i] = new TrafficLight();
		for (int i = 0; i < cars.length; i++) {
			cars[i] = new Car("Car " + i, trafficLights);
		}

		// Simulation
		while (true) {
			for (int i = 0; i < trafficLights.length; i = i + 2) {

				// Display state of simulation
				System.out.println("=====================================================");
				for (int j = 0; j < trafficLights.length; j++) {
					String prefix;
					if (j == i || j == i + 1)
						prefix = "->";
					else
						prefix = "  ";
					System.out.print(prefix + " at Light " + j + ":");
					for (int k = 0; k < cars.length; k++) {
						if (cars[k].position() == j)
							System.out.print(" " + k);
					}
					System.out.println();
				}
				System.out.println("=====================================================");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException logOrIgnore) {
					;
				}
				trafficLights[i].switchToGreen();
				if (i + 1 < trafficLights.length) {
					trafficLights[i + 1].switchToGreen();
				}
				// green period
				try {
					Thread.sleep((int) (Math.random() * 500));
				} catch (InterruptedException logOrIgnore) {
					;
				}
				trafficLights[i].switchToRed();
				if (i + 1 < trafficLights.length)
					trafficLights[i + 1].switchToRed();
				// red period
				try {
					Thread.sleep(1000);
				} catch (InterruptedException logOrIgnore) {
					;
				}
			}
		}
	}
}
