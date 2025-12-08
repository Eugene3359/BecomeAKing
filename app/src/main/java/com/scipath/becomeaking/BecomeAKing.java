package com.scipath.becomeaking;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.contract.model.IGoods;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.data.CitiesList;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.manager.SaveManager;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.activity.BaseActivity;
import com.scipath.becomeaking.view.fragment.DialogueFragment;

import java.util.ArrayList;
import java.util.List;


public class BecomeAKing extends Application implements ViewModelStoreOwner {

    // Fields
    private static BecomeAKing instance;
    private final ViewModelStore viewModelStore = new ViewModelStore();

    private GameState gameState;
    private boolean isLoaded;
    private boolean isTraveling;
    private final boolean isTestMode = true;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gameState = null;
        isLoaded = false;
        isTraveling = false;
    }

    public static BecomeAKing getInstance() {
        return instance;
    }

    @Override @NonNull
    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }


    // Accessors
    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isTraveling() {
        return isTraveling;
    }

    public boolean isTestMode() {
        return isTestMode;
    }

    public GameState getGameState() {
        return gameState;
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
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public IGoods getGoodsStorage() {
        return gameState.goodsStorage;
    }

    public ICity getCity() {
        if (isTraveling) return CitiesList.getOnTheWay();
        return CitiesList.getCity(gameState.cityId);
    }

    public int getDay() {
        return gameState.day;
    }


    // Mutators
    public void isTraveling(boolean isTraveling) {
        this.isTraveling = isTraveling;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setCity(ICity city) {
        if (city != CitiesList.getOnTheWay()) {
            gameState.cityId = city.getId();
        }
    }


    // Methods
    public IStats getCurrentStatBonuses () {
        IStats stats = new Stats();
        for (ICategory ICategory : gameState.categories) {
            IStats categoryStats = ICategory.getStats();
            for (Stat stat : categoryStats.getKeys()) {
                stats.add(stat,stats.get(stat) + categoryStats.get(stat));
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

        boolean isAllDayWorkSelected = false;
        for (ICategory category : gameState.categories) {
            List<IItem> items = category.getItems();
            if (!(items.get(0) instanceof Work)) continue;
            if (items.size() > 1) {
                for (IItem item : items) {
                    ((Work)item).setState(Work.State.NotCompleted);
                }
            } else {
                if (items.get(0).getState() == Work.State.Selected) isAllDayWorkSelected = true;
            }
        }

        if (!isAllDayWorkSelected) getPersonage().renewEnergy();
    }

    public void checkPersonageState(BaseActivity activity) {
        if (gameState.personage.getHealth() == 0) gameOver(0, activity);
        if (gameState.personage.getReputation() < 0) gameOver(1, activity);
        if (gameState.personage.getMoney() < 0) gameOver(2, activity);
    }

    public void gameOver(int code, BaseActivity activity) {
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
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(message)
                .setButton1(R.string.got_it, () -> {
                    // Deleting save file
                    SaveManager.deleteSave(getApplicationContext());
                    clearGameState();
                    activity.finish();
                }).build();
        activity.showDialogue(dialogueFragment);
    }


    // Save/Load
    public void saveGame() {
        SaveManager.saveGame(gameState, getApplicationContext());
    }

    public void loadGame(BaseActivity activity) {
        if (SaveManager.saveExists(getApplicationContext())) {
            GameState gameState = SaveManager.loadGame(getApplicationContext());
            if(gameState != null) {
                this.gameState = gameState;
                isLoaded = true;
            } else {
                activity.showNotification(R.string.something_went_wrong);
                isLoaded = false;
            }
        } else {
            activity.showNotification(R.string.save_not_found);
            isLoaded = false;
        }
    }

    public void clearGameState() {
        gameState = null;
        isLoaded = false;
    }
}
