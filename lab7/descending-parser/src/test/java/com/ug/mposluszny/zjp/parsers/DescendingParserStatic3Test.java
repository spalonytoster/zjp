package com.ug.mposluszny.zjp.parsers;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class DescendingParserStatic3Test {

    private Parser parser = new DescendingParserStatic3();

    @Test
    public void parserTest() {
        // is accepted by grammar
        assertTrue(parser.parse("a+(a+a)").isSuccess());
        assertTrue(parser.parse("(a+(a+(a+a)))").isSuccess());
        assertTrue(parser.parse("(a+a)+(a+a)").isSuccess());
        assertTrue(parser.parse("((a+a)+(a))").isSuccess());

        // is NOT accepted by grammar
        assertFalse(parser.parse("(aa)").isSuccess());
        assertFalse(parser.parse("a+aa").isSuccess());
        assertFalse(parser.parse("a+(a+a").isSuccess());
        assertFalse(parser.parse("()").isSuccess());
    }
}
