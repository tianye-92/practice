package com.ty.service;

import com.ty.entity.TestTy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @ClassName TestTyService
 * @Author tianye
 * @Date 2019/5/6 16:21
 * @Version 1.0
 */
public interface TestTyService {

    List<TestTy> get();

    void modify(TestTy testTy);
}
