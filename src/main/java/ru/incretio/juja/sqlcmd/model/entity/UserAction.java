package ru.incretio.juja.sqlcmd.model.entity;

import javax.persistence.*;

@Entity
public class UserAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String dbName;
    private String action;

    public UserAction() {
        // do nothing
    }

    public UserAction(String userName, String dbName, String action) {
        this.userName = userName;
        this.dbName = dbName;
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
