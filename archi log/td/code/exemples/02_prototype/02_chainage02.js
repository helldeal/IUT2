// On commence par créer un objet o pour lequel la fonction f sera
// son constructeur et lui créera deux propriétés en propre
// a et b :
    let f = function () {
        this.a = 1;
        this.b = 2;
    }
    let o = new f(); // {a: 1, b: 2}

    // on ajoute des propriétés au prototype de la fonction
    // f
    f.prototype.b = 3;
    f.prototype.c = 4;

    // Note : on ne définit pas le prototype de f avec f.prototype = {b:3,c:4};
    // car cela briserait la chaîne de prototype

    // o.[[Prototype]] possède les propriétés b and c.
    // o.[[Prototype]].[[Prototype]] est Object.prototype.
    // Enfin, o.[[Prototype]].[[Prototype]].[[Prototype]] vaut null.
    // On a alors atteint la fin de la chaîne de prototype car,
    // par définition, null n'a pas de [[Prototype]].
    // Ainsi, la chaîne complète est ici :
    // {a: 1, b: 2} ---&gt; {b: 3, c: 4} ---&gt; Object.prototype ---&gt; null

    console.log(o.a); // 1
    // Existe-t-il une propriété 'a' en propre sur o ? Oui, elle vaut 1.

    console.log(o.b); // 2
    // Existe-t-il une propriété 'b' en propre sur o ? Oui, elle vaut 2.
    // Le prototype possède également une propriété 'b' mais elle n'est pas
    // utilisée.
    // C'est ce qu'on appelle l'ombrage (shadowing en anglais)

    console.log(o.c); // 4
    // Existe-t-il une propriété 'c' en propre sur o ? Non, on vérifie le
    // prototype.
    // Existe-t-il une propriété 'c' en propre sur o.[[Prototype]] ?
    // Oui, elle vaut 4.

    console.log(o.d); // undefined
    // Existe-t-il une propriété 'd' en propre sur o ? Non, on vérifie le
    // prototype.
    // Existe-t-il une propriété 'd' en propre sur o.[[Prototype]] ? Non, on vérifie le
    // prototype.
    // o.[[Prototype]].[[Prototype]] est Object.prototype et ne contient pas
    // de propriété 'd' par défaut. On vérifie son prototype.
    // o.[[Prototype]].[[Prototype]].[[Prototype]] est null, on arrête la recherche
    // aucune propriété n'est trouvée, le moteur renvoie undefined.</db:programlisting

