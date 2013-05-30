package com.nerdspvp.tc.games;

import com.nerdspvp.tc.Game;
import com.nerdspvp.tc.Team;
import com.nerdspvp.tc.WorldWrapper;

public class StandardGame extends Game {

    public Team redTeam = new Team("Red", 0xff0000);
    public Team blueTeam = new Team("Blue", 0x0000ff);

    @Override
    public WorldWrapper getWorld() {
        return null;
    }
}
