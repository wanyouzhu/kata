Tasks:
* Parse value from string
    * 8080
    * true
    * false
    * x.txt
    * 1,2,3
    * a.log,b.log
* Parse schema
    * p:integer:1080
    * v:boolean:false
    * o:string:a.txt
    * w:integers:1,2,3
    * i:strings:a.txt,b.txt
    * p:integer:1080; v:boolean:false
* Parse arguments
    * p:integer:1080 | -p 8080
    * p:integer:1080 | 
    * v:boolean:false | -v true
    * v:boolean:false | -v
    * p:integer:1080; v:boolean:false; i:strings:test | -p 8888 -v true -i a.txt,b.txt



