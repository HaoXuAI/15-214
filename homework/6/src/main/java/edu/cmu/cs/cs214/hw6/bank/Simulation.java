package edu.cmu.cs.cs214.hw6.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that drives the simulation and creates the relevant threads.
 */
public class Simulation {

    public static void main(String[] args) throws InterruptedException {
        new Simulation().run();
    }

    private void run() throws InterruptedException {

        Economy e = initializeEconomy();


        e.printReport();

        List<Thread> shoppers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new SimShopping(e));
            shoppers.add(t);
        }
        List<Thread> supportingThreads = new ArrayList<>();
        supportingThreads.add(new Thread(new SimSalary(e)));
        supportingThreads.add(new Thread(new SimLifeAndDeath(e)));
        supportingThreads.add(new Thread(new SimInterest(e)));


        for (Thread t : shoppers)
            t.start();
        for (Thread t : supportingThreads)
            t.start();

        long start = System.currentTimeMillis();


        for (Thread t : shoppers)
            t.join();

        long end = System.currentTimeMillis();

        for (Thread t : supportingThreads)
            t.interrupt();

        System.out.println("\nSimulation done in " + (end - start) + " ms\n");

        e.printReport();

    }

    private final static int SHOPNUMBER = 10000;
    private final static int UNEMPLOYED = 1000;
    private final static int INITIALBANKCAPITAL = 1000;
    private final static int INITIALSHOPCAPITAL = 1000;
    private final static int INITIALPERSONCAPITAL = 1000;
    private final static int INITIALPRIVATEACCOUNTFEE = 10;

    private Economy initializeEconomy() {
        Economy e = new Economy();
        e.getBank().getOwnFunds().setBalance(INITIALBANKCAPITAL);
        for (int i = 0; i < SHOPNUMBER; i++) {
            Shop s = new Shop();
            Person p1 = new Person();
            Person p2 = new Person();
            s.addEmployee(p1);
            s.addEmployee(p2);
            e.addPerson(p1);
            e.addPerson(p2);
            e.addShop(s);
            e.getBank().addAccount(new Account(s, INITIALSHOPCAPITAL, 0));
            e.getBank().addAccount(new Account(p1, INITIALPERSONCAPITAL, INITIALPRIVATEACCOUNTFEE));
            e.getBank().addAccount(new Account(p2, INITIALPERSONCAPITAL, INITIALPRIVATEACCOUNTFEE));
        }
        for (int i = 0; i < UNEMPLOYED; i++) {
            Person p = new Person();
            e.addPerson(p);
            e.getBank().addAccount(new Account(p, INITIALPERSONCAPITAL, INITIALPRIVATEACCOUNTFEE));
        }

        return e;
    }

}
