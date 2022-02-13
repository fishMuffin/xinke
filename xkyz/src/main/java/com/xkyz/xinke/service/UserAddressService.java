package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserAddressMapper;
import com.xkyz.xinke.mapper.UserProfileMapper;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserProfile;
import com.xkyz.xinke.pojo.UserAddressWithUserProfileView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private UserProfileMapper userProfileMapper;

    public UserAddress getUserAddressByAddressId(int addressId) {
        return userAddressMapper.selectByPrimaryKey(addressId);
    }

    public List<UserAddressWithUserProfileView>  getUserAddressListByUserId(String token) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skey", token);
        List<UserAddress> userAddressList = userAddressMapper.selectByExample(example);
//        UserProfile userProfile = userProfileMapper.selectOne(UserProfile.builder().skey(token).build());
        //data convert
        List<UserAddressWithUserProfileView> userAddressWithUserProfileViews = dataConvert( userAddressList);
        return userAddressWithUserProfileViews;
    }

    private List<UserAddressWithUserProfileView> dataConvert(List<UserAddress> userAddressList) {
        List<UserAddressWithUserProfileView> resList = new ArrayList<>();
        userAddressList.stream().forEach(s -> {
            resList.add(UserAddressWithUserProfileView.builder().wx_name(s.getUserName()).phone_number(s.getPhoneNumber()).province(s.getProvince()).city(s.getCity()).district(s.getDistrict()).addressDetail(s.getAddressDetail()).addressId(s.getAddressId()).build());
        });
        return resList;
    }

    public int addUserAddress(UserAddress userAddress) {

        return userAddressMapper.insertSelective(userAddress);
    }
//    private boolean checkUserAddress(UserAddress userAddress){
//        if(StringUtils.isEmpty(userAddress.))
//    }

    public int deleteUserAddressByAddressId(int addressId) {
        UserAddress userAddress = UserAddress.builder().addressId(addressId).build();
        return userAddressMapper.delete(userAddress);
    }

    public int updateUserAddressByAddressId(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }


}
