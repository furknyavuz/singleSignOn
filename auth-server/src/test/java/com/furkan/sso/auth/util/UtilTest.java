package com.furkan.sso.auth.util;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class UtilTest {

    @Test
    public void testGenerateAuthToken() {
        // given
        Util util = new Util();

        // when
        String result = util.generateAuthToken();

        // then
        assertNotNull(result);
    }
}
