package com.mygdx.spacechoppers

import java.util.Stack

class GameStateManager(var game: SpaceChoppersGame) {
    /*
    * Maybe singletonize this class idk
    * */
    private val gameStates = Stack<GameState>()

    fun update(dt: Float) {
        gameStates.peek().update(dt)
    }

    fun render() {
        gameStates.peek().render()
    }

    fun set(state: GameState) {
        gameStates.pop()
        gameStates.push(state)
    }

    fun push(state: GameState) {
        gameStates.push(state)
    }

    fun pop() {
        gameStates.pop()
    }
}