package interview.aggregate_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Developer> developers = new ArrayList<>();
        Developer developer1 = new Developer(1, "Petro");
        Developer developer2 = new Developer(2, "Mik");
        Developer developer3 = new Developer(3, "Jonson");
        Developer developer4 = new Developer(4, "Simons");

        developers.add(developer1);
        developers.add(developer2);
        developers.add(developer3);
        developers.add(developer4);

        List<Task> tasks = new ArrayList<>();
        Task task2 = new Task(2, "Hello2");
        Task task3 = new Task(3, "Hello3");
        Task task4 = new Task(4, "Hello4");
        Task task5 = new Task(5, "Hello5");

        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);

        List<Assignment> assignments = new ArrayList<>();
        Assignment assignment1 = new Assignment(1,1);
        Assignment assignment2 = new Assignment(2,1);
        Assignment assignment3 = new Assignment(3,2);


        assignments.add(assignment1);
        assignments.add(assignment2);
        assignments.add(assignment3);

        Map<String, List<String>> report = report(tasks, developers, assignments);
        System.out.println(report);
    }


    public static Map<String, List<String>> report(
            List<Task> tasks,
            List<Developer> developers,
            List<Assignment> assignments
    ) {

        Map<Integer, String> mapDevelopers = developers.stream()
                .collect(Collectors.toMap(Developer::getId, Developer::getName));

        Map<Integer, String> mapTasks = tasks.stream()
                .collect(Collectors.toMap(Task::getId, Task::getTitle));


        Map<String, List<String>> report = new HashMap<>();
        for (Developer developer : developers) {
            report.put(developer.name, new ArrayList<>());
        }

        for (Assignment assignment : assignments) {
            int taskId = assignment.getTaskId();
            int developerId = assignment.getDeveloperId();

            String developerName = mapDevelopers.get(developerId);
            String taskName = mapTasks.get(taskId);

            if (developerName != null && taskName != null) {
                report.get(developerName).add(taskName);
            }
        }

        return report;
    }


    public static class Task {
        int id;
        String title;

        public Task(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Developer {
        int id;
        String name;

        public Developer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Assignment {
        int taskId;
        int developerId;

        public Assignment(int taskId, int developerId) {
            this.taskId = taskId;
            this.developerId = developerId;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getDeveloperId() {
            return developerId;
        }

        public void setDeveloperId(int developerId) {
            this.developerId = developerId;
        }
    }
}