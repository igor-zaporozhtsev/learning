package design_patterns.patterns.creation.abstractfactory;

import design_patterns.patterns.creation.abstractfactory.banking.BankingTeamFactory;

public class SuperBankSystemDemo {
    public static void main(String[] args) {
        ProjectTeamFactory projectTeamFactory = new BankingTeamFactory();
        Developer developer = projectTeamFactory.getDeveloper();
        Tester tester = projectTeamFactory.getTester();
        ProjectManager projectManager = projectTeamFactory.getProjectManager();

        System.out.println("creating bank system...");

        developer.writeCode();
        tester.testCode();
        projectManager.manageProject();
    }
}
