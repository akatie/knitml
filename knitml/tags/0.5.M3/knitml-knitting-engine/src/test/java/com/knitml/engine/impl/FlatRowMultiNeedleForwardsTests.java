/**
 * 
 */
package com.knitml.engine.impl;


/**
 * @author Jonathan Whitall (fiddlerpianist@gmail.com)
 * 
 */
public class FlatRowMultiNeedleForwardsTests extends FlatRowMultiNeedleTests {

	@Override
	protected void onSetUp() throws Exception {
		knitter.startNewRow();
		knit(40);
		knitter.resetRowNumber();
		knitter.startNewRow();
	}
	
}
