package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "userAddressMapper")
public interface UserAddressMapper extends Mapper<UserAddress> {
    List<UserAddress> queryUserAddressList(
            @Param("userId") Integer userId);
//    public UserAddress getUserAddressByAddressId(int addressId);
//
//    public List<UserAddress> getUserAddressListByUserId(int userId);
//
//    public int addUserAddress(UserAddress userAddress);
//
//    public int deleteUserAddressByAddressId(int addressId);
//
//    public int updateUserAddressByAddressId(UserAddress userAddress);

}

//public interface OrderMapper extends Mapper<Order> {
//
//    List<Order> queryOrderList(
//            @Param("userId") Long userId,
//            @Param("status") Integer status);
//}
