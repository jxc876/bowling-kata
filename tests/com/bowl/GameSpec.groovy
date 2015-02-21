package com.bowl

import spock.lang.Specification

class GameSpec extends Specification {
	
	Game game = new Game()
	
	def "verify new game starts at zero"(){
		expect:
		game.gameScore == 0
	}
	
	def "verify score after one roll"(){
		when:
		game.roll(5)
		
		then:
		game.gameScore == 5
	}
	
	def "verify score after one frame without bonuses"(){
		when:
		game.roll(5)
		game.roll(3)
		
		then:
		game.gameScore == 8
	}
	
	def "verify that a new frame is only created after two rolls"(){
		when:
		game.roll(5)
		game.roll(3)
		game.roll(1)
		
		then:
		game.frames.size == 2
	}
	
	def "verify that we skip to next frame after a strike"(){
		when:
		game.roll(10)
		game.roll(1)
		
		then: 
		game.frames.size == 2
	}
	
	
	def "verify score after a strike with bonus"(){
		when:
		game.roll(10) // strike
		game.roll(3) // bonus roll
		game.roll(2) // bonus roll
		
		then:
		game.getFrame(1).frameScore == 15
		game.gameScore == 20 
	}
	
	/**
	 * Testing against sample data from:
	 * http://bowling.about.com/od/rulesofthegame/a/bowlingscoring.htm 
	 */
	def "verify scoring for a full game"(){
		expect:
		// turn 1
		game.roll(10)
		
		// turn 2
		game.roll(7)
		game.roll(3)
		game.getFrame(1).frameScore == 20		
		
		// turn 3
		game.roll(7)
		game.roll(2)
		game.getFrame(2).frameScore == 17
			
		// turn 4
		game.roll(9)
		game.roll(1)
		game.getFrame(3).frameScore == 9
		
		// turn 5
		game.roll(10)
		game.getFrame(4).frameScore == 20
		
		// turn 6
		game.roll(10)
		
		// turn 7
		game.roll(10)
		game.getFrame(5).frameScore == 30
		
//		
//		// turn 8
//		game.roll(2)
//		game.roll(3)
//		game.gameScore == 138
	}
}
