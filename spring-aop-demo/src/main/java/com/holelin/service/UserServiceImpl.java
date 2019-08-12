package com.holelin.service;

import com.holelin.dao.IUserDao;
import com.holelin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao mIUserDao;

    @Override
    public boolean login(String username, String password) {
        // 验证非空
        if (validNoNull(username, password)) {
            // 根据用户名查询用户
            User user = mIUserDao.findByName(username);
            if (!StringUtils.isEmpty(user)) {
                // 进一步验证密码
                String pwd = user.getPassword();
                if (pwd.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean register(User user) {
        // 校验参数

        if (!StringUtils.isEmpty(user)) {
            User userByQuery = mIUserDao.findByName(user.getUsername());
            if (StringUtils.isEmpty(userByQuery)) {
                int result = mIUserDao.addUser(user);
                if (result > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (!StringUtils.isEmpty(id)) {
            int result = mIUserDao.deleteById(id);
            if (result > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (!StringUtils.isEmpty(user)) {
            int result = mIUserDao.updateUser(user);
            if (result > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUserInfo() {
        return mIUserDao.findAll();
    }

    @Override
    public User getUserByName(String username) {
        return mIUserDao.findByName(username);
    }

    /**
     * 验证输入参数是否为空
     *
     * @param args
     * @return 为空 返回false;非空 返回true
     */
    private boolean validNoNull(Object... args) {
        for (Object arg : args) {
            if (StringUtils.isEmpty(arg)) {
                return false;
            }
        }
        return true;
    }
}
