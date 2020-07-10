import java.util.*;

public class CallCenter {
    private class Employee {
        String name;
        int maxDifficulty;
        boolean isFree;
        public Employee(String name, int maxDifficulty) {
            this.name = name;
            this.maxDifficulty = maxDifficulty;
            isFree = true;
        }
        public boolean canHandle(Call c) {
            return this.maxDifficulty >= c.difficulty;
        }
        public void takeCall(Call c) {
            System.out.println(name + " has taken the call.");
        }
        public void endCall(Call c) {
            System.out.println(name + " has ended the call.");
        }
    }
    private class Respondent extends Employee {
        public Respondent(String name) {
            super(name, 4);
        }
    }
    private class Manager extends Employee {
        public Manager(String name) {
            super(name, 7);
        }
    }
    private class Director extends Employee {
        public Director(String name) {
            super(name, 10);
        }
    }
    private class Call {
        int difficulty;
        public Call(int difficulty) {
            this.difficulty = difficulty;
        }
    }

    List<Respondent> respondents;
    List<Manager> managers;
    List<Director> directors;
    Queue<Call> holdQueue;

    public CallCenter() {
        respondents = new ArrayList<>();
        managers = new ArrayList<>();
        directors = new ArrayList<>();
        holdQueue = new LinkedList<>();
    }

    public void dispatchCall(Call c) {
        Respondent r = nextAvailableRespondent();
        if (r == null) holdQueue.add(c);
        else if (r.canHandle(c)) r.takeCall(c);
        else {
            Manager m = nextAvailableManager();
            if (m != null && m.canHandle(c)) m.takeCall(c);
            else {
                Director d = nextAvailableDirector();
                if (d != null) d.takeCall(c);
                else holdQueue.add(c);
            }
        }
    }

    private Respondent nextAvailableRespondent() {
        for (Respondent r : respondents) if (r.isFree) return r;
        return null;
    }
    private Manager nextAvailableManager() {
        for (Manager m : managers) if (m.isFree) return m;
        return null;
    }
    private Director nextAvailableDirector() {
        for (Director d : directors) if (d.isFree) return d;
        return null;
    }
}
