import { expect } from 'chai';
import Anagram from '../src/Anagram';
import AnagramGenerator from '../src/AnagramGenerator'
import AnagramResolver from '../src/AnagramResolver'

describe('Anagram tests', () => {
    it('Can match its concatenation', () => {
        const source = 'ab';
        const anagram = new Anagram('a', 'b');
        expect(anagram.match(source)).equals(true);
    });

    it('Can match its reversed concatenation', () => {
        const source = 'ba';
        const anagram = new Anagram('a', 'b');
        expect(anagram.match(source)).equals(true);
    });

    it('Can match words that contains all its characters', () => {
        const source = 'abababab';
        const anagram = new Anagram('a', 'b');
        expect(anagram.match(source)).equals(true);
    });

    it('Can NOT match words that count of one character is less then then one in anagram', () => {
        const source = 'aob';
        const anagram = new Anagram('ao', 'bo');
        expect(anagram.match(source)).equals(false);
    });

    it('Can generate a sequence of anagrams from word list', () => {
        const wordList = ['a', 'b'];
        const generator = new AnagramGenerator(wordList);
        const anagrams = [];
        generator.walk(anagram => anagrams.push(anagram));
        expect(anagrams).deep.equals([new Anagram('a', 'b'), new Anagram('b', 'a')]);
    });

    it('Can find out matched anagrams', () => {
        const wordList = ['a', 'b', 'c'];
        const anagramResolver = new AnagramResolver(wordList);
        expect(anagramResolver.resolve('ab')).deep.equals([new Anagram('a', 'b'), new Anagram('b', 'a')]);
        expect(anagramResolver.resolve('ba')).deep.equals([new Anagram('a', 'b'), new Anagram('b', 'a')]);
        expect(anagramResolver.resolve('bc')).deep.equals([new Anagram('b', 'c'), new Anagram('c', 'b')]);
    });
});