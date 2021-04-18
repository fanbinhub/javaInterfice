package com.dao;


public class ShellResult {
    private String caseName;
    private boolean result;

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ShellResult{" +
                "caseName='" + caseName + '\'' +
                ", result=" + result +
                '}';
    }
}
