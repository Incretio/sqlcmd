package ru.incretio.juja.sqlcmd.model.entity;

import javax.persistence.*;

@Entity
public class UserAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String action;
    @Column(name = "databaseConnection_id")
    @OneToMany(fetch = FetchType.LAZY)
    private DatabaseConnection databaseConnection;

    public UserAction() {
        // do nothing
    }

    public UserAction(Integer id, String action, DatabaseConnection databaseConnection) {
        this.id = id;
        this.action = action;
        this.databaseConnection = databaseConnection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}

