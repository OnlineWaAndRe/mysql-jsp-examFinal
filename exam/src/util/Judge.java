package util;

public class Judge {

	public static boolean isTrue(String userName, String userPassword) {
		if(userName.contains("|") || userName.contains("or")|| userName.contains("true")) {
			return false;
		}
		if(userPassword.contains("|") || userPassword.contains("or")||userPassword.contains("true")) {
			return false;
		}
		return true;
	}
}
