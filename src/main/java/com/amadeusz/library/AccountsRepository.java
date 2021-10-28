package com.amadeusz.library;

import java.util.UUID;

public interface AccountsRepository {

    void addUserAccount(Account account);

    Account searchUserById(UUID id);

}