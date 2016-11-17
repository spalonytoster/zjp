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

let state, lexeme, lexemes;

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function takeInput() {
  rl.question('> enter input:\n> ', (answer) => {
    parse(answer);
    takeInput();
  });
}

function determineState(char, currentState) {

  // STATE START
  if (currentState === STATE.START) {
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
  if (currentState === STATE.A && char === 'b') {
    return STATE.AB;
  }

  // STATE AB
  if (currentState === STATE.AB && char === 'a') {
    return STATE.ABA;
  }

  // STATE ABA
  if (currentState === STATE.ABA) {
    return determineState(char, STATE.START);
  }

  // STATE NUM
  if (currentState === STATE.NUM) {
    if (isNumber(char)) {
      return STATE.NUM;
    }
    else {
      return determineState(char, STATE.START);
    }
  }

  // STATE ERR
  if (state === STATE.ERR) {
    console.log('> wrong input');
  }

  return STATE.ERR;
}

function parse(input) {
  state = STATE.START;
  lexeme = '';
  lexemes = [];

  // console.log('to parse: ' + input);
  console.log('state: ' + state);

  for (let i = 0; i < input.length; i++) {
    let currentChar = input.charAt(i);
    state = determineState(currentChar, state);
    if (state === STATE.ERR) {
      console.log('> wrong input');
      return;
    }
    lexeme += currentChar;
    console.log('state: ' + state);
    // console.log('currentChar: ' + currentChar);
    // console.log('lexeme: ' + lexeme);
  }
  state = STATE.END;
  console.log('state: ' + state);
  lexemes.push(lexeme);
  lexemes.forEach((lexeme) => {
    console.log('> leks: ' + lexeme);
  });
}

function isLetter(str) {
  return str.length === 1 && str.match(/[a-z]/i);
}

function isNumber(str) {
  return str.length === 1 && str.match(/[0-9]/i);
}

takeInput();
