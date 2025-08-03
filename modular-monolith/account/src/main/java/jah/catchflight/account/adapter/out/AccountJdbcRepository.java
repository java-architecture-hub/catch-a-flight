package jah.catchflight.account.adapter.out;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface AccountJdbcRepository extends CrudRepository<AccountJdbcEntity, UUID> {
    Optional<AccountJdbcEntity> findByEmail(String email);
}
