package com.hazelcast.demo.worldcup;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UtilHashtagTest {

	private static final String INPUT_TEXT = UtilHashtagTest.class.getSimpleName();
	
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

	@Test(expected=NullPointerException.class)
	public void null_string() throws Exception {
		String input = null;
		Util.makeHashtag(input);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void empty_string() throws Exception {
		String input = "";
		Util.makeHashtag(input);
	}

	@Test
	public void no_hashtag() throws Exception {
		String input = INPUT_TEXT;
		String output = Util.makeHashtag(input);
		assertThat("Size", output.length(), equalTo(input.length() + 1));
		assertThat("Content", output, equalTo("#" + input));
	}

	@Test
	public void one_hashtag() throws Exception {
		String input = "#" + INPUT_TEXT;
		String output = Util.makeHashtag(input);
		assertThat("Size", output.length(), equalTo(input.length()));
		assertThat("Content", output, equalTo(input));
	}

	@Test
	public void two_hashtags() throws Exception {
		String input = "#" + "#" + INPUT_TEXT;
		String output = Util.makeHashtag(input);
		assertThat("Size", output.length(), equalTo(input.length() - 1));
		assertThat("Content", output, equalTo(input.substring(1)));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void only_hashtag() throws Exception {
		String input = "#";
		Util.makeHashtag(input);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void only_hashtags() throws Exception {
		String input = "##";
		Util.makeHashtag(input);
	}

	@Test(expected=Exception.class)
	public void embedded_hashtag() throws Exception {
		String input = INPUT_TEXT + "#" + INPUT_TEXT;
		Util.makeHashtag(input);
		this.expectedException.expectMessage(containsString("Only letters allowed,"));
	}

	@Test(expected=Exception.class)
	public void not_letters() throws Exception {
		String input = "12345";
		Util.makeHashtag(input);
		this.expectedException.expectMessage(containsString("Only letters allowed,"));
	}

}
