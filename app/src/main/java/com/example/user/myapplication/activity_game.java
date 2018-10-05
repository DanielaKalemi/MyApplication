package com.example.user.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class activity_game extends AppCompatActivity implements View.OnClickListener {

    TextView tv_info, tv_word;
    EditText guess;
    Button b_check, b_new;

    String currentWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        tv_word = findViewById(R.id.tv_word);

        guess = findViewById(R.id.guess);

        b_check = findViewById(R.id.b_check);
        b_check.setOnClickListener(this);
        b_new = findViewById(R.id.b_new);
        b_new.setOnClickListener(this);

        newGame();
    }


    @Override
    public void onClick(View view) {

        if (view == b_check) {
            b_check();
        } else if (view == b_new) {
            newGame();
        }

    }


    private void b_check() {
        String w = guess.getText().toString();

        if (currentWord.equals(w)) {
            Toast.makeText(this, "Congratulations ! You found the word " + currentWord, Toast.LENGTH_SHORT).show();
            newGame();
        } else {
            Toast.makeText(this, "Retry !", Toast.LENGTH_SHORT).show();
        }
    }

    private void newGame() {
        currentWord = Anagram.randomWord();
        String wordShuffled = Anagram.shuffleWord(currentWord);
        tv_word.setText(wordShuffled);
        guess.setText("");
    }
}
