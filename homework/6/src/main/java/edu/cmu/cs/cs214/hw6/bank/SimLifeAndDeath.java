package edu.cmu.cs.cs214.hw6.bank;


/**
 * simulate that new people are born and die regularly
 */
public class SimLifeAndDeath implements Runnable {
    private static final double INHERITANCERATE = 0.1;
    private final Economy economy;

    public SimLifeAndDeath(Economy e) {
        this.economy = e;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            simulate();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private void simulate() {
        //new child is born
        Person dad = economy.getRandomCustomer();
        Person mom = economy.getRandomCustomer();
        Person child = new Person();
        economy.addPerson(child);
        economy.getBank().addAccount(new Account(child, 0, 1));
        //get's 10% of their parents funds
        long dadsMoney = economy.getBank().getAccount(dad).getBalance();
        long momsMoney = economy.getBank().getAccount(mom).getBalance();
        economy.getBank().transferFunds(dad, child, Math.round(dadsMoney * INHERITANCERATE));
        economy.getBank().transferFunds(mom, child, Math.round(momsMoney * INHERITANCERATE));
        //get's hired by a random shop
        economy.getRandomShop().addEmployee(child);


        //a random person dies and a random different person inherits
        Person deceased = economy.getRandomCustomer();
        long balance = economy.getBank().getAccount(deceased).getBalance();
        economy.removePerson(deceased);
        if (deceased.getEmployer() != null)
            deceased.getEmployer().removeEmployee(deceased);
        Person benefactor = economy.getRandomCustomer();
        economy.getBank().transferFunds(deceased, benefactor, balance);
        economy.getBank().closeAccount(deceased);
    }
}
