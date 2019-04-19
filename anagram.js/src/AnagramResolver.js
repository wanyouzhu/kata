import AnagramGenerator from "./AnagramGenerator";

export default class AnagramResolver {
    constructor(wordList) {
        this.generator = new AnagramGenerator(wordList);
    }

    resolve(source) {
        const result = [];
        this.generator.walk(anagram => anagram.match(source) && result.push(anagram));
        return result;
    }
}