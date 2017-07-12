package ru.incretio.juja.sqlcmd.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserActionsDAOImpl implements UserActionsDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void log(String userName, String dbName, String action) {
        jdbcTemplate.update(
                "INSERT INTO public.user_actions " +
                        "(user_name, db_name, action) " +
                        "VALUES (?, ?, ?)",
                userName, dbName, action);
    }

    @Override
    public List<UserAction> getAllFor(String userName) {
        return jdbcTemplate.query("SELECT * FROM public.user_actions WHERE user_name = ?",
                new Object[]{userName},
                (resultSet, i) -> {
                    UserAction result = new UserAction();
                    result.setId(resultSet.getInt("id"));
                    result.setUserName(resultSet.getString("user_name"));
                    result.setDbName(resultSet.getString("db_name"));
                    result.setAction(resultSet.getString("action"));
                    return result;
                });
    }
}
