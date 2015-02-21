package com.bowl

class Frame {

	Integer rollOne = null
	Integer rollTwo = null
	Frame nextFrame
	
	boolean hasRollsLeft(){
		if (isStrike() || isSpare()){
			return false
		}
		if (!rollOne || !rollTwo){
			return true
		}
		return false
	}
	
	Frame addRoll(int pins){
		if (rollOne == null){
			rollOne = pins
		}
		else {
			rollTwo = pins
		}
		return this
	}
	
	boolean isStrike(){
		return (rollOne == 10)
	}
	
	boolean isSpare(){
		return ((rollOne && rollTwo) && (rollOne + rollTwo == 10))
	}
	
	int getFrameScore(){
		int one = rollOne ? rollOne : 0
		int two = rollTwo ? rollTwo : 0

		return one + two + getBonus()
	}
	
	private int getBonus(){
		int bonus = 0
		if (isSpare() && nextFrame){
			bonus += nextFrame.rollOne ? nextFrame.rollOne : 0
		}
		else if (isStrike() && nextFrame){
			if (nextFrame.isStrike()){
				bonus += 10 // one
				if (nextFrame?.nextFrame?.isStrike()){
					bonus += 10 // two
				}
				else {
					bonus += nextFrame?.nextFrame?.rollOne ? nextFrame.nextFrame.rollOne : 0
				}
			}
			else{
				bonus += nextFrame.rollOne ? nextFrame.rollOne : 0
				bonus += nextFrame.rollTwo ? nextFrame.rollTwo : 0
			}
		}
		return bonus
	}
}
