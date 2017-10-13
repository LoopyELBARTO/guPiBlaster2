package ServoBlaster;

import java.io.IOException;


/*
    Good job making an interface. Howeveer, there are two seperate functionalities here. One of them describes opening a program file. The other describes setting values for an GUI. It would be better to brake them up as there may be more interfaces for GUI in the future. 
 
 Naming : As described in MainServoBlaster, this can be made more resusable by chaning it to something like "startProgram" etc.. Be creative but try not to limit it to just this project. Make stuff other people can use, or not have to recreate, if they want to use it for something else. 
 
 */

public interface ServoBlasterInterface<T>  {

    void startServoBlaster() throws IOException;

    void stopServoBlaster() throws IOException;

    //void automaticMode(int T) throws InterruptedException;

    void setSliderValue(int T) throws InterruptedException;

}
