/**
 * 
 */
package com.knitml.core.model.directions.inline

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat
import static test.support.JiBXTestUtils.unmarshalXml
import static test.support.JiBXTestUtils.marshalXmlAndCompare

import org.custommonkey.xmlunit.XMLUnit

import org.junit.Test
import org.junit.Ignore
import org.junit.BeforeClass
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.JUnitCore
import org.junit.runner.RunWith

import com.knitml.core.model.Pattern
import com.knitml.core.common.Side
import com.knitml.core.common.Wise
import com.knitml.core.common.LoopToWork
import com.knitml.core.common.YarnPosition

@RunWith(JUnit4ClassRunner)
class DecreaseTests {
	@BeforeClass
	static void setUp() {
		XMLUnit.ignoreWhitespace = true
	}
	
	@Test
	void decrease() {
		def xml = '''
			<pattern xmlns="http://www.knitml.com/schema/pattern">
			  <directions>
					<row>
						<decrease yarn-ref="yarn0" type="ssk"/>
						<decrease>5</decrease>
					</row>
				  </directions>
			</pattern>'''
		Pattern pattern = unmarshalXml(xml)
		def element = pattern.directions.operations[0].operations[0]
		element.with {
			assertThat(it instanceof Decrease, is (true))
			assertThat yarnIdRef, is ('yarn0')
			assertThat type, is ('ssk')
			assertThat numberOfTimes, is (null)
		}
		element = pattern.directions.operations[0].operations[1]
		element.with {
			assertThat(it instanceof Decrease, is (true))
			assertThat yarnIdRef, is (null)
			assertThat type, is (null)
			assertThat numberOfTimes, is (5)
		}
		marshalXmlAndCompare(pattern,xml)
	}
	
	@Test
	void doubleDecrease() {
		def xml = '''
			<pattern xmlns="http://www.knitml.com/schema/pattern">
			  <directions>
					<row>
						<double-decrease yarn-ref="yarn0" type="cdd"/>
						<double-decrease>5</double-decrease>
					</row>
				  </directions>
			</pattern>'''
		Pattern pattern = unmarshalXml(xml)
		def element = pattern.directions.operations[0].operations[0]
		element.with {
			assertThat(it instanceof DoubleDecrease, is (true))
			assertThat yarnIdRef, is ('yarn0')
			assertThat type, is ('cdd')
			assertThat numberOfTimes, is (null)
		}
		element = pattern.directions.operations[0].operations[1]
		element.with {
			assertThat(it instanceof DoubleDecrease, is (true))
			assertThat yarnIdRef, is (null)
			assertThat type, is (null)
			assertThat numberOfTimes, is (5)
		}
		marshalXmlAndCompare(pattern,xml)
	}
	@Test
	void bindOffTen() {
		def xml = '''
				<pattern xmlns="http://www.knitml.com/schema/pattern">
				  <directions>
				  	<row>
				  		<bind-off yarn-ref="yarn1">10</bind-off>
				  	</row>
				  </directions>
				</pattern>'''
		Pattern pattern = unmarshalXml(xml)
		def element = pattern.directions.operations[0].operations[0]
		element.with {
			assertThat yarnIdRef, is ('yarn1')
			assertThat numberOfStitches, is (10)
		}
		marshalXmlAndCompare(pattern,xml)
	}

	@Test
	void bindOffAll() {
		def xml = '''
				<pattern xmlns="http://www.knitml.com/schema/pattern">
				  <directions>
				  	<row>
				  		<bind-off-all type="purlwise" yarn-ref="yarn1"/>
				  	</row>
				  </directions>
				</pattern>'''
		Pattern pattern = unmarshalXml(xml)
		def element = pattern.directions.operations[0].operations[0]
		element.with {
			assertThat yarnIdRef, is ('yarn1')
			assertThat type, is (Wise.PURLWISE)
			assertThat fastenOffLastStitch, is (true)
		}
		marshalXmlAndCompare(pattern,xml)
	}
	
	@Test
	void bindOffAllWithNoFastenOff() {
		def xml = '''
				<pattern xmlns="http://www.knitml.com/schema/pattern">
				  <directions>
				  	<row>
				  		<bind-off-all type="purlwise" yarn-ref="yarn1"
                                      fasten-off-last-stitch="false"/>
				  	</row>
				  </directions>
				</pattern>'''
		Pattern pattern = unmarshalXml(xml)
		def element = pattern.directions.operations[0].operations[0]
		element.with {
			assertThat yarnIdRef, is ('yarn1')
			assertThat type, is (Wise.PURLWISE)
			assertThat fastenOffLastStitch, is (false)
		}
		marshalXmlAndCompare(pattern,xml)
	}
	
	static void main(args) {
		JUnitCore.main(DecreaseTests.name)
	}
	
}
