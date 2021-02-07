package TestFunc;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "rnbQ2Q1/pppp3p/6k1/8/1P6/8/Pn1pPKPP/RNB2BNR/BPQRppq/";
		System.out.print(getFormatedString(test));
	}

	public static String getFormatedString(String str) {
		String result = "";
		String temp = "";
		String [] arr = str.split("/");
		for (String cur : arr) {
			for (int i = 0; i < cur.length(); i++) {
				

				char c = cur.charAt(i);
				if (c >= '0' && c <= '9') {
					for (int j = 0; j < ((int)(c - '0')) ; j ++) {
						temp += "l";
					}
				}
				else
					temp += c;
				
				}
			result += temp +  "/";
			temp = "";
			}
		
		return result;
	}
}
