package selrunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.remote.server.handler.BySelector;

import selrunner.exceptions.ByConverterException;
import fitnesse.slim.Converter;

public class ByConverter implements Converter {

	private static final Pattern PATTERN = Pattern.compile("^(.+)\\:\\s*(.+)");
	private static final BySelector BY_SELECTOR = new BySelector();

	@Override
	public Object fromString(String byString) {
		Matcher matcher = PATTERN.matcher(byString);
		if (!matcher.matches()) {
			throw new ByConverterException(byString);
		}
		String method = matcher.group(1);
		String selector = matcher.group(2);
		return BY_SELECTOR.pickFrom(method, selector);
	}

	@Override
	public String toString(Object by) {
		return by.toString();
	}

}
