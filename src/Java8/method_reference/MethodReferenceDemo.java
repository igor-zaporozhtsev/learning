package Java8.method_reference;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferenceDemo {
	/**
	 * See {@link Demo#main}
	 * */
	public static void main(String[] args) {

		//1. Reference to a static method
		// Class::staticMethodName
		// Person::compareByAge
		//MethodReferencesExamples::appendStrings
		IntUnaryOperator abs = Math::abs;

		//2. Reference to an instance method of a particular object
		// Object::instanceMethodName/Instance::instanceMethodName
		// myComparisonProvider::compareByName
		//myApp::appendStrings2

		ThreadLocalRandom random = ThreadLocalRandom.current();
		IntSupplier intSupplier = random::nextInt;

		//3. Reference to an instance method of an arbitrary(довільний) object of a particular type
		// Type::methodName/Class::methodName
		// String::compareToIgnoreCase
		//String::concat
		String hello = "Hello";
		Supplier<String> stringSupplier = hello::toUpperCase;
		stringSupplier.get();

		UnaryOperator<String> upperFunction = String::toUpperCase;
		upperFunction.apply("Hello"); //pass instance as first lambda parameter

		// 4.Reference to a constructor
		// ClassName::new
		// HashSet::new

	}
}
