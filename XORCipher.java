package info2;

public class XORCipher {
    
    //
    // This table contains 64 possible chars that are
    // used as alphabet for this encryption exercise.
    // We can use 6 bits (e.g. the most right 6 bits of an
    // int value) to uniquely encode a character.
    //
    public static char[] CHAR_TABLE = {
        ' ', '\n', ',', '.', '\'', '-', ':', ';', '?', '(', ')', '!',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    // ----------------------------------------------------------------
    // Exercise 2 (a)
    // ----------------------------------------------------------------

    // TODO: implement valueToChar
    public static char valueToChar(int val) {
    	if (val >= 0|| val<=63) {
    		return CHAR_TABLE[val];
    	}
    	else {
    		char ret = ' ';
    		return ret;
    	}
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (b) 
    // ----------------------------------------------------------------
    
    // TODO: implement charToValue
    public static int charToValue(char c) {
    	int res = 0;
    	for (int idx =0; idx < CHAR_TABLE.length; idx++) {
    		if (c==CHAR_TABLE[idx]) {
    			res = idx;
    			break;
    		}
    	}
    	return res;
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    
    // TODO: implement stringToValue
    public static int[] stringToValue(String s) {
    	if (s ==null) {
    		int[] ret = new int[0];
    		return ret;
    	}
    	else {
    		int len = s.length();
    		int[] numbs = new int[len];
    		for ( int i=0; i<len;i++) {
    			numbs[i] = charToValue(s.charAt(i));
    		}
    		return numbs;
    	}
    	
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    
    // TODO: implement valuesToString
    public static String valuesToString(int[] int_code) {
    	
    	String out = "";
    	if (int_code!=null){
    	
    	for (int idx =0; idx<int_code.length;idx++) {
    		out += valueToChar(int_code[idx]);
    	}
    	
    	}
    	return out;
    	
    	
    	
    }
    
    public static int power(int b, int ex) {
    	int res = 1;
    	for (int i =1;i<=ex;i++) {
    		res *= b;
    	}
    	return res;
    }
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------

    // TODO: implement encryptDecrypt for type int[]
    
    public static int[] decToBinary(int[] numb) {
    	int[] out = new int[numb.length*6];
    	for (int n = 0; n<numb.length;n++) {
    		int dec = numb[n];
    		for (int i =5;i>=0;i--) {
    			out[6*n+i] =((dec/power(2,i))*3)/((dec/power(2,i))*3-1);
    			dec = dec%power(2,i);
    		}
    	}
    	return out;
    	
    }
    
    public static int[] binaryToDec(int[] bin) {
    	int[] out = new int[bin.length/6];
    	for (int idx_n =0; idx_n<(bin.length/6);idx_n++) {
    		for (int i = 0; i<6;i++) {
    			out[idx_n] += power(2,i)*bin[idx_n*6+i];    		
    			}
    		}
    	return out;
		}
    
    public static int[] encryptDecrypt(int[] msg, int[] key) {
    	if (msg==null || key == null) {
    		return msg;
    	}
    	else {
    	int[] binary_msg = decToBinary(msg);
    	int[] binary_key = decToBinary(key);
    	int[] bin_crypt = new int[binary_msg.length];
    	int key_len = binary_key.length;
    	int[] out = new int[msg.length];
    	for (int idx =0; idx<binary_msg.length;idx++) {
    		bin_crypt[idx] = (binary_msg[idx]+binary_key[idx%key_len])%2;
    		
    			}
    	out = binaryToDec(bin_crypt);
    	return out;
    		
    	}
    	}
    	
    	
    

    
    // ----------------------------------------------------------------
    // Exercise 2 (f)
    // ----------------------------------------------------------------
    
    // TODO: implement encryptDecryot for type String
    public static String encryptDecrypt(String msg, String key) {
    	if (msg==null || key==null) {
    		return null;
    	}
    	else {
    		int[] dec_msg = stringToValue(msg);
    		int[] dec_key = stringToValue(key);
    		int[] dec_crypt_msg = encryptDecrypt(dec_msg,dec_key);
    		String crypt_msg = valuesToString(dec_crypt_msg);
    		return crypt_msg;
    		
    	}
    	
    }

    public static void print_array(int[] array) {
    	for (int i=0;i<array.length;i++) {
       	 
            System.out.print(array[i]);
            System.out.print('\t');
            }
            
            System.out.print('\n');
    }
    public static void print_array_char(char[] array) {
    	for (int i=0;i<array.length;i++) {
       	 
            System.out.print(array[i]);
            System.out.print('\t');
            }
            
            System.out.print('\n');
    }
    
    
    // ----------------------------------------------------------------
    
    public static void main(String[] args) {
        String msg = "Dieser Text ist sehr geheim...";
        String key = "keyword";
//        
         String cipher = encryptDecrypt(msg, key);
         String decrypted = encryptDecrypt(cipher, key);
        
        //
        // Wenn alles richtig implementiert wurde, sollte
        // der String decrypted wieder genau dem String 
        // msg entsprechen, waehrend der String cipher fuer
        // uns nicht lesbar ist.
        //
        
		System.out.println(msg);
		System.out.println(cipher);
		System.out.println(decrypted);
		
		System.out.println();
		
		
        
        
    }
}