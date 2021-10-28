package com.amadeusz.library;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class InMemoryAccountsRepository implements AccountsRepository {

    private static Map<UUID, Account> usersRepository;

    public InMemoryAccountsRepository() {
        usersRepository = new HashMap<>();
    }

    @Override
    public void addUserAccount(Account account) {
        usersRepository.put(account.getId(), account);
    }

    @Override
    public Account searchUserById(UUID id) {
        return usersRepository.get(id);
    }

    @Override
    public String toString() {
        return "InMemoryAccountsRepository{" +
                "usersRepository=\n" + usersRepository +
                '}';
    }

}
