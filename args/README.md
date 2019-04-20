Tasks:
* Split string into segments
    * Split by simple delimiter
        * 1
        * 1,2,3
        *  1 , 2, 3
        * "this is \"a\"", 'that is \'b\''
        * 'this,is', "that,is"
        * 'this, 'that'
        * "this, "that"
        * 'a:c':b
        * 'a;c';b
    * Split by flag delimiter
        * -p
        * -p 2
        * -p 2 -t 3
        * -p "a -s b" -t
        * -p a b c -t
        * -p a,b,c -t
* Parse value from string
    * Parse single value
        * 5
        * -5
        * +5
        * invalid-integer
        * true
        * false
        * invalid-boolean
        * this
        * string with whitespaces
        * 'single quote', "double quoted"
        * "this is a 'single quote'",
        * 'this is "double quote"'
        * "that is a \"double quote\" value"
        * 'this is a \'single_quote\' value'
    * Parse list value
        * 5,6,7
        * test,that
        * 'this is', 'that, is'
* Parse schema
    * Parse single option from a segment
        * p:integer:5
        * t:integer:
        * t:
        * t:boolean:false
        * t:string:test
        * t:string:
        * t:integers:1,2,3
        * t:strings:test, that, abc
        * t:string:'a:b'
        * t : integer : 8
    * Parse multiple options from list of segments
        * p:integer:3; t:bool:false
        * p:integer:3;
        * ;p:integer:3
        * p:string:'a;b'; t:bool:false
* Parse arguments
    * Parse simple arguments
        * p:boolean:false | -p true
        * u:integer:5 | -u 8
        * p:string: | -p test
        * p:integers: | -p 1,2,3
        * p:string: | -p this is a string
        * p:strings: | -p this,is,a,list,of,string
    * Support default value
        * p:string: | -t other
        * v:boolean:false | -v
        * u:integer:5; v:boolean:false; w:string: | -u 12 -v true -w test
    * Parse arguments that contains special chars
        * p:string: | -p "a fake flag -t here"
        * p:string:;f:string: | -p can you see this -f flag

