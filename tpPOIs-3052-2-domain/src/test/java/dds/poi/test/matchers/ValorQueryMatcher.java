package dds.poi.test.matchers;

import org.mockito.ArgumentMatcher;

public class ValorQueryMatcher extends ArgumentMatcher<String> {

	private String genericQueryValue;
	
	public ValorQueryMatcher(String genericQuery) {
		this.genericQueryValue = genericQuery;
	}
	
	@Override
	public boolean matches(Object argument) {
		return this.genericQueryValue.equals(argument);
	}

}