package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserMapper;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectOne(String openId){
        User user = User.builder().openId(openId).build();
        return userMapper.selectOne(user);
    }
    public int insert(User user){
        return userMapper.insert(user);
    }
//
//    public UserProfile selectUserById(int id) {
//        return userMapper.selectUserById(id);
//    }
//
//    public boolean login(UserProfile user) {
//        String name = user.getUsername();
//        String password = user.getPassword();
//        UserProfile u1 = userMapper.selectUserByName(name);
//        if (u1 == null) {
//            return false;
//        } else {
//            if (u1.getPassword().equals(password)) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//
//    public boolean zhuCe(UserProfile user) {
//        int x = userMapper.insertUser(user);
//        if (x > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
