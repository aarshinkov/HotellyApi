package com.aarshinkov.api.hotelly;

import java.util.UUID;
import org.junit.Test;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class IdTests {

    @Test
    public void generateUUID() {
        System.out.println("UUID: " + UUID.randomUUID().toString());
    }
}
