package com.amadeusz.library.application;

import java.util.UUID;

public interface AccountsRepository {

    void addUserAccount(Account account);

    Account searchUserById(UUID id);

}
