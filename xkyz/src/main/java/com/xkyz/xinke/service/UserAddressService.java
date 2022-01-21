package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserAddressMapper;
import com.xkyz.xinke.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    public UserAddress getUserAddressByAddressId(int addressId) {
        return userAddressMapper.selectByPrimaryKey(addressId);
    }

    public List<UserAddress> getUserAddressListByUserId(int userId) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return userAddressMapper.selectByExample(example);
    }

    public int addUserAddress(UserAddress userAddress) {
        return userAddressMapper.insertSelective(userAddress);
    }

    public int deleteUserAddressByAddressId(int addressId) {
        UserAddress userAddress = UserAddress.builder().addressId(addressId).build();
        return userAddressMapper.delete(userAddress);
    }

    public int updateUserAddressByAddressId(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }


}
