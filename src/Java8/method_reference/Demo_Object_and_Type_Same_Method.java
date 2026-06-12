package Java8.method_reference;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Demo_Object_and_Type_Same_Method {
	public static void main(String[] args) {
		//2 and 3 together use same method
		//example 1
		StatusControlService service1 = new StatusControlService();
		Supplier<String> statusSupplier = service1::changeStatus;
		Function<StatusControlService, String> statusFunction = StatusControlService::changeStatus;
		statusFunction.apply(new StatusControlService());

		//example 2
		String hello = "hello";
		Supplier<String> stringSupplier = hello::toUpperCase;
		Function<String, String> stringFunction = String::toUpperCase;

		//example 3
		String hello2 = "hello";
		Function<Integer, String> helloSunstringFunction = hello2::substring;
		String resultingString = helloSunstringFunction.apply(1);
		System.out.println(resultingString);

		BiFunction<String, Integer, String> stringIntegerStringBiFunction = String::substring;
		String peter = stringIntegerStringBiFunction.apply(new String("Peter"), 2);
		System.out.println(peter);
	}
}

class StatusControlService {

	public String changeStatus(){
		return "Status changed".toUpperCase();
	}
}
