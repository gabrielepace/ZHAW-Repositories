import java.awt.*;
import javax.swing.*;

public class CarWindow extends JFrame {
    
    public CarWindow(CarWorld world) {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add("Center",world);
        JButton addLeft = new JButton("Add Left");
        JButton addRight = new JButton("Add Right");
        addLeft.addActionListener((e) -> world.addCar(Car.REDCAR));
        addRight.addActionListener((e) -> world.addCar(Car.BLUECAR));

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(addLeft);
        p1.add(addRight);
        c.add("South",p1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
