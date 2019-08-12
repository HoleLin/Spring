package com.holelin.dao;

import com.holelin.entity.User;
import sun.rmi.runtime.Log;

import java.util.List;

public interface IUserDao {


    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();


    /**
     * 根据姓名查询用户信息
     * @return
     */
    public User findByName(String username);



    /**
     * 根据Id查询用户信息
     * @param id
     * @return
     */
    public User findById(Long id);



    /**
     * 根据Id删除用户
     * @param id
     * @return
     */
    public int deleteById(Long id);


    /**
     * 新增用户
     * @param user
     * @return
     */
    public int addUser(User user);



    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int updateUser(User user);
}
