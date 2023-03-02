const tab = [10,20,0,5,8,12];
const tab2 = tab.map(note => note < 20 ? note + 1 : note);
console.log(tab2);

const total = tab2.reduce((acc, note) => acc + note);
const moyenne = total / tab2.length;
console.log(moyenne);

const nbNotesSupMoyenne = tab2.filter(note => note > moyenne).length;
console.log(nbNotesSupMoyenne);