package com.knitml.el

import static com.knitml.el.KelUtils.toXml
import static org.custommonkey.xmlunit.XMLAssert.*

import org.custommonkey.xmlunit.XMLUnit
import org.junit.BeforeClass
import org.junit.Test

class LanguageEHTests {
	
	@BeforeClass
	static void setUp() {
		XMLUnit.ignoreWhitespace = true
		XMLUnit.ignoreComments = true
	}
	
	@Test
	void fromHolder() {
		String actual = toXml ("fromHolder 'sh1' { p2, k to end }")
		String expected = '''
			<from-stitch-holder ref="sh1">
				<purl>2</purl>
				<repeat until="end">
					<knit/>
				</repeat>
			</from-stitch-holder>'''
		assertXMLEqual expected, actual
	}
}