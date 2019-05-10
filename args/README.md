Tasks:
* Parse boolean arguments
    * v:boolean | "-v true"
    * v:boolean | "-v false"
    * v:boolean | "-v"
    * v:boolean | ""
    * v:boolean | "-v not-a-boolean"
* Parse integer arguments
    * p:integer | "-p 8080"
    * n:integer | "-n -10"
    * n:integer | "-p"
    * n:integer | ""
    * n:integer | "-p not-an-integer"
* Parse string arguments
    * o:string | "-o game"
    * o:string | "-o"
    * o:string | ""
* Parse integers arguments
    * w:integers | "-w 1,2,3"
    * w:integers | "-w -1,-2,-3"
    * w:integers | "-w"
    * w:integers | ""
* Parse strings arguments
    * i:strings | "-i a.lib,b.lib"
    * i:strings | "-i"
    * i:strings | ""
* Parse multiple arguments
    * v:boolean p:integer: o:integers | "-v -p -5 -w -1,-2"