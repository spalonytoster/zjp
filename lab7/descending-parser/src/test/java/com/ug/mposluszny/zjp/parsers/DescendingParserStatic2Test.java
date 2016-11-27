package com.ug.mposluszny.zjp.parsers;


import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class DescendingParserStatic2Test {
    private Parser parser = new DescendingParserStatic2();

    @Test
    public void parserTest() {
        // is accepted by grammar
        assertTrue(parser.parse("axbx").isSuccess());
        assertTrue(parser.parse("aaxbbbx").isSuccess());
        assertTrue(parser.parse("axbbx").isSuccess());
        assertTrue(parser.parse("aaaaaaaxbx").isSuccess());

        // is NOT accepted by grammar
        assertFalse(parser.parse("axxbx").isSuccess());
        assertFalse(parser.parse("axbxx").isSuccess());
        assertFalse(parser.parse("axbbxx").isSuccess());
        assertFalse(parser.parse("xaxbx").isSuccess());
    }
}
