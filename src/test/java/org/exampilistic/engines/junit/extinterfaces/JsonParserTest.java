package org.exampilistic.engines.junit.extinterfaces;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class JsonParserTest {
	@Test
	public void parsesJsonIntoMap() {
		String json = "{ \"foo\": 5, \"bar\": \"test\", \"baz\": { \"x\": true }}";
		try(StringReader reader = new StringReader(json)) {
			Map<String, Object> parsed = new JsonParser().fromJson(reader);

			assertThat(parsed).isNotNull();
			assertThat(parsed.get("foo")).isEqualTo(5.0);
			assertThat(parsed.get("bar")).isEqualTo("test");

			assertThat(parsed.get("baz")).isNotNull();
			Map<String, Object> baz = (Map<String, Object>) parsed.get("baz");
			assertThat(baz.get("x")).isEqualTo(true);
		}
	}
}