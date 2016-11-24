package com.ug.mposluszny.zjp.parsers;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DescendingParserStaticTest {

    private Parser parser = new DescendingParserStatic();

    @Test
    public void parserTest() {
        assertNotNull(parser.parse("abecd"));
        assertNotNull(parser.parse("aabecdd"));
        assertNotNull(parser.parse("aabbeccdd"));
        assertNotNull(parser.parse("aaabbeccddd"));

        assertNull(parser.parse("aaabecdd"));
        assertNull(parser.parse("abeccd"));
        assertNull(parser.parse("abbecd"));
        assertNull(parser.parse("abeecd"));
    }
}
