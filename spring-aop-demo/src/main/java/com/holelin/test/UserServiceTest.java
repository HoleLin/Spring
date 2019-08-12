package com.holelin.test;

import com.holelin.entity.User;
import com.holelin.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class UserServiceTest {
    @Autowired
    private IUserService mIUserService;
    @Autowired
    private User mUser;

    @Test
    public void testDeleteUser() {
        User holeLin = mIUserService.getUserByName("HoleLin");
        boolean delete = mIUserService.deleteUser(holeLin.getId());
        System.out.println(delete ? "删除成功" : "删除失败");
    }

    @Test
    public void testRegister() {
        mUser.setUsername("HoleLin");
        mUser.setPassword("111");
        mUser.setEmail("Lin@163.com");
        boolean register = mIUserService.register(mUser);
        System.out.println(register ? "注册成功" : "注册失败");
    }

    @Test
    public void testLogin() {
        boolean login = mIUserService.login("a11111111", "111");
        System.out.println(login ? "登陆成功" : "登录失败");
    }

    @Test
    public void testGetUserInfo() {
        User user = mIUserService.getUserByName("a11111111");
        System.out.println(user);
    }

    @Test
    public void testGetAllUserInfo() {
        List<User> allUserInfo = mIUserService.getAllUserInfo();
        System.out.println(allUserInfo);
    }


}
