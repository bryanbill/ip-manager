package com.syokinet.ipmanager.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpValidationUtil {

    private static final String IP_ADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final String SUBNET_MASK_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final Pattern IP_ADDRESS_REGEX = Pattern.compile(IP_ADDRESS_PATTERN);
    private static final Pattern SUBNET_MASK_REGEX = Pattern.compile(SUBNET_MASK_PATTERN);

    public static boolean isValidIpAddress(String ipAddress) {
        return validate(ipAddress, IP_ADDRESS_REGEX);
    }

    public static boolean isValidSubnetMask(String subnetMask) {
        return validate(subnetMask, SUBNET_MASK_REGEX);
    }

    private static boolean validate(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
