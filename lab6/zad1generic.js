// jshint esversion: 6, node: true
'use strict';

const readline = require('readline');
const fs = require('fs');
const _ = require('lodash');

const STATE = {
  START: 'START',
  END: 'END',
  ERR: 'ERR'
};
const EOF = 'EOF';
const FSM = JSON.parse(fs.readFileSync('zad2_fsm.json', 'utf-8'));

let state, lexeme, lexemes;

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function determineState(char, currentState) {
  console.log(`state: ${state}(${char})`);

  _.forEach(FSM.states, (state, name) => {
    if (currentState === name) {
      state.transitions.forEach((transition) => {
        let regex = new RegExp(transition.input);
        if (char.match(regex)) {
          return transition.targetState;
        }
      });
    }
  });

  if (currentState === STATE.ERR) {
    return STATE.ERR;
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

    if (state === STATE.START) {
      if (lexeme !== '') {
        lexemes.push(lexeme);
      }
    }
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
