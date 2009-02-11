package com.knitml.renderer.visitor.model

import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.text.StringStartsWith.startsWith
import static org.junit.Assert.assertThat

import org.junit.Test
import org.junit.Before
import org.junit.runner.JUnitCore

import com.knitml.core.model.header.Yarn
import com.knitml.core.model.directions.block.Row

import test.support.AbstractRenderingContextTests

class RowVisitorTests extends AbstractRenderingContextTests {

	@Before
	void addYarns() {
		Yarn yarnOne = new Yarn('yarn1', 'A')
		renderingContext.patternRepository.addYarn yarnOne
	}
	
	@Test
	void rowOne() {
		processXml '''
			<row xmlns="http://www.knitml.com/schema/pattern" number="1">
				<knit>1</knit>
			</row>''', Row
		assertThat output, startsWith ('Row 1: k1')
	}

	@Test
	void rowOneThreeFive() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1 3 5"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Rows 1,3,5: k1')
	}
	
	@Test
	void nextRow() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" ><knit>1</knit></row>', Row
		assertThat output, startsWith ('Next row: k1')
	}
	
	@Test
	void roundOne() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1" type="round"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Round 1: k1')
	}

	@Test
	void roundOneThreeFive() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1 3 5" type="round"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Rounds 1,3,5: k1')
	}
	
	@Test
	void nextRound() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" type="round"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Next round: k1')
	}
	
	@Test 
	void rowOneWithA() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1" yarn-ref="yarn1"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Row 1 (A): k1')
	}

	@Test
	void rowOneThreeFiveWithA() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1 3 5" yarn-ref="yarn1"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Rows 1,3,5 (A): k1')
	}
	
	@Test
	void nextRowWithA() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" yarn-ref="yarn1"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Next row (A): k1')
	}
	
	@Test
	void roundOneWithA() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1" type="round" yarn-ref="yarn1"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Round 1 (A): k1')
	}

	@Test
	void roundOneThreeFiveWithA() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" number="1 3 5" type="round" yarn-ref="yarn1"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Rounds 1,3,5 (A): k1')
	}
	
	@Test
	void nextRoundWithA() {
		processXml '<row xmlns="http://www.knitml.com/schema/pattern" type="round" yarn-ref="yarn1"><knit>1</knit></row>', Row
		assertThat output, startsWith ('Next round (A): k1')
	}
	
	@Test
	void rowWithInformation() {
		processXml '''<row xmlns="http://www.knitml.com/schema/pattern" number="1">
						<information>
							<message label="whew!"/>
						</information>
						<knit>1</knit>
					</row>''', Row
		assertThat output, startsWith ('Row 1 [whew!]: k1')
	}
	
	@Test
	void rowWithSide() {
		processXml '''<row xmlns="http://www.knitml.com/schema/pattern" number="1" side="right">
						<knit>1</knit>
					</row>''', Row
		assertThat output, startsWith ('Row 1 [RS]: k1')
	}
	@Test
	void rowWithInformationAndSide() {
		processXml '''<row xmlns="http://www.knitml.com/schema/pattern" number="1" side="right">
						<information><message label="whew!"/></information>
						<knit>1</knit>
					</row>''', Row
		assertThat output, startsWith ('Row 1 [whew!,RS]: k1')
	}
	
	static void main(args) {
		JUnitCore.main(RowVisitorTests.name)
	}
	
}
 