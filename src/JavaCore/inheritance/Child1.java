package JavaCore.inheritance;

public class Child1 extends Parent {
    public int number;

	public Child1() {
		super();
		System.out.println("\n Child1 constructor without parameters, count " + count);
	}

	public Child1(Double n) {
		super(n);
	}

	Child1(int count, int number) {
       // super(count);
        this.number = number;
        System.out.println("Constructor of Child1 class: " + number);
    }
}
