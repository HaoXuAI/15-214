package edu.cmu.cs.cs214.hw6.bank;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;

/**
 * A bank has a number of accounts and also has an own account
 * it owns itself.
 */
public class Bank implements AccountOwner {
    private final Map<AccountOwner, Account> accounts = new HashMap<>();
    @GuardedBy("this")
    private final Account bankAccount = new Account(this, 0, 0);

    public synchronized boolean transferFunds(AccountOwner from, AccountOwner to, long funds) {
        Account f = getAccount(from);
        Account t = getAccount(to);
        if (f != null && t != null/* && f.getBalance() >= funds && (Long.MAX_VALUE - funds > t.getBalance())*/) {
            f.setBalance(f.getBalance() - funds);
            t.setBalance(t.getBalance() + funds);
            return true;
        }
        return false;
    }

    public synchronized Account getAccount(AccountOwner owner) {
        if (bankAccount.getOwner() == owner)
            return bankAccount;
        return accounts.get(owner);
    }

    public synchronized Account getOwnFunds() {
        return bankAccount;
    }

    public synchronized void addAccount(Account account) {
        accounts.put(account.getOwner(), account);
    }

    public synchronized Collection<Account> getAccounts() {
        return accounts.values();
    }

    public void closeAccount(AccountOwner owner) {
        accounts.remove(owner);
    }
}
