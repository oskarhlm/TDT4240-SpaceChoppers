package com.mygdx.spacechoppers;

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameStateManagerTest {
    private lateinit var game: SpaceChoppersGame
    private lateinit var gameStateManager: GameStateManager

    @Before
    fun setUp() {
        game = SpaceChoppersGame()
        game.create()
        gameStateManager = GameStateManager(game)
    }

    @Test
    fun testUpdateMethod() {
        val mockState = MockGameState(gameStateManager)
        gameStateManager.push(mockState)
        gameStateManager.update(0.1f)
        assertEquals(1, mockState.updateCallCount)
    }

    @Test
    fun testRenderMethod() {
        val mockState = MockGameState(gameStateManager)
        gameStateManager.push(mockState)
        gameStateManager.render()
        assertEquals(1, mockState.renderCallCount)
    }

    @Test
    fun testSetMethod() {
        val mockState1 = MockGameState(gameStateManager)
        val mockState2 = MockGameState(gameStateManager)
        gameStateManager.push(mockState1)
        gameStateManager.set(mockState2)
        assertEquals(mockState2, gameStateManager.pop())
    }

    @Test
    fun testPushMethod() {
        val mockState1 = MockGameState(gameStateManager)
        val mockState2 = MockGameState(gameStateManager)
        gameStateManager.push(mockState1)
        gameStateManager.push(mockState2)
        assertEquals(mockState2, gameStateManager.pop())
    }

    @Test
    fun testPopMethod() {
        val mockState1 = MockGameState(gameStateManager)
        val mockState2 = MockGameState(gameStateManager)
        gameStateManager.push(mockState1)
        gameStateManager.push(mockState2)
        val state = gameStateManager.pop()
        assertEquals(mockState1, state)
    }

    private class MockGameState(gsm: GameStateManager) : GameState(gsm) {
        var updateCallCount = 0
        var renderCallCount = 0

        override fun update(dt: Float) {
            updateCallCount++
        }

        override fun render() {
            renderCallCount++
        }
    }

}

