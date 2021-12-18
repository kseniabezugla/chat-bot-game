package com.devatlant.chatbot.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.api.objects.Message;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    private Game testSubject;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        testSubject = new Game(100);
    }

    @org.junit.jupiter.api.Test
    void should_respond_with_start() {
        Message start = buildMessage("/start");
        RESPONSE res = testSubject.reactOnGamerMessage(start);
        assertEquals(RESPONSE.START, res);
    }

    public Message buildMessage(final String text){
        try {
            return objectMapper.readValue(String.format("{\"text\":\"%s\"}", text), Message.class);
        } catch (IOException e) {
           throw new RuntimeException("wrong json syntax for "+ text,e);
        }
    }
    @org.junit.jupiter.api.Test
    public void should_return_true_when_input_data_is_integer(){
        //given
        testSubject = new Game(100);

        //run
        boolean res = testSubject.isInteger("100");

        // assert
        assertEquals(true, res);
    }

    @org.junit.jupiter.api.Test
    public void should_return_false_for_text_anyText(){
        //given
        testSubject = new Game(100);

        //run
        boolean res = testSubject.isInteger("text");

        // assert
        assertEquals(false, res);
    }
}
