### Tasks:
* Define schema
  * Define boolean type
  * Define integer type
  * Define string type
  * Define integer-list type
  * Define string-list type
* Convert command line arguments array into Arguments(pairs of flag and value)
  * Check if an element is a flag(with schema)
  * Get 'next' element as value string
  * Build an Argument
* Parse value from Arguments
  * Parse boolean values
  * Parse integer values
  * Parse string values
  * Parse integer-list values
  * Parse string-list values


### Tests
* "l:boolean" + "-l true" -> l true
* "l:boolean" + "-l false" -> l false
* "l:boolean" + "-l" -> true
* "l:boolean" + "-l not-a-boolean" -> error: Malformed boolean value for flag 'l'!
* "p:integer" + "-p 8080" -> p 8080
* "p:integer" + "-p not-an-integer" -> error: Malformed integer value for flag 'p'!
* "p:integer" + "-p" -> error: Missing value part for flag 'p'!
* "d:string" + "-d /srv/test" -> d /srv/test
* "d:string" + "-d" -> error: Missing value part for flag 'd'!
* "w:integers" + "-w 1,2" -> w [1,2]
* "w:integers" + "-w 1" -> w [1]
* "w:integers" + "-w" -> error: Missing value part for flag 'w'!
* "i:strings" + "-i a,b" -> i [a,b]
* "i:strings" + "-i a" -> i [a]
* "i:strings" + "-i" -> error: Missing value part fro flag 'i'!
* "x:integer v:boolean" + "-v -x 5" -> v true, x 5
* "x:integer p:integer" + "-x -5 -p 8080" -> x -5, p 8080
* "x:integer v:boolean" + "" -> v false, x 0
