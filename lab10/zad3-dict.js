t={}
require('fs').readFileSync('i','utf-8').split(' ').forEach(e=>{
  if(!t[e])t[e]=0
  t[e]++;
})
console.log(t);
