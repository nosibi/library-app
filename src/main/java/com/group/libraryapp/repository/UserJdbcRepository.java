package com.group.libraryapp.repository;

import com.group.libraryapp.dto.response.UserResponse;
import com.group.libraryapp.dto.user.UserUpdateRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name, int age){
        String sql = "insert into user(name,age) value(?,?)";
        jdbcTemplate.update(sql,name,age);
    }

    public List<UserResponse> getUserResponse(){
        List<UserResponse> responses = new ArrayList<>();
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }

    public boolean isUserNotExist(UserUpdateRequest request){
        String readsql = "select*from user where id = ?";
        return jdbcTemplate.query(readsql,(rs,rowNum)->0,request.getId()).isEmpty();
    }
    public void updateUserName(String name, long id){
        String updatesql = "update user set name = ? where id = ?";
        jdbcTemplate.update(updatesql,name,id);
    }
    public boolean isUserNotExist(String name){
        String readsql = "select * from user where name = ?";
        return jdbcTemplate.query(readsql,(rs,rowNum)->0,name).isEmpty();
    }
    public void deleteUser(String name){
        String deletesql = "delete from user where name = ?";
        jdbcTemplate.update(deletesql,name);
    }
}
