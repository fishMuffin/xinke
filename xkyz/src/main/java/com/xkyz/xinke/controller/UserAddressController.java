package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.UserAddress;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.xkyz.xinke.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户地址操作API")//作用在模块API类上，对API模块进行说明
@RestController()
@RequestMapping("/sys/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation("添加用户地址接口")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addUserAddress(@ApiParam(name = "用户地址实体类")UserAddress userAddress) {
        int i = userAddressService.addUserAddress(userAddress);
        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    @ApiOperation("根据用户OpenId查询用户地址列表")
    @GetMapping(value = "/list")
    //作用在参数上，对参数进行说明
    public ResponseEntity<List<UserAddress>> getUserAddressListByOpenId(
            @ApiParam(name = "用户token", required = true) String token) {
        List<UserAddress> list = userAddressService.getUserAddressListByUserId(token);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("更新用户地址")
    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> updateUserAddressByAddressId(
            @ApiParam("地址信息") UserAddress userAddress) {
        int i = userAddressService.updateUserAddressByAddressId(userAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除用户地址")
    @GetMapping(value = "/delete")
    public ResponseEntity<Boolean> deleteUserAddressByAddressId(@ApiParam("地址ID") Integer addressId) {
        int i = userAddressService.deleteUserAddressByAddressId(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
