package com.danielk.jnotepad.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ManageTextStatisticsTest {

    @ParameterizedTest
    @DisplayName("Correctly calculating chars (excl. whitespaces) in given Strings:")
    @MethodSource("charFactory")
    void getCharactersNoWhitespaces_parametrized(String text, int expected, String description) {

        //when
        int charactersCounted = ManageTextStatistics.getCharactersNoWhitespaces(text);

        //then
        assertEquals(expected, charactersCounted);
    }


    private static Stream<Arguments> charFactory(){
        return Stream.of(
          Arguments.of("AAA aaa a BBBBBbbbb   \n  q111",20, "(Correctly calculated chars (excl. whitespaces) in mixed String)"),
                Arguments.of("",0,"(correctly calculated \"0\" chars (excl. whitespaces) when String is empty)"),
                Arguments.of(null,0,"(correctly calculated \"0\" chars (excl. whitespaces) when String is null)"),
                Arguments.of("          ",0,"(correctly calculated \"0\" chars (excl. whitespaces) when String consists only of spaces)"),
                Arguments.of("          \n ",0,"correctly calculated \"0\" chars (excl. whitespaces) when String consists of mix of white spaces"),
                Arguments.of("qwertyuiop",10,"(correctly calculated chars(excl. whitespaces) when String has no whitespaces at all)"),
                Arguments.of(" qwertyuiop",10,"(correctly calculated chars (excl. whitespaces) when only first character is a whitespace)"),
                Arguments.of("qwertyuiop ",10, "(Correctly calculated chars (excl. whitespaces) when only last character is a whitespace)")
        );
    }

    @ParameterizedTest
    @DisplayName("Correctly calculating number of words in given Strings:")
    @MethodSource("argumentFactory")
    void getWordsCount_parametrized(String text, int expected, String description) {

        //when
        int wordsCount = ManageTextStatistics.getWordsCount(text);

        //then
        assertEquals(expected, wordsCount);
    }

    private static Stream<Arguments> argumentFactory(){

        return Stream.of(
                Arguments.of("qqq QQQ a AAA aaaaaaacvvv", 5, "(Correctly calculated mixed set of words)"),
                Arguments.of(null, 0, "(Correctly calculated \"0\" words when String is null)"),
                Arguments.of("",0,"(Correctly calculated words when String is empty)"),
                Arguments.of("aaaQQQ",1,"(Correctly calculated \"1\" when one word, few chars long)"),
                Arguments.of("q",1,"(Correctly calculated \"1\" when one word, one char long (other than whitespace))"),
                Arguments.of("\n",0,"(Correctly calculated \"0\" when one word, one char long (whitespace))"),
                Arguments.of("qqq   qqq    \n\n  QQQ",3,"(Correctly calculated words when separated with more than one whitespace)"),
                Arguments.of("                   \n\n", 0,"(Correctly calculated \"0\" when only whitespaces)"),
                Arguments.of("q w e r t y",6,"(Correctly calculating set of single char words)")
        );
    }
}