package com.amadeusz.library.application.service;

import com.amadeusz.library.application.exceptions.NoInRepositoryException;
import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality.CreditCard;
import com.amadeusz.library.infrastructure.repository.UserAccountsJpaRepository;
import com.amadeusz.library.infrastructure.repository.entities.LibraryMemberEntity;
import com.amadeusz.library.infrastructure.repository.entities.mappers.DefaultLibraryMemberMapper;
import com.amadeusz.library.infrastructure.repository.entities.mappers.LibraryMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("accountsService")
public class LibraryMembersServiceImpl implements LibraryMembersService {

    @Autowired
    private UserAccountsJpaRepository libraryMembersRepository;

    private final LibraryMemberMapper mapper = new DefaultLibraryMemberMapper();


    @Override
    public LibraryMember add(LibraryMember libraryMember) {
        LibraryMemberEntity libraryMemberEntity =
                libraryMembersRepository.saveAndFlush(mapper.map(libraryMember));
        return mapper.map(libraryMemberEntity);
    }

    @Override
    public LibraryMember getById(UUID id) {
        Optional<LibraryMemberEntity> libraryMemberEntity =
                                libraryMembersRepository.findById(id);
        if (libraryMemberEntity.isEmpty()) {
            throw new NoInRepositoryException("User not in repository");
        }
        return mapper.map(libraryMemberEntity.get());
    }

    @Override
    public void removeById(UUID id) {
        libraryMembersRepository.deleteById(id);
    }

    @Override
    public LibraryMember update(LibraryMember libraryMember) {
        getById(libraryMember.getId());
        LibraryMemberEntity libraryMemberEntity
                = libraryMembersRepository.saveAndFlush(mapper.map(libraryMember));
        return mapper.map(libraryMemberEntity);
    }

    @Override
    public CreditCard addCreditCardToUser(UUID userId, CreditCard creditCard) {
        LibraryMember byId = getById(userId);
        byId.addCreditCard(creditCard);
        LibraryMember update = update(byId);
        return update.getCard();
    }

}
