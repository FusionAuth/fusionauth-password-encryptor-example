/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Base64;

/**
 * @author Matthew Hartstonge
 */
public class ExampleArgon2idPasswordEncryptorTest {
    @Test
    public void encrypt() {
        // Test control input and output to ensure the hash is correct.
        PasswordEncryptor encryptor = new ExampleArgon2idPasswordEncryptor();
        String result = encryptor.encrypt("password", "4MTVxbvk8swI0ys2Lf4saeR3swRvn0f2", 1);
        Assert.assertEquals(result, new String(Base64.getEncoder().encode("$argon2id$v=19$m=65536,t=1,p=1$4MTVxbvk8swI0ys2Lf4saeR3swRvn0f2$RM2FCk53pEw2en0JWtoIqWu3c9xJvhb/7GRx8KkX9kU".getBytes())));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Should_ThrowException_When_TimeCostLessThan1() {
        ExampleArgon2idPasswordEncryptor encryptor = new ExampleArgon2idPasswordEncryptor();
        encryptor.setTimeCost(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Should_ThrowException_When_MemoryLessThan1() {
        ExampleArgon2idPasswordEncryptor encryptor = new ExampleArgon2idPasswordEncryptor();
        encryptor.setMemoryCost(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Should_ThrowException_When_MemoryGreaterThan16_777_215Kibibytes() {
        ExampleArgon2idPasswordEncryptor encryptor = new ExampleArgon2idPasswordEncryptor();
        encryptor.setMemoryCost(16384);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Should_ThrowException_When_ParallelismLessThan1() {
        ExampleArgon2idPasswordEncryptor encryptor = new ExampleArgon2idPasswordEncryptor();
        encryptor.setParallelism(0);
    }
}
