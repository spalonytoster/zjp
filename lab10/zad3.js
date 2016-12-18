fs=require('fs')
t={}
fs.readFileSync('input.txt','utf-8').split(' ').forEach(e=>{
  if(!t[e])t[e]=0
  t[e]++;
})
console.log(t);
