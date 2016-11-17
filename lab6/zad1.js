// jshint esversion: 6, node: true
'use strict';

const readline = require('readline');

const STATE = {
  START: 'START',
  A: 'A',
  AB: 'AB',
  ABA: 'ABA',
  NUM: 'NUM',
  END: 'END',
  ERR: 'ERR'
};

const EOF = 'EOF';

let state, lexeme, lexemes;

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function determineState(char, state) {
  console.log(`state: ${state}(${char})`);
  // STATE START
  if (state === STATE.START) {
    if (lexeme !== '') {
      lexemes.push(lexeme);
    }
    lexeme = '';

    if (char === 'a') {
      return STATE.A;
    }
    else if (isNumber(char)) {
      return STATE.NUM;
    }
  }

  // STATE A
  if (state === STATE.A && char === 'b') {
    return STATE.AB;
  }

  // STATE AB
  if (state === STATE.AB && char === 'a') {
    return STATE.ABA;
  }

  // STATE ABA
  if (state === STATE.ABA) {
    if (isLetter(char) || isNumber(char)) {
      return determineState(char, STATE.START);
    }
    if (isEOF(char)) {
      return STATE.END;
    }
  }

  // STATE NUM
  if (state === STATE.NUM) {
    if (isNumber(char)) {
      return STATE.NUM;
    }
    if (isLetter(char)) {
      return determineState(char, STATE.START);
    }
    if (isEOF(char)) {
      return STATE.END;
    }
  }

  // STATE ERR
  if (state === STATE.ERR) {
    return STATE.ERR;
  }

  // STATE END
  if (state === STATE.END) {
    return STATE.END;
  }

  return STATE.ERR;
}

function parse(input) {
  state = STATE.START;
  lexeme = '';
  lexemes = [];

  for (let i = 0; i < input.length; i++) {
    let currentChar = input.charAt(i);
    state = determineState(currentChar, state);
    if (state === STATE.ERR) {
      break;
    }
    lexeme += currentChar;
    // console.log('currentChar: ' + currentChar);
    // console.log('lexeme: ' + lexeme);
  }
  state = determineState(EOF, state);
  console.log('state: ' + state);

  if (state === STATE.END) {
    lexemes.push(lexeme);
  }

  printLexemes(lexemes);

  if (state !== STATE.END) {
    console.log('> wrong input');
  }
}

function isLetter(str) {
  if (!str) return false;
  return str.length === 1 && str.match(/[a-z]/i);
}

function isNumber(str) {
  if (!str) return false;
  return str.length === 1 && str.match(/[0-9]/i);
}

function isEOF(char) {
  return char === EOF;
}

function printLexemes(lexemes) {
  lexemes.forEach((lexeme) => {
    console.log('> leks: ' + lexeme);
  });
}

(function takeInput() {
  rl.question('> enter input:\n> ', (answer) => {
    parse(answer);
    takeInput();
  });
})();
