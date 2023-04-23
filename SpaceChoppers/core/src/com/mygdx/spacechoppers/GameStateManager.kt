package com.mygdx.spacechoppers

import java.util.Stack

class GameStateManager(val game: SpaceChoppersGame) {

    private val gameStates = Stack<GameState>()

    fun update(dt: Float) {
        gameStates.peek().update(dt)
    }

    fun render(dt: Float) {
        gameStates.peek().render(dt)
    }

    fun set(state: GameState) {
        gameStates.pop().dispose()
        gameStates.push(state)
    }

    fun push(state: GameState) {
        gameStates.push(state)
    }

    fun pop() {
        gameStates.pop()
    }
}