package com.truebeans.voicetuner.billing;

public class KeyConstructor {

    public static String get() {
        String key1 = "miibiJanbGKQHKIg9W0baqefaaocaq8amiibcGkcaqeaUlL";
        String key3 = "RPhYOK6iDlsrboPOvxT2CG7hrzKWO5IFJtpO2mKQeQr8jBK9T6idd4mCExZ9tW+5Yl3jI3aWrnEd/4w1dNhcRQ/4hbGews9oFr0sT9loIAQW2p1LZn+Qb8QY87Rcora65HHi3MXqwyQ5ppqnMN43r663V3w+qSz4mu70FDIobyVrzf7RjFJW3HphAYZv+B5BuvXOqIDRdhXddBsr0T/2h15qidaqab";
        String key2 = "+azqKJqi6TpKrtIOo+CWKFOG6MHJNyYX2xOzA5CWGCa/oLtKjr8yNfKUM02iwPcbEljndUFcQwagrjC66BAX2bgyZWtt1+ZkCWBFESY25C/Jt8N3jNXq/Uwm894";
        return swap(key1) + swap(key2) + swap(key3);
    }

        private static String swap(String string){
            byte[] bytes = string.getBytes();

            for (
                    int i = 0;
                    i < bytes.length; i++) {
                if (bytes[i] >= 'A' && bytes[i] <= 'Z')
                    bytes[i] = (byte) ('a' + (bytes[i] - 'A'));
                else if (bytes[i] >= 'a' && bytes[i] <= 'z')
                    bytes[i] = (byte) ('A' + (bytes[i] - 'a'));
            }

            return new String(bytes);
        }
    }
