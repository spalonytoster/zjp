// jshint esversion: 6, node: true
'use strict';

const _ = require('lodash');
const STATE = require('./BaseState');

let Fsm = function Fsm(states) {
  this.states = states;
};

Fsm.prototype.reset = function reset() {
  this.state = STATE.START;
};

Fsm.prototype.makeTransition = function makeTransition(input) {
  let _this = this,
      visitedStates = [];

  let determineState = (fromState, input) => {
    console.log(`state: ${fromState}(${input})`);
    let resultState;

    _.each(_this.states, (state, name) => {
      if (fromState === name) {
        state.transitions.forEach((transition) => {
          let regex = new RegExp(transition.input);
          if (input.match(regex)) {
            resultState = transition.targetState;
          }
        });
      }
    });

    if (typeof resultState !== 'undefined') {
      return resultState;
    }

    if (fromState === STATE.ERR) {
      return STATE.ERR;
    }

    return STATE.ERR;
  };

  this.state = determineState(this.state, input);
  return this.state;
};

module.exports = Fsm;
