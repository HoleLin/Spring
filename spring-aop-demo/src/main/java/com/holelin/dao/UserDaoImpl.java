package com.holelin.dao;

import com.holelin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class UserDaoImpl implements IUserDao {
    @Autowired
    private JdbcTemplate mJdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "select id,username,password,email,created_by,created_time,updated_by,updated_time" +
                " from user";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        return mJdbcTemplate.query(sql, mapper);
    }

    @Override
    public User findByName(String username) {
        String sql = "select id,username,password,email,created_by,created_time,updated_by,updated_time" +
                " from user where username=?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);

        try {
            return mJdbcTemplate.queryForObject(sql, mapper, username);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public User findById(Long id) {
        String sql = "select id,username,password,email,created_by,created_time,updated_by,updated_time" +
                " from user where id=?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);

        return mJdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "delete from user where id=?";
        return mJdbcTemplate.update(sql,id);
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into user(username,password,email) values(?,?,?)";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        return mJdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getEmail()});
    }

    @Override
    public int updateUser(User user) {
        String sql = "update user set passsword ?,email=? where id=?";
        return mJdbcTemplate.update(sql, user.getPassword(), user.getEmail(), user.getId());
    }
}
