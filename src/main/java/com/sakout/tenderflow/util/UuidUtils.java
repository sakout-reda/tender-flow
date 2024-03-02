package com.sakout.tenderflow.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class UuidUtils {

    private UuidUtils() {
    }

    public static String getHashedUuid(LocalDateTime dateCreation) {
        UUID uuid = UUID.randomUUID();
        String hash = uuid.toString() + dateCreation;
        return DigestUtils.sha256Hex(hash);

    }
    public static String generateRequestNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate the first seven digits
        for (int i = 0; i < 7; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        // Add the en dash
        sb.append("-");

        // Generate the remaining seven digits
        for (int i = 0; i < 7; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }


        return sb.toString();
    }
}
