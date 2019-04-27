Tasks:
* Parse arguments from schema
    * check size: schema = "n:integer:1; v:integer:5" | commandLine = ""
    * schema = "n:integer:8" | commandLine = ""
    * schema = "v:boolean:false" | commandLine = ""
    * schema = "v:boolean:true" | commandLine = ""
    * schema = "o:string:a.txt" | commandLine = ""
    * schema = "w:integers:1,2" | commandLine = ""
    * schema = "i:strings:a.txt,b.txt" | commandLine = ""
* Parse arguments from command
    * schema = "v:integer:0" | commandLine = "-v 100"
    * schema = "v:boolean:false" | commandLine = "-v"
    * schema = "v:integer:0" | commandLine = "-v -5"
    * schema = "p:integer:0; t:string:x; s:integers:0" | commandLine = "-p 8080 -t test -s 1,2,3"
