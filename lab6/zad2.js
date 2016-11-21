// jshint esversion: 6, node: true
'use strict';

const readline = require('readline');
const fs = require('fs');
const State = require('./BaseState');
const Fsm = require('./Fsm');

const EOF = 'EOF';
const States = JSON.parse(fs.readFileSync('zad2.json', 'utf-8'));
let fsm = new Fsm(States);
let lexeme, lexemes;

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function parse(input) {
  fsm.reset();
  lexeme = '';
  lexemes = [];

  for (let i = 0; i < input.length; i++) {
    let currentChar = input.charAt(i);
    fsm.makeTransition(currentChar);

    if (fsm.state === State.START) {
      fsm.makeTransition(currentChar);
      if (lexeme !== '') {
        lexemes.push(lexeme);
        lexeme = '';
      }
    }
    if (fsm.state === State.ERR) {
      break;
    }
    lexeme += currentChar;
    // console.log('currentChar: ' + currentChar);
    // console.log('lexeme: ' + lexeme);
  }
  fsm.makeTransition(EOF);
  console.log('state: ' + fsm.state);

  if (fsm.state === State.END) {
    lexemes.push(lexeme);
  }

  printLexemes(lexemes);

  if (fsm.state !== State.END) {
    console.log('> wrong input');
  }
}

function printLexemes(lexemes) {
  lexemes.forEach((lexeme) => {
    let gender = '';
    if (lexeme.charAt(lexeme.length-1) === 'a') {
      gender = 'kobieta';
    }
    else {
      gender = 'mężczyzna';
    }
    console.log(`> ${gender}: ${lexeme}`);
  });
}

(function takeInput() {
  rl.question('> enter input:\n> ', (answer) => {
    parse(answer);
    takeInput();
  });
})();
