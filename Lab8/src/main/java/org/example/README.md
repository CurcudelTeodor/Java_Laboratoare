## Laborator 8

### Am incercat sa rezolv (dificultati la instalare postgresql):

* [x] Compulsory (am adaugat si modul de construire a tabelelor)
* [x] Tema  
  * Am luat doar primele 50 de albume din lista selectand doar anul, albumul, 
    artistul si genurile muzicale. De asemenea, am modificat virgula de la 
    genuri pentru ca `String[] genreNames = data[3].split(",");` nu
    mergea. Ce se intampla in spate de fapt: fisierul are virgule intre fiecare
    coloane. 
  * Ex: un rand din fisierul csv `1965,Rubber Soul,The Beatles,Rock, Pop`
  Cand faceam `String[] data = line.split(csvDelimiter);` considera 
  ce e intre 2 virgule ca fiind atomic, un tot, un token. Adica: `token1`: 1965;
  `token2`: Rubber Soul; `token3`:The Beatles; `token4`: Rock; \
  `token5`: Pop. Deci cand faceam `String[] genreNames = data[3].split(",");` practic puneam in `genreNames` doar Rock, insa
  tot nu stiu de ce imi afisa: `"Rock` in loc de `Rock`.
  * Asadar, am schimbat virgula de la coloana de genuri in simbolul `:` 
  pentru a lua `Rock: Pop` ca un intreg.
  * Apoi cu `String[] genreNames = data[3].split("\\s*:\\s*");` elimin
  spatiile albe dintre `:` si urmatorul gen. Ex: La `Rock: Pop` exista un 
  spatiu intre `:` si `Pop`, iar cu linia de cod de mai sus, eliminam acest 
  spatiu. Acest lucru este necesar deoarece `Pop` si `_Pop` ( _ = spatiu)
  sunt in esenta acelasi lucru. Daca nu am elimina spatiile, s-ar considera 
  ca fiind diferite. `Pop` (fara spatiu in fata) poate aparea atunci cand este
  primul gen care apare pe o anumita linie, ex: `Pop, Funk`.
* [ ] Bonus