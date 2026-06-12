package JavaCore.inheritance;

public class Parent {
    int count;

	public Parent() {
		System.out.println("\n parent constructor without parameters, count " + count);
	}

	public Parent(Number n) {
		System.out.println("\n parent constructor without parameters, count " + n);
	}

	// Constructor of super class
    Parent(int count) {
        this.count = count;
        System.out.println("Constructor of Base class: " + count);
    }

}
