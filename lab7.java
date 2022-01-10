import java.math.BigInteger;
import java.util.HashMap;
public class lab7 {
    public static BigInteger calculateLength(String cable) {
        int iNumCount = 0, a = 0, b = 0;
        char[] strArray = cable.toCharArray();

        try {
            for(int i = 0; i < strArray.length; i++) {
                switch(strArray[i]) {
                    case '(': a++; break;
                    case ')': b++; break;
                    case '-': break;
                    case '_': break;
                    case '=': break;
                    default: throw new NodeException(/*"Err: Another character detected"*/);
                }
            }
            if(a != b) throw new BracketException(/*"Unequal number of parentheses"*/);

            HashMap<Integer, Boolean> colHashLeftBracket = new HashMap<>();
            HashMap<Integer, Boolean> colHashRightBracket = new HashMap<>();

            for(int i = 0; i < strArray.length; i++) {
                switch(strArray[i]) {
                    case '-': iNumCount++; break;
                    case '_': iNumCount += 2; break;
                    case '=': iNumCount += 3; break;
                    case '(': {
                        if(!colHashLeftBracket.containsKey(i)
                        || !colHashLeftBracket.get(i)) {
                            colHashLeftBracket.put(i, true);
                        }
                        break;
                    }
                    case ')': {
                        if(colHashRightBracket.containsKey(i)
                        && colHashRightBracket.get(i)) {
                            colHashRightBracket.put(i, false);
                            break;
                        }

                        for(int j = i; j >= 0; j--) {
                            if (strArray[j] == '('
                            && colHashLeftBracket.get(j)) {
                                colHashRightBracket.put(i, true);

                                i = j;
                                colHashLeftBracket.put(i, false);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (BracketException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        catch (NodeException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        catch (IllegalStateException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        finally {
            return BigInteger.valueOf(iNumCount);
        }
    }
}
class BracketException extends Exception {}
class NodeException extends Exception {}