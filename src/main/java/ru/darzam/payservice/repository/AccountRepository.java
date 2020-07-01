package ru.darzam.payservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.darzam.payservice.entity.Account;

/**
 * @author zamaliev
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {

  Account getById(UUID id);
}
