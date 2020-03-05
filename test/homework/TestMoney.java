package homework;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestMoney {
	private int input;
	private boolean expected;
	private Money mon = null;
	
	public TestMoney(int input, boolean expected) {
		this.input = input;
		this.expected = expected;
	}
	@Before
	public void setUp(){
		mon = new Money();
	}
	@Parameters
	public static Collection<Object[]> getData(){
		return Arrays.asList(new Object[][] {
			{-1,false},
			{0,true},
			{1,true},
			{4,false},
			{9,true},
			{48,false},
			{94,false},
			{100,false}
		});
	}
	
	@Test
	public void testIsEnough(){
		assertEquals(this.expected, mon.isEnough(input));
	}

	@Test
	public void testHamcrest(){
		assertThat(mon.isEnough(input), allOf(equalTo(this.expected)));
	}


}
