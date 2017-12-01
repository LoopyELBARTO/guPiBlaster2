package ADS.Voltage;

import ADS.ADS1015DifferentialPins;
import ADS.DifferentialGpioProvider;
import HelperFunctions.ReadAndWriteText;
import Interfaces.ADSInterface;
import com.pi4j.gpio.extension.ads.ADS1015GpioProvider;
import com.pi4j.gpio.extension.ads.ADS1015Pin;
import com.pi4j.gpio.extension.ads.ADS1x15GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalog;
import com.pi4j.io.gpio.event.GpioPinAnalogValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerAnalog;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class ADSReadVoltage implements ADSInterface {
    protected double voltage;
    private double value;
    private double percent;

    ReadAndWriteText writeTotext = new ReadAndWriteText();

    public GpioPinListener listener;

    protected final DecimalFormat DF = new DecimalFormat("#.##");

    private final GpioController GPIO = GpioFactory.getInstance();

    private final DifferentialGpioProvider DIFFERENTIAL_PROVIDER = new DifferentialGpioProvider(I2CBus.BUS_1, ADS1015GpioProvider.ADS1015_ADDRESS_0x48);
    public GpioPinAnalog[] diffAnalogInputs = {
            GPIO.provisionAnalogInputPin(DIFFERENTIAL_PROVIDER, ADS1015DifferentialPins.INPUT_A0_A1, "A0-A1"),
            GPIO.provisionAnalogInputPin(DIFFERENTIAL_PROVIDER, ADS1015DifferentialPins.INPUT_A2_A3, "A2-A3"),
    };

    public ADSReadVoltage() throws IOException, I2CFactory.UnsupportedBusNumberException {
        setupGpio();
        analogPinValueListener();
    }

    public void setupGpio(){

        DIFFERENTIAL_PROVIDER.setProgrammableGainAmplifier(ADS1x15GpioProvider.ProgrammableGainAmplifierValue.PGA_4_096V, ADS1015Pin.ALL);

        DIFFERENTIAL_PROVIDER.setEventThreshold(500, ADS1015Pin.ALL);

        DIFFERENTIAL_PROVIDER.setMonitorInterval(100);
    }

    public void analogPinValueListener() {
         listener = new GpioPinListenerAnalog() {
            @Override
            public void handleGpioPinAnalogValueChangeEvent(GpioPinAnalogValueChangeEvent event) {
                setListerValue(event);
                writeTotext.setFileName("Voltage");
                try {
                    writeTotext.write2(voltage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public double setListerValue(GpioPinAnalogValueChangeEvent gpioEvent){
        value = gpioEvent.getValue();
        percent = ((value * 100) / ADS1015GpioProvider.ADS1015_RANGE_MAX_VALUE);
        return voltage = DIFFERENTIAL_PROVIDER.getProgrammableGainAmplifier(gpioEvent.getPin()).getVoltage()* (percent/100);

    }

}
