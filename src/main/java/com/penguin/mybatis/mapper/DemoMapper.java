package com.penguin.mybatis.mapper;

import com.penguin.mybatis.bean.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper {
    public Payment getPayment(int id);
}