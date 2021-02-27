package mcm.edu.ph.MorphTech_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Hero morph = new Hero("Derk",1500,100,85,100,15,25,10);

    String bon = "Vaughn";
    int bonhealth = 1000;
    int bonMinDPT = 75;
    int bonMaxDPT = 100;

    int turnNumber = 1; //This variable is responsible for counting how many turns the game has.

    @Override
    protected void onCreate(Bundle s) {
        /** any code here in onCreate executes only once: during the start of an app **/
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        /** button is declared here so that we can set an onClickListener **/
        Button nextTurn = findViewById(R.id.btnNextTurn);

        /** TextViews are declared here so that text displays are updated during the start of the app **/
        TextView txtMonsName = findViewById(R.id.txtEnemy_name);
        TextView txtHeroName = findViewById(R.id.txtPlayer_name);
        TextView txtMonsHP = findViewById(R.id.txtEnemy_hp);
        TextView txtHeroHP = findViewById(R.id.txtPlayer_hp);
        TextView txtHeroDPT = findViewById(R.id.txtPlayerDPT);
        TextView txtMonsDPT = findViewById(R.id.txtEnemyDPT);

        /** Setting of the previously declared TextViews so it has information shown right from the start**/
        txtHeroName.setText(morph.getUnitName());
        txtMonsName.setText(bon);
        /** directly putting integers as it is will cause an error, so heroHP and monsHP are first converted to string using String.valueOf() Method**/
        txtHeroHP.setText(String.valueOf(morph.getbaseHealth()));
        txtMonsHP.setText(String.valueOf(bonhealth));
        /** to display the damage ranges of of player and enemy, i showed the minimum damage, the the maximum possible damage **/
        txtHeroDPT.setText(morph.getMinDPT()+ " ~ "+morph.getMaxDPT());
        txtMonsDPT.setText(bonMinDPT+ " ~ "+bonMinDPT);
        /** setting the onClickListener for the button to allow it to "sense" user input **/
        nextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        /** onClick() Method allows any code put into it to be executed whenever a button(with an onClickListener) is pressed **/

        /** I added a randomizer variable to add more unpredictability to the values given **/
        Random randomizer = new Random();

        /** declared the buttons and the textviews so that they can be modified later on **/
        Button nextTurn =       findViewById(R.id.btnNextTurn);
        TextView txtMonsHP =    findViewById(R.id.txtEnemy_hp);
        TextView txtHeroHP =    findViewById(R.id.txtPlayer_hp);
        TextView txtCombatLog = findViewById(R.id.txtTurnLog);

        /** We displayed the HP values here to reset the display every time the button is clicked**/
        txtMonsHP.setText(String.valueOf(bonhealth));
        txtHeroHP.setText(String.valueOf(morph.getbaseHealth()));

        /** the randomizer we declared earlier was used in the declaration of turn damage of the player and the enemy.
         *  Each time onClick() is executed, the randomizer generates a number from the range provided, in our case, it
         *  works like this:
         *
         *  usually the randomizer starts its lower bound range from zero to the upper bound that we set it upon.
         *  this will be a problem as we intend the randomized ranged of values to have lower bound to start at the
         *  minimum damage(75 for player, 100 for enemy) to the maximum damage (100 for player, 150 for enemy). So
         *  to address this, we set the upper bound limit to the maximum damage MINUS the minimum damage. For example,
         *  for the player, it should be (100 - 75), which is 25. now the randomizer has a range from zero to 25.
         *  Then, we add back the minimum damage that we deducted earlier which is 75. In this way, we can simulate the
         *  the range of 75 to 100. **/
        int heroDPT = randomizer.nextInt(morph.getMaxDPT() - morph.getMinDPT()) + morph.getMinDPT();
        int monsDPT = randomizer.nextInt(bonMaxDPT - bonMinDPT) + bonMinDPT;

        /** the switch for the button/s starts here. DO NOT MODIFY!**/
        switch (v.getId()){
            case R.id.btnNextTurn:

                /** To make our turn based game work. We first have to assign who goes to attack in a particular turn,
                 * and for this example project ODD turns (1,3,5,7....) goes to the player while EVEN turns (2,4,6 etc)
                 * goes to the enemy. so we will start our program with a condition to check whether the turn number is
                 * ODD or EVEN. We can achieve this by using modulo operator. All real numbers can be classified only as
                 * odd or even, for this case if the number is ODD (has a remainder of 1) it will execute the player's
                 * attack**/
                if(turnNumber%2 == 1){
                    /**When an attack is initiated by the player, the monster's health is deducted by randomized damage
                     * from the player. Because this is a very basic sample of a turn based game, no armor reductions or
                     * passive and active spells were added in this demo. Also, as we have elapsed our turn, we incremented
                     * the turn number to simulate the end of the turn.**/
                    bonhealth = bonhealth - heroDPT;
                    turnNumber++;
                    /** We have created a combat log to show how much damage was dealt**/
                    txtCombatLog.setText("The Player dealt " +heroDPT+ " damage to the Enemy");
                    /** the HP values of the monster is updated to correspond with the deduction taken from the player damage **/
                    txtMonsHP.setText(String.valueOf(bonhealth));
                    /** We also have updated the text of the button to show how much turns have elapsed **/
                    nextTurn.setText("Enemy's Turn ("+turnNumber+ ")");
                    /** this nested condition here catches whether we dealt a fatal blow (our damage exceeded the monster hp.
                     * If triggered, it will cause everything to be reset to its default value. Because we are not using classes,
                     * Every value has to be reset manually.**/
                    if (bonhealth < 0){
                        txtCombatLog.setText("The Player dealt " +heroDPT+ " damage to the Enemy. The Player was Victorious!");
                        morph.setBasehealth(1500);
                        bonhealth = 1000;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    } // end of the resetting condition
                } // end of player's turn condition
                /** The condition below is if the current turn number is EVEN (The condition literally means
                 * "IF TURN NUMBER HAS NO REMAINDER WHEN DIVIDED BY TWO"). The code below will not be explained
                 * any further as it is just a mirror of the code we have for the player turn**/
                else if(turnNumber%2 != 1){
                    morph.setBasehealth(morph.getbaseHealth() - bonMinDPT);
                    turnNumber++;
                    txtCombatLog.setText("The Monster dealt " +monsDPT+ " damage to the Player");
                    txtHeroHP.setText(String.valueOf(morph.getbaseHealth()));
                    nextTurn.setText("Player's Turn ("+turnNumber+ ")");
                    if (morph.getbaseHealth() < 0){
                        txtCombatLog.setText("The Monster dealt " +monsDPT+ " damage to the Player. The Player Died");
                        morph.setBasehealth(1500);
                        bonhealth = 1000;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    }// end of the resetting condition
                } // end of the monsters turn condition
                break;  // switch breaker for the button function. DO NOT EDIT
        }
    }
}