package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.model.ParsingResult;

public interface Parser {
    ParsingResult parse(String input);
}
