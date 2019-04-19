Tasks:
* Parse value from string
    * Parse single value
        * 5
        * -5
        * +5
        * true
        * false
        * this
        * that is
        * 'this is a "string" value'
        * "that is a 'string' value"
        * "that is a \"string\" value"
        * 'this is a \'string\' value'
    * Parse list value
        * 5,6,7
        * 5, 6, 7
        * test,that
        * test, that
        * 'this is', 'that, is'
        * "this, is", "that is"
* Parse schema
    * Split string into segments
        * a.txt
        * a.txt:b.txt
        * a.txt:
        * :a.txt
        * a.txt:b.txt:nice
        * a.txt : b.txt
        * 'str:abc':nice
        * "str:abc":nice
        * 'str\'good:abc':nice
        * "str\"good:abc":nice
        * 'str"good:abc':nice
        * "str'good:abc":nice
    * Parse single option from a segment
        * p:integer:5
        * t:integer:
        * t:
        * t:boolean:false
        * t:string:test
        * t:string:
        * t:integer[]:1,2,3
        * t:string[]:test, that, abc
    * Parse multiple options from list of segments
        * p:integer:3; t:bool:false
        * p:integer:3;
        * ;p:integer:3
        * p:string:'a;b'; t:bool:false
* Parse option values
    * Split string into flag-value pairs
        * -p true
        * -p test
        * -p 5
        * -p 1,2,3
        * -p this is a string
        * -p this,is,a,list,of,string
        * -p 
        * -p -t
        * -p "a fake flag -t here"
        * -p 'a fake flag -t here'
        * -p 'a fake flag -t here
        * -p 'a fake flag -t here
        * -p can you see this -f flag
    * Parse value by schema
        * u:integer:5 | -u 8
        * v:boolean:false | -v
        * u:integer:5; v:boolean:false; w:string: | -u 12 -v true -w test
