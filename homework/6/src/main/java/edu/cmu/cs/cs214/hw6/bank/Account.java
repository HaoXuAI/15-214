package edu.cmu.cs.cs214.hw6.bank;


/**
 * represents a bank account with an owner, a current balance and
 * a fee.
 */
public class Account {

    private AccountOwner owner;
    private long balance;
    private long fee;

    public Account(AccountOwner s, long balance, long fee) {
        this.owner = s;
        this.balance = balance;
        this.fee = fee;
    }

    public AccountOwner getOwner() {
        return owner;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public synchronized void setBalance(long balance) {
        this.balance = balance;
    }

    public long getFee() {
        return fee;
    }
}
