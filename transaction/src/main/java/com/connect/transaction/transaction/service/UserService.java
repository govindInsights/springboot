package com.connect.transaction.transaction.service;

import com.connect.transaction.transaction.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public boolean insertUser(List<User> _listUsers){
        if(!_listUsers.isEmpty()){
            for(User _user : _listUsers){
                System.out.println("Inserting the value of USer " + _user);
                jdbcTemplate.update("insert into User (name, department, salary) values (?,?,?)",
                        preparedStatement -> {
                            preparedStatement.setString(1, _user.getName());
                            preparedStatement.setString(2, _user.getDepartment());
                            preparedStatement.setLong(3, _user.getSalary());
                        });
            }
        }
        return true;
    }

    public List<User> getUser(){
        List<User> _listUser = jdbcTemplate.query("select name, department, salary from User", new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {

                return new User(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getLong("salary"));
            }
        });
        return _listUser;
    }

}
