package ru.mccarl.account.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mccarl.account.api.entity.Account;

/**
 * Created by vrudometkin on 27/01/2018.
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findOne(String _id);

}
