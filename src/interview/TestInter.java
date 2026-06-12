package interview;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestInter {

	public static void main(String[] args) {
		var organizations1 = List.of(
			new Organization("country1.first", 2000),
			new Organization("country1.second", 10000),
			new Organization("country1.third", 10000),
			new Organization("country1.fourth", 1000)

		);
		Country country1 = new Country(
			"Ukraine",
			organizations1
		);

		var organizations2 = List.of(
			new Organization("country2.first", 4000),
			new Organization("country2.second", 5000),
			new Organization("country2.third", 4000),
			new Organization("country2.fourth", 5000)

		);

		Country country2 = new Country(
			"USA",organizations2
		);

		List<Country> countries = List.of(
			country1,
			country2
		);

		Map<String, Double> collect = countries.stream()
			.collect(Collectors.groupingBy(
				Country::getName,
				Collectors.averagingDouble(
					c -> c.getOrganizations()
						.stream()
						.mapToInt(Organization::getIncome)
						.average().orElse(0))
			));

		collect.forEach((k,v) -> System.out.println(k + " " + v));

		
		double averageIncomeAllOrg = countries.stream()
			.map(Country::getOrganizations)
			.flatMap(Collection::stream)
			.mapToInt(Organization::getIncome)
			.average()
			.orElse(0.0);

		System.out.println(averageIncomeAllOrg);
	}

}


class Country{

	String name;
	List<Organization> organizations;

	public Country(
		String name,
		List<Organization> organizations
	) {
		this.name = name;
		this.organizations = organizations;
	}

	@Override
	public String toString() {
		return "Country{" +
			"name='" + name + '\'' +
			", organizations=" + organizations +
			'}';
	}

	public String getName() {
		return name;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}
}


class Organization{
	String name;

	int income;

	public Organization(String name, int income) {
		this.income = income;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Organization{" +
			"name='" + name + '\'' +
			'}';
	}

	public String getName() {
		return name;
	}

	public int getIncome() {
		return income;
	}
}
