Tasks:
* Build dictionary
  * Read words from word-list file
  * Build dictionary from read words
    * Index a word
    * Group words by index
* Find out anagrams
  * Read words from input file
  * Test if a word is an anagram
    * Index a word
    * Find out words at the same index
  * Write an anagram into output file
  
Tests:
* WordReader can read words from file line by line
* Index should equal to the one indexed from another word has the same letters 
* WordSet can hold multiple words
* WordSet should only hold unique words
* Dictionary should group words by indices
* Dictionary can be looked up by existed key
* Dictionary should return empty WordSet if key is not found
* AnagramResolver should return an anagram if input word can be resolved as an anagram
* AnagramResolver should return nothing if input word can NOT be resolved as an anagram
* Anagrams should write resolved anagrams into output file
* Anagrams should resolve 48162 anagrams from the kata wordlist 
