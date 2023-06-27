package com.sudip.ecommerce_poc.vo.response;

public class ExtensibleStringType {

    private final String value;

    public ExtensibleStringType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object t) {
        if(t instanceof String) {
            return value.equals(t);
        }
        if(!(t instanceof ExtensibleStringType)) return false;
        return value.equals(((ExtensibleStringType)t).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() { return value; }
}