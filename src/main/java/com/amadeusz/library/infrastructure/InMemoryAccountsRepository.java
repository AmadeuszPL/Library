package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.Account;
import com.amadeusz.library.application.AccountsRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryAccountsRepository implements AccountsRepository {

//    private static Map<UUID, AccountEntity> usersRepository;

/*    public InMemoryAccountsRepository() {
        usersRepository = new HashMap<>();
    }*/

    @Override
    public void addUserAccount(Account account) {
//        usersRepository.put(account.getId(), account);
    }

    @Override
    public Account searchUserById(UUID id) {
//        return usersRepository.get(id);
        return null;
    }

/*    @Override
    public String toString() {
        return "InMemoryAccountsRepository{" +
                "usersRepository=\n" + usersRepository +
                '}';
    }*/

}
