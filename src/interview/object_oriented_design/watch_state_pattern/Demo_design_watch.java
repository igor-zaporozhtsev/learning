package interview.object_oriented_design.watch_state_pattern;

import java.util.List;

/*
	Design the watches, implement the solution for the watch class describing the wristwatches.
	Use the best OOP design practices implementing the solution.
	Watches have two buttons called A, B, C, and D.
	Watches should support two different modes.
		1) clock,
		2)stopwatch.

	In the clock mode, the buttons' functionality should be:
	a) Set time.
	b) AM/PM/24 hours.
	c) Toggle light.
	d) Switch to the next mode.

	In the stopwatch mode, the buttons' functionality is:
	a) Start/stop stopwatch.
	b) Reset the stopwatch.
	c) Toggle light.
	d) Switch to the next mode.
* */
public class Demo_design_watch {

	public static void main(String[] args) {
		Watch watch = new Watch();
		watch.B();//Press button B to switch to 24-hour mode.
		watch.C();//Press button C to turn on the light.
		watch.D();//Press button D to switch to stopwatch mode.
		watch.A();//Press button A to start the stopwatch.
		watch.C();//Press button C again to turn on the light.
		watch.D();//Press button D to switch back to clock mode (it remains in 24-hour mode).
		watch.D();//Press button D again to switch to stopwatch mode — the stopwatch is still running.
	}
}

abstract class Mode {

	private Mode next;

	void setNext(Mode next) {
		this.next = next;
	}

	Mode nextMode() {
		return next;
	}

	abstract void A();
	abstract void B();
}

class Clock extends Mode {

	@Override
	public void A() {
		System.out.println("Set time");
	}

	@Override
	public void B() {
		System.out.println("24 hours");
	}
}

class Stopwatch extends Mode {

	@Override
	public void A() {
		System.out.println("Start/stop stopwatch");
	}

	@Override
	public void B() {
		System.out.println("Reset the stopwatch.");
	}
}

class Watch {

	Mode mode;

	public Watch() {
		Clock clock = new Clock();
		Stopwatch stopwatch = new Stopwatch();
		clock.setNext(stopwatch);
		stopwatch.setNext(clock);
		mode = clock;
	}

	public void A() {
		mode.A();
	}

	public void B() {
		mode.B();
	}

	public void C() {
		System.out.println("light on");
	}

	public void D() {
		mode = mode.nextMode();
		System.out.println("switch to mode " + mode.getClass().getSimpleName());
	}
}


