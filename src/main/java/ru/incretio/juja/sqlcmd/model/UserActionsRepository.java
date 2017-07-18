package ru.incretio.juja.sqlcmd.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserActionsRepository extends JpaRepository<UserAction, Long> {
    List<UserAction> findByUserName(String userName);
}
