package com.bowl

class Game {

	def frames = [new Frame()]
	
	Game roll(int pins){
		Frame last = frames.last()
		
		if (last.hasRollsLeft()){
			last.addRoll(pins)
		}
		else{
			Frame nextFrame = new Frame()
			nextFrame.addRoll(pins)
			last.nextFrame = nextFrame
			frames << nextFrame
		}
		return this
	}

	int getGameScore(){
		def gameScore = 0
		frames.each { gameScore += it.frameScore }
		return gameScore
	}
	
	Frame getFrame(int turnNum){
		return frames[turnNum - 1]
	}
}
