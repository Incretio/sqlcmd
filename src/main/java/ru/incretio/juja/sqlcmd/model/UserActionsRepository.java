package ru.incretio.juja.sqlcmd.model;

import org.springframework.data.repository.CrudRepository;
import ru.incretio.juja.sqlcmd.model.entity.UserAction;

import java.util.List;

public interface UserActionsRepository extends CrudRepository<UserAction, Long> {
    List<UserAction> findByUserName(String userName);
}
