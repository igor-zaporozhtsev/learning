package JavaCore.inheritance;

public class Child2 extends Child1 {
    public int number;

	public Child2(Double n) {
		super(n);
	}

	public Child2(int count, int number) {
        //super(count, number);
        this.number = number;
        System.out.println("\n Constructor of Child2 class: " + number);
    }

}
