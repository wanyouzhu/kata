export default class Anagram {
    constructor(word1, word2) {
        this.chars = Anagram.sortChars(word1 + word2);
    }

    match(source) {
        return Anagram.sortChars(source).includes(this.chars);
    }

    static sortChars(str) {
        return (str).split('').sort().join('');
    }
}
