package edu.cmu.cs.cs214.hw6.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.jcip.annotations.GuardedBy;

/**
 * Main class of the simulation. Contains the bank, and a list of
 * people and shops.
 */
public class Economy {

    private final Bank bank = new Bank();
    private final List<Person> people = new ArrayList<>();
    @GuardedBy("this")
    private final List<Shop> shops = new ArrayList<>();
    private final List<Shop> closedShops = new ArrayList<>();
    private final Random random = new Random();

    public Person getRandomCustomer() {
        return people.get(random.nextInt(people.size()));
    }

    public Shop getRandomShop() {
        return shops.get(random.nextInt(shops.size()));
    }

    public synchronized Bank getBank() {
        return bank;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public synchronized void addShop(Shop s) {
        shops.add(s);
    }

    public void closeShop(Shop s) {
        s.close();
        shops.remove(s);
        closedShops.add(s);
    }

    public synchronized void addPerson(Person p) {
        people.add(p);
    }

    public void removePerson(Person p) {
        people.remove(p);
    }


    public void printReport() {

        long privateFunds = 0;
        long shopFunds = 0;
        for (Shop s : shops)
            shopFunds += bank.getAccount(s).getBalance();
        for (Shop s : closedShops)
            shopFunds += bank.getAccount(s).getBalance();
        for (Person p : people)
            privateFunds += bank.getAccount(p).getBalance();
        long totalFunds = bank.getOwnFunds().getBalance() + privateFunds + shopFunds;

        System.out.println("Money in the economy: " + totalFunds);
        System.out.println("Money in private households: " + privateFunds);
        System.out.println("Money in corporations: " + shopFunds);
        System.out.println("Bank capital: " + bank.getOwnFunds().getBalance());
        System.out.println("Shops: " + shops.size());
    }


}
