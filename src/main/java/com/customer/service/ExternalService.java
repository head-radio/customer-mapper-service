package com.customer.service;


import com.customer.IExternalService;
import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.util.Random;

@org.springframework.stereotype.Service
@Log4j2
public class ExternalService implements IExternalService {

    /**
     * create external id and provide it to the client
     * @param customerId
     * @return the external id created
     * @throws Exception if something goes wrong
     */
    @Override
    public String createExternalId(String customerId) throws Exception {

        return calculateHash(customerId + new Random());

    }

    public static String calculateHash(String inputValue) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputValue.getBytes());

        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

}
