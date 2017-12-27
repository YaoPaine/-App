package com.yao.lib_common.sign;

public class SaltHandle {

	/**
	 *
	 * 
	 * @param paramString
	 * @return
	 */
	public String handle(String paramString) {
		String[] paramStr = paramString.split(" ");
		StringBuilder localStringBuilder = new StringBuilder();
		int i = 0;
		while (i < paramStr.length) {
			localStringBuilder.append((char) (Integer.parseInt(paramStr[i]) / 3));
			i += 1;
		}
		return localStringBuilder.toString();
	}

}
