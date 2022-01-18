package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserAddressMapper;
import com.xkyz.xinke.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    public UserAddress getUserAddressByAddressId(int addressId) {
        return userAddressMapper.getUserAddressByAddressId(addressId);
    }

    public List<UserAddress> getUserAddressListByUserId(int userId) {
        return userAddressMapper.getUserAddressListByUserId(userId);
    }

    public int addUserAddress(UserAddress userAddress) {
        return userAddressMapper.addUserAddress(userAddress);
    }

    public int deleteUserAddressByAddressId(int addressId) {
        return userAddressMapper.deleteUserAddressByAddressId(addressId);
    }

    public int updateUserAddressByAddressId(int addressId) {
        return userAddressMapper.updateUserAddressByAddressId(addressId);
    }


}
