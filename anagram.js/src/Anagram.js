export default class Anagram {
    constructor(word1, word2) {
        this.chars = this.sortChars(word1 + word2);
    }

    match(source) {
        return this.sortChars(source).includes(this.chars);
    }

    sortChars(str) {
        return (str).split('').sort().join('');
    }
}
