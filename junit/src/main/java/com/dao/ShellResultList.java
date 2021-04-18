package com.dao;

import java.util.List;

/*
* 结果列表
* */
public class ShellResultList {
    private List<ShellResult> resultList;

    public List<ShellResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ShellResult> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "ShellResultList{" +
                "resultList=" + resultList +
                '}';
    }
}
