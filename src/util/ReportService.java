package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
 /*
       Return Map where:
       Key is Developer Name;
       Value is List of Tasks Title assigned to Developer
       Note: also handle non-consistent cases:
       Developer::getId not present in Assignment::getDeveloperId - add Developer::getName with Collections::emptyList
       Assignment::getTaskId not present in Task::getId - Skip Assignment
    */


	public static void main(String[] args) {
		//Map<name, List<title>>
		//one-to-many

		Map<String, List<String>> report = report(
			List.of(
				new Task(1,"task1"),
				new Task(2,"task2"),
				new Task(3,"task3")
			),
			List.of(
				new Developer(1, "developer1"),
				new Developer(2, "developer2"),
				new Developer(3, "developer3")
			),
			List.of(
				new Assigment(1, 1),
				new Assigment(2, 2),
				new Assigment(3, 1),
				new Assigment(4, 1)
			)
		);
		System.out.println(report);
	}

	public static Map<String, List<String>> report(
		List<Task> tasks,
		List<Developer> developers,
		List<Assigment> assignments

	){
		HashMap<String, List<String>> result = new HashMap<>();

		assignments.forEach(
			assigment -> {
				int developerId = assigment.developerId;
				int taskId = assigment.taskId;

				Developer developer = developers.stream()
					.filter(dev -> dev.id == developerId)
					.findFirst()
					.orElse(null);

				Task task = tasks.stream()
					.filter(ta -> ta.id == taskId)
					.findFirst()
					.orElse(null);

				if (task == null) {
					return;
				}

				List<String> existsTitles = result.get(developer.name);

				if (existsTitles != null) {
					existsTitles.add(task.tittle);
					result.put(developer.name, existsTitles);
				} else {
					List<String> newTittles = new ArrayList<>();
					newTittles.add(task.tittle);
					result.put(developer.name, newTittles);
				}
			}
		);

		for (Developer dev : developers) {
			if (!result.containsKey(dev.getName())) {
				result.put(dev.getName(), Collections.emptyList());
			}
		}

		return result;
	}

	public static class Task{
		int id;
		String tittle;

		private Task(
			int id,
			String tittle
		) {
			this.id = id;
			this.tittle = tittle;
		}
	}

	public static class Developer{
		int id;
		String name;

		private Developer(
			int id,
			String name
		) {
			this.id = id;
			this.name = name;
		}

		private int getId() {
			return id;
		}

		private String getName() {
			return name;
		}
	}

	public static class Assigment{
		int taskId;
		int developerId;

		private Assigment(
			int taskId,
			int developerId
		) {
			this.taskId = taskId;
			this.developerId = developerId;
		}
	}

}
