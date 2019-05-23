package com.ty.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @ClassName Demo
 * @Author tianye
 * @Date 2019/4/28 17:18
 * @Version 1.0
 */
public class Demo {

    private String ask;

    private String message;

    private List<DemoTest> data;

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DemoTest> getData() {
        return data;
    }

    public void setData(List<DemoTest> data) {
        this.data = data;
    }

    public class DemoTest {
        private String transitWarehouseCode;
        private String transitWarehouseName;

        public String getTransitWarehouseCode() {
            return transitWarehouseCode;
        }

        public void setTransitWarehouseCode(String transitWarehouseCode) {
            this.transitWarehouseCode = transitWarehouseCode;
        }

        public String getTransitWarehouseName() {
            return transitWarehouseName;
        }

        public void setTransitWarehouseName(String transitWarehouseName) {
            this.transitWarehouseName = transitWarehouseName;
        }
    }

    public static void main(String[] args) {

//        List<Integer> list = new ArrayList<>();
//
//        for (int i = 0; i < 599 ; i++) {
//            list.add(i);
//        }
//
//        int size = 599;
        int count = 1;
//        if (size > 100) {
//            count = size % 100 == 0 ? size / 100 : size / 100 + 1;
//        }

        for (int i = 0; i < count; i++) {
//            List<Integer> list1 = list.stream().skip(i * 100).limit(100).collect(Collectors.toList());
            System.out.println(i);
        }


    }
}
