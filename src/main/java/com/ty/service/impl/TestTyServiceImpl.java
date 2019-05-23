package com.ty.service.impl;

import com.ty.entity.TestTy;
import com.ty.mapper.TestTyMapper;
import com.ty.service.TestTyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @ClassName TestTyServiceImpl
 * @Author tianye
 * @Date 2019/5/6 16:22
 * @Version 1.0
 */
@Service
public class TestTyServiceImpl implements TestTyService {

    @Autowired
    private TestTyMapper mapper;

    @Override
    public List<TestTy> get() {
        return mapper.selectList();
    }

    @Override
//    @Transactional(rollbackFor = Throwable.class)
    public void modify(TestTy testTy) {
        mapper.insert(testTy);

        System.out.println("=================");

        System.out.println("测试事务！");

        TestTy ty = new TestTy();
        ty.setId(11L);
        ty.setLakersLoc("sdasd");
//        ty.setLakersName(new Date());
//        ty.setQuantity(10L);
//        ty.setSequenceNumber("4876dsad512ds1ad4q6we1qa");
        mapper.insert(ty);

        System.out.println("=======================");

    }

}
