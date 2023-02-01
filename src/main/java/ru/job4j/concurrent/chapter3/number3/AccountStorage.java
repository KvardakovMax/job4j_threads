package ru.job4j.concurrent.chapter3.number3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.getId(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.getId(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id, accounts.get(id));
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account accountFrom = accounts.get(fromId);
        Account accountTo = accounts.get(toId);
        if (accounts.containsKey(fromId) && accounts.containsKey(toId) && fromId != toId
                && accountFrom.getAmount() >= amount) {
            accountFrom.setAmount(accountFrom.getAmount() - amount);
            accountTo.setAmount(accountTo.getAmount() + amount);
        }
        return false;
    }

}
