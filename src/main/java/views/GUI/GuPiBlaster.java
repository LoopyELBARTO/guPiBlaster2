import ADS.Voltage.ADSReadVoltage;
import HelperFunctions.ReadAndWrite;
import HelperFunctions.ReadAndWriteText;
import HelperFunctions.ReturnADSReadings;
import com.pi4j.io.i2c.I2CFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GuPiBlaster extends JFrame {
    private JPanel mainPanel;
    private JPanel readingsPanel;
    private JPanel readingsTextBoxPanel;
    private JPanel readingLabelUnits;
    private JPanel servoBlasterPanel;

    private JButton startSensors;

    //TextBox
    public JTextField currentBox, forceBox, temperatureBox, voltageBox;
    private JLabel currentLabel, forceLabel, temperatureLabel, voltageLabel;

    ReadAndWriteText readADS = new ReadAndWriteText();

    public GuPiBlaster(String windowTitle) throws IOException, I2CFactory.UnsupportedBusNumberException {
        setTitle(windowTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        add(mainPanel);
        pack();
        setVisible(true);
    }

    public void buildPanel() {
        mainPanel = new JPanel(new GridLayout(1,2));

        readingsPanel = new JPanel(new GridLayout(1,1));
        readingsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        readingsTextBoxPanel = new JPanel(new GridLayout(4,1));
        readingLabelUnits = new JPanel(new GridLayout(4,1));

        //Button not working
        servoBlasterPanel = new JPanel();
        startSensors = new JButton("Start Sensor");

        currentLabel = new JLabel("Amps");
        currentBox = new JTextField();
        currentBox.setEditable(false);

        forceLabel = new JLabel("Newtons");
        forceBox = new JTextField();
        forceBox.setEditable(false);

        temperatureLabel = new JLabel("Celsius");
        temperatureBox = new JTextField();
        temperatureBox.setEditable(false);

        voltageLabel = new JLabel("Volts");
        voltageBox = new JTextField();
        voltageBox.setEditable(false);

        readingsTextBoxPanel.add(currentBox);
        readingsTextBoxPanel.add(voltageBox);
        readingsTextBoxPanel.add(forceBox);
        readingsTextBoxPanel.add(temperatureBox);

        readingLabelUnits.add(currentLabel);
        readingLabelUnits.add(voltageLabel);
        readingLabelUnits.add(forceLabel);
        readingLabelUnits.add(temperatureLabel);

        readingsPanel.add(readingsTextBoxPanel);
        readingsPanel.add(readingLabelUnits);

        servoBlasterPanel.add(startSensors);

        mainPanel.add(readingsPanel);
        mainPanel.add(servoBlasterPanel);

    }
}
