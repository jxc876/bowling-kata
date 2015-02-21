package com.bowl

import spock.lang.*

class FrameSpec extends Specification {
	Frame frame = new Frame()

	def "verify normal roll"(){
		when:
		frame.addRoll 1
		frame.addRoll 3

		then:
		!frame.isSpare()
		!frame.isStrike()
		frame.frameScore == 4
	}

	def "verify that spares are detected"(){
		when:
		frame.addRoll 3
		frame.addRoll 7

		then:
		frame.isSpare()
		!frame.isStrike()
	}

	def "verify that strikes are detected"(){
		when:
		frame.addRoll 10

		then:
		frame.isStrike()
		!frame.isSpare()
	}
	
	def "verify that bonus is calculated on strikes"(){
		when:
		frame.addRoll(10)
		frame.nextFrame = new Frame(rollOne: 4, rollTwo: 2)
		
		then:
		frame.frameScore == 16
	}

	def "verify that bonus is calculated on spares"(){
		when:
		frame.addRoll(3)
		frame.addRoll(7)
		frame.nextFrame = new Frame(rollOne: 4, rollTwo: 2)

		then:
		frame.frameScore == 14
	}
	
	def "verify that no rolls are left after a strike"(){
		when:
		frame.addRoll(10)
		
		then:
		!frame.hasRollsLeft()
	}
}
