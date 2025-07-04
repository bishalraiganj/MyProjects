package Adhikary.X.parser;

import java.util.regex.Pattern;

public class LogParser {

	// The pattern matches or finds in Log's of formats "YYYY-MM-DD HH:MM:SS LEVEL [Source] - Message" UNTESTED
	private static final Pattern  pattern = Pattern.compile("(([1-9][0-9]{3})-([01][0-9])-([0123][0-9])) (([012][0-9]):([0-6][0-9]):([0-6][0-9])) ([A-Za-z]{3,40}) (\\[[a-zA-Z0-9]{3,300}\\]) - (([a-zA-Z0-9]{1,40}){1}([ ]{1}[a-zA-Z0-9]{1,40})*)");




}
