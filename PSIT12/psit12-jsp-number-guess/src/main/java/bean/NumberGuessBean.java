/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package bean;

import java.util.Random;

public class NumberGuessBean {

	private static final int NOT_VALID_INPUT = -1;
	private int answer;
	private boolean success;
	private String hint;
	private int numGuesses;
	private Random ramdon;

	public NumberGuessBean() {
		this.ramdon = new Random();
		this.reset();
	}

	public String getHint() {
		return "" + this.hint;
	}

	public int getNumGuesses() {
		return this.numGuesses;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void reset() {
		this.answer = Math.abs(this.ramdon.nextInt() % 100) + 1;
		this.success = false;
		this.numGuesses = 0;
	}

	public void setGuess(String guess) {
		this.numGuesses++;

		int inputValue;
		try {
			inputValue = Integer.parseInt(guess);
		} catch (NumberFormatException e) {
			inputValue = NOT_VALID_INPUT;
		}

		if (inputValue == this.answer) {
			this.success = true;
		} else if (inputValue == NOT_VALID_INPUT) {
			this.hint = "a number next time";
		} else if (inputValue < this.answer) {
			this.hint = "higher";
		} else {
			this.hint = "lower";
		}
	}
}
