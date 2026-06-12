package design_patterns.patterns.behavior.state;

public class StateWatchImpl {
    public static void main(String[] args) {


//        In the Clock mode the buttons functionality should be:
//        A - set time
//        B - am/pm -> 24h
//        C - toggle light
//        D - switch to the next mode
//
//        In the Stopwatch mode the buttons functionality is:
//        A - start/stop stopwatch
//        B - reset the stopwatch
//        C - toggle light
//        D - switch to the next mode
//
        //The example of the class usage:
//        const watch = new Watch(); /* in Clock mode by default */
//        watch.B(); /* turn mode to 24h */
//        watch.C(); /* turn on the light */
//        watch.D(); /* switch to Stopwatch mode */
//        watch.A(); /* start the stopwatch */
//        watch.C(); /* turn off the light */
//        watch.D(); /* switch back to Clock mode, still in 24h mode */

        Watch watch = new Watch();
        watch.B();
        watch.C();
        watch.D();
        watch.A();
        watch.C();
        watch.D();
    }
}

interface Mode {
    void buttonA();
    void buttonB();
    void buttonC();
}


class Cloak implements Mode {
    public void buttonA(){
        System.out.println("set time");
    }

    @Override
    public void buttonB() {
        System.out.println("set 24h");
    }

    @Override
    public void buttonC() {
        System.out.println("Clock light is turned on");
    }
}


class StopWatch implements Mode{

    @Override
    public void buttonA() {
        System.out.println("StopWatch is started");
    }

    @Override
    public void buttonB() {
        System.out.println("Reset stop watch");

    }

    @Override
    public void buttonC() {
        System.out.println("Clock light is turned on");
    }

}


//context
class Watch{
    Mode mode;

    Watch(){
        this.mode = new Cloak();
    }


    public void A() {   //start  - topWatch
        mode.buttonA();
    }

    public void B(){   // turn mode to 24h - Cloak
        mode.buttonB();
    }

    public void C() {   //turn on the light - Cloak
        mode.buttonC();
    }

    public void D() {  //switch mode
        switcher();
    }

    public void setClockMode(Mode clockMode){
       this.mode = clockMode;
    }


	/**
	 * {@link interview.object_oriented_design.watch_state_pattern.Demo_design_watch}
	 * */
	//instanceof - smell code
    public void switcher(){
        if (mode instanceof Cloak){
            setClockMode(new StopWatch());
        } else if (mode instanceof StopWatch) {
            setClockMode(new Cloak());
        }
    }
}