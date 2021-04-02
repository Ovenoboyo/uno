package com.unoapp.uno.engine;

import com.unoapp.uno.models.Player;
import com.unoapp.uno.models.Table;

public class GameController {
    private Table table = new Table();
    private Integer turnIndex = 0;

    public Player getCurrentPlayer() {
        return table.getPlayers().get(turnIndex);
    }
}
