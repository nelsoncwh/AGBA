package com.agba.wealth.wrapper.utils;

import java.util.Arrays;
import java.util.List;

public class BuildFundWealth {
    public static List<String> buildFundWealth(String source) {
        return Arrays.stream(source.split(",")).toList();
    }
}
