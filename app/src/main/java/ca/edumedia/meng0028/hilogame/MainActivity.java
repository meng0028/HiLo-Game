/**
 * Hi-Lo App : A app used for guessing a number between 1 and 1000 within 10 attempts
 *
 * @author Yanming Meng (meng0028)
 */

package ca.edumedia.meng0028.hilogame;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int userGuess;
    int guessCount;
    int min = 1;
    int max = 1000;
    int theNumber;
    private EditText userNumber;
    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    protected void init() {
        Random r = new Random();
        theNumber = r.nextInt(max - min + 1) + min;
        guessCount = 0;
        userNumber.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button guessBtn = (Button) findViewById(R.id.guessBtn);
        userNumber = (EditText) findViewById(R.id.editText);
        init();
        guessBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                userGuess = Integer.parseInt(userNumber.getText().toString());
                if (userGuess > 1000 || userGuess < 1) {
                    userNumber.setError("You should enter a number between 1 and 1000");
                    userNumber.requestFocus();
                } else if (userGuess > theNumber) {
                    if (guessCount > 10) {
                        Context context = getApplicationContext();
                        CharSequence text = "You reached the maximum attempts" + "\n" + "Please reset";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Your guess is too high!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        guessCount = guessCount + 1;
                    }
                } else if (userGuess < theNumber) {
                    if (guessCount > 10) {
                        Context context = getApplicationContext();
                        CharSequence text = "You reached the maximum attempts" + "\n" + "Please reset";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Your guess is too low!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        guessCount = guessCount + 1;
                    }
                } else if (guessCount > 5 && guessCount <= 10) {
                    Context context = getApplicationContext();
                    CharSequence text = "Excellent win!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Superior win!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                return;
            }
        });

        Button resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Game Restarted";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                init();
            }
        });
        resetBtn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Your are cheating!" + "\n" + "The number is " + theNumber;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                init();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}








