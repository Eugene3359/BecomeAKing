package com.scipath.becomeaking;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.manager.SaveManager;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.fragment.DialogueFragment;

import java.util.ArrayList;
import java.util.List;


public class BecomeAKing extends Application {

    // Fields
    private static BecomeAKing instance;
    private GameState gameState;
    private boolean isLoaded;
    private final boolean isTest = true;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gameState = null;
        isLoaded = false;
    }

    public static BecomeAKing getInstance() {
        return instance;
    }


    // Accessors
    public boolean isTest() {
        return isTest;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getDay() {
        return gameState.day;
    }

    public Personage getPersonage() {
        return gameState.personage;
    }

    public ArrayList<ICategory> getCategories() {
        return gameState.categories;
    }

    public ArrayList<ICategory> getCategoriesSublist(int fromIndex, int toIndex) {
        return new ArrayList<>(gameState.categories.subList(fromIndex, toIndex));
    }

    public ICategory getCategoryById(int id) {
        return gameState.categories
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }


    // Mutators
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    // Methods
    public IStats getCurrentStatBonuses () {
        IStats stats = new Stats();
        for (ICategory ICategory : gameState.categories) {
            IStats categoryStatBonuses = ICategory.getStats();
            for (Stat stat : categoryStatBonuses.getKeys()) {
                stats.add(stat,
                        stats.get(stat)
                                + categoryStatBonuses.get(stat));
            }
        }

        int coinsPerDay = stats.get(Stat.CoinsPerDay);
        coinsPerDay += stats.get(Stat.Might) * stats.get(Stat.CoinsPerMight);
        stats.add(Stat.CoinsPerDay, coinsPerDay);

        return stats;
    }

    public void nextDay() {
        gameState.day++;
        IStats statBonuses = getCurrentStatBonuses();
        gameState.personage.affectHealth(statBonuses.get(Stat.HealthPerDay));
        gameState.personage.affectReputation(statBonuses.get(Stat.ReputationPerDay));
        gameState.personage.affectMoney(statBonuses.get(Stat.CoinsPerDay));

        ArrayList<ICategory> categories = gameState.categories;
        for (int i = 0; i < categories.size(); i++) {
            List<IItem> items = categories.get(i).getItems();
            for (IItem work : items) {
                if (work instanceof Work && items.size() > 1) {
                    work.setInteracted(false);
                    Work.refreshInteractionCounter();
                }
            }
        }
    }

    public void checkPersonageForNegativeValues(AppCompatActivity activity) {
        if (gameState.personage.getHealth() == 0) gameOver(0, activity);
        if (gameState.personage.getReputation() < 0) gameOver(1, activity);
        if (gameState.personage.getMoney() < 0) gameOver(2, activity);
    }

    public void gameOver(int code, AppCompatActivity activity) {
        // Forming game-over message
        int reasonId;
        switch (code) {
            case 0:
                reasonId = R.string.negative_health;
                break;
            case 1:
                reasonId = R.string.negative_reputation;
                break;
            case 2:
                reasonId = R.string.negative_money;
                break;
            default:
                // Game Over from unknown reason
                reasonId = 0;
        }
        String message = activity.getString(
                R.string.s_s,
                activity.getString(reasonId),
                activity.getString(R.string.game_over));

        // Showing dialogue
        DialogueFragment dialogueFragment = DialogueFragment.newInstance(R.string.notification, reasonId, R.string.got_it);
        dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
        dialogueFragment.setCallback(() ->
        {
            // Deleting save file
            SaveManager.deleteSave(getApplicationContext());
            clearGameState();
            activity.finish();
        });
    }

    public void saveGame() {
        SaveManager.saveGame(getApplicationContext(), gameState);
    }

    public void loadGame(AppCompatActivity activity) {
        if (SaveManager.saveExists(getApplicationContext())) {
            // Save file exists
            GameState gameState = SaveManager.loadGame(getApplicationContext());
            if(gameState != null) {
                // Successful load
                this.gameState = gameState;
                isLoaded = true;
            } else {
                // Load error
                DialogueFragment dialogueFragment = DialogueFragment.newInstance(R.string.notification, R.string.something_went_wrong, R.string.got_it);
                dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
            }
        } else {
            // No save file
            DialogueFragment dialogueFragment = DialogueFragment.newInstance(R.string.notification, R.string.save_not_found, R.string.got_it);
            dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
        }
    }

    public void clearGameState() {
        gameState = null;
        isLoaded = false;
    }
}
