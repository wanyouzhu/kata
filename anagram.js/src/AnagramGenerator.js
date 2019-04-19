import Anagram from "./Anagram";

export default class AnagramGenerator {
    constructor(wordList) {
        this.wordList = wordList;
    }

    walk(consumer) {
        this.wordList.forEach((word1, i) => this.wordList.forEach((word2, j) => i != j && consumer(new Anagram(word1, word2))));
    }
}