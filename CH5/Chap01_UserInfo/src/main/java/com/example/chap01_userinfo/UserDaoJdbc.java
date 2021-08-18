package com.example.chap01_userinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
조회 기능이 있는 UserDao 클래스
 */

public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            return user;
        }
    };

    //중복 키 예외의 전환
    public void add(final User user) throws DuplicateUserIdException   {
        try {
            //JdbcTemplate 이용해서 User 를 add
            this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values(?,?,?,?,?,?)",
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    user.getLevel().intValue(),
                    user.getLogin(),
                    user.getRecommend());

        } catch (DuplicateUserIdException e) {
            //로그를 남기는 등의 작업
            throw new DuplicateUserIdException(e);
            //예외를 전환할 때는 원인이 되는 예외를 중첩하는 것이 좋음.
        }

    }


    //사용자 데이터 가져오기
    public User get(String id)  {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                this.userRowMapper,
                id);
    }

    public void update(User user) {
        this.jdbcTemplate.update(
                "update users set name = ?, password = ?, level = ?, login = ?," + "recommend = ? where id = ?", user.getName(),
                user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());

    }


    public void deleteAll()  {
        this.jdbcTemplate.update("delete from users");
    }


    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users ORDER BY id", this.userRowMapper);
    }

}
