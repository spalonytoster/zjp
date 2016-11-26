package com.ug.mposluszny.zjp.parsers;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class DescendingParserStaticTest {

    private Parser parser = new DescendingParserStatic();

    @Test
    public void parserTest() {
        // is accepted by grammar
        assertTrue(parser.parse("abecd").isSuccess());
        assertTrue(parser.parse("aabecdd").isSuccess());
        assertTrue(parser.parse("aabbeccdd").isSuccess());
        assertTrue(parser.parse("aaabbeccddd").isSuccess());

        // is NOT accepted by grammar
        assertFalse(parser.parse("aaabecdd").isSuccess());
        assertFalse(parser.parse("abeccd").isSuccess());
        assertFalse(parser.parse("abbecd").isSuccess());
        assertFalse(parser.parse("abeecd").isSuccess());
    }
}
