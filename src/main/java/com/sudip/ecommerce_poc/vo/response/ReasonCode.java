package com.sudip.ecommerce_poc.vo.response;

import java.util.*;

public class ReasonCode extends ExtensibleStringType {
    private static final Set<String> existingValuesSet = new HashSet();
    private static final Map<String, ReasonCode> enumMap = new HashMap();
    public static final ReasonCode OKAY = new ReasonCode("OKAY");
    public static final ReasonCode NONE = new ReasonCode("NONE");
    public static final ReasonCode UNAUTHORIZED = new ReasonCode("UNAUTHORIZED");
    public static final ReasonCode SF_INSTATIATION_EXCEPTION = new ReasonCode("SF_INSTATIATION_EXCEPTION");
    public static final ReasonCode SQL_EXCEPTION = new ReasonCode("SQL_EXCEPTION");
    public static final ReasonCode INVALID_PARAMETER = new ReasonCode("INVALID_PARAMETER");
    public static final ReasonCode MISSING_PARAMETER = new ReasonCode("MISSING_PARAMETER");
    public static final ReasonCode NOT_FOUND = new ReasonCode("NOT_FOUND");
    public static final ReasonCode TIMEDOUT = new ReasonCode("TIMEDOUT");
    public static final ReasonCode INVALID_REQUEST = new ReasonCode("INVALID_REQUEST");
    public static final ReasonCode NON_EXISTENT_CUSTOMER_ID = new ReasonCode("NON_EXISTENT_CUSTOMER_ID");
    public static final ReasonCode ALREADY_PRESENT = new ReasonCode("ALREADY_PRESENT");

    private static String checkDup(String value) {
        if (!existingValuesSet.contains(value)) {
            existingValuesSet.add(value);
        }
        return value;
    }


    public ReasonCode(String value) {
        super(checkDup(value));
        enumMap.put(value, this);
    }

    public static Optional<ReasonCode> findRC(String rc) {
        return enumMap.containsKey(rc) ? Optional.of((ReasonCode)enumMap.get(rc)) : Optional.empty();
    }
}

