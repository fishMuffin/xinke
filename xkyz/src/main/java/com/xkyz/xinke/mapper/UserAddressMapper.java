package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAddressMapper {
    public UserAddress getUserAddressByAddressId(int addressId);

    public List<UserAddress> getUserAddressListByUserId(int userId);

    public int addUserAddress(UserAddress userAddress);

    public int deleteUserAddressByAddressId(int addressId);

    public int updateUserAddressByAddressId(int addressId);

}