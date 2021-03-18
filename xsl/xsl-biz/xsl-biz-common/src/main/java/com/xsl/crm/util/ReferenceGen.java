package com.xsl.crm.util;

import org.apache.commons.lang3.RandomUtils;

public class ReferenceGen {
    private static final int RANDOM = (int) Math.pow(RadixUtils.MAX_RADIX, 3);

    public static String gen(String prefix) {
        String radix = RadixUtils.radix(System.currentTimeMillis(), RadixUtils.MAX_RADIX);
        return prefix + "-" + radix + "-" + RadixUtils.radix(RandomUtils.nextInt(0, RANDOM), RadixUtils.MAX_RADIX);
    }

}
