package fr.pag.rfid.cypher;

import java.util.ArrayList;
import java.util.Collections;

public class Decrypter {
	
	public static ArrayList<String> moneyRead(String Money){
		ArrayList<String> DecrypteList = new ArrayList<String>();
			ArrayList<String> TheAlphabet = new ArrayList<String>();
			ArrayList<String> TheAlphabetPair = new ArrayList<String>();
			ArrayList<String> TheAlphabetImpair = new ArrayList<String>();
			ArrayList<String> TheAlphabetMagique = new ArrayList<String>();
			ArrayList<String> TheAlphabetFantasmabolique = new ArrayList<String>();
			ArrayList<String> TheAlphabetMathematique = new ArrayList<String>();
			String[] AlphabetWooow = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
			String[] AlphabetImPair = {"b","d","f","h","j","l","n","p","r","t","v","x","z"};
			String[] AlphabetPair = {"a","c","e","g","i","k","m","o","q","s","u","w","y"};
			String[] AlphabetMagique = {"a","j","p","g","m","s","r","f","k","d","t","q","z"};
			String[] AlphabetFantasmabolique = {"z","y","w","a","d","f","g","p","s","x","h","l","r"};
			String[] AlphabetMathematique = {"0","1","2","3","4","5","6","7","8","9"};
			Collections.addAll(TheAlphabet, AlphabetWooow);
			Collections.addAll(TheAlphabetPair, AlphabetPair);
			Collections.addAll(TheAlphabetImpair, AlphabetImPair);
			Collections.addAll(TheAlphabetMagique, AlphabetMagique);
			Collections.addAll(TheAlphabetFantasmabolique, AlphabetFantasmabolique);
			Collections.addAll(TheAlphabetMathematique, AlphabetMathematique);
		    
		    String TheMoins = "";
		    for(int k = 0; TheAlphabetMathematique.contains(String.valueOf(Money.charAt(k))); k++){
		    	TheMoins = TheMoins + Money.charAt(k);
		    	if(!TheAlphabetMathematique.contains(Money.charAt(k+1))){
		    		int NombreDeNombre = k+1;
		    		int MinusThat = Integer.parseInt(TheMoins);
				    String PairouImpair = String.valueOf(Money.charAt(1+NombreDeNombre));
				    int NombreCaracteres = 0;
				    if(TheAlphabetPair.indexOf(PairouImpair) != -1){
				    	NombreCaracteres = TheAlphabet.indexOf(String.valueOf(Money.charAt(0+NombreDeNombre))) - TheAlphabet.indexOf(String.valueOf(Money.charAt(2+NombreDeNombre))) + MinusThat;
				    }
				    else if(TheAlphabetImpair.indexOf(PairouImpair) != -1){
				    	NombreCaracteres = TheAlphabet.indexOf(String.valueOf(Money.charAt(0+NombreDeNombre))) + TheAlphabet.indexOf(String.valueOf(Money.charAt(2+NombreDeNombre))) + MinusThat;
				    }
			    	int HowMuch = TheAlphabetMagique.indexOf(String.valueOf(Money.charAt(3+NombreDeNombre)))*42 + TheAlphabetFantasmabolique.indexOf(String.valueOf(Money.charAt(4+NombreDeNombre)))*42;
			    	HowMuch = HowMuch + 4 + NombreDeNombre;
			    	int NombreCaracteresRestant = NombreCaracteres;
				    for(int z = 1 + HowMuch; Money.length() > z + 2 && NombreCaracteresRestant > 0; z = z + 3){
				    	if(TheAlphabetPair.indexOf(String.valueOf(Money.charAt(z))) != -1){
				    		if(TheAlphabetMathematique.contains(String.valueOf(Money.charAt(z + 1)))){
				    			String MoinsCombien = "";
				    			for(int w = 1; Money.length() > z + w && TheAlphabetMathematique.indexOf(String.valueOf(Money.charAt(w + z))) != -1; w++){
				    				MoinsCombien = MoinsCombien + String.valueOf(Money.charAt(z + w));
				    				if(TheAlphabetMathematique.indexOf(String.valueOf(Money.charAt(w + z + 1))) == -1){
				    					int LeCalcul2Reel = -TheAlphabet.indexOf(String.valueOf(Money.charAt(z + 2 + w))) - Integer.parseInt(MoinsCombien);
				    					int WichNumber = LeCalcul2Reel + TheAlphabetPair.indexOf(String.valueOf(Money.charAt(z))) + TheAlphabet.indexOf(String.valueOf(Money.charAt(1 + z + w)));
				    					DecrypteList.add(String.valueOf(WichNumber));
				    					z = z + w;
				    				}
				    			}
				    		}
				    		else if(!TheAlphabetMathematique.contains(String.valueOf(Money.charAt(z + 1)))){
					    		int LeCalcul2Reel = -TheAlphabet.indexOf(String.valueOf(Money.charAt(z + 2)));
				    			int WichNumber = LeCalcul2Reel + TheAlphabet.indexOf(String.valueOf(Money.charAt(z + 1))) + TheAlphabetPair.indexOf(String.valueOf(Money.charAt(z)));
				    			DecrypteList.add(String.valueOf(WichNumber));
				    		}
				    	}
				    	else if(TheAlphabetImpair.indexOf(String.valueOf(Money.charAt(z))) != -1){
				    		int WichNumber = TheAlphabet.indexOf(String.valueOf(Money.charAt(z + 2))) + TheAlphabet.indexOf(String.valueOf(Money.charAt(z + 1)))+ TheAlphabetImpair.indexOf(String.valueOf(Money.charAt(z)));
				    		DecrypteList.add(String.valueOf(WichNumber));
				    	}
				    	NombreCaracteresRestant = NombreCaracteresRestant - 1;
				    }
		    	}
		    }
		return DecrypteList;	
	}
	
	public static String decryptagePass(ArrayList<String> PartiellementDecrypte){
		String Decrypte = "";
		ArrayList<String> TheAlphabetAndNumerique = new ArrayList<String>();
		String[] AlphabetAndNumerique = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9",
				" ","&","_","Ã©","\"","#","'","{","(","[","-","|","Ã¨","'\'","Ã§","ï¿½","!","$","%",")","*","+",",","-",".","/",":",";","<","=",">","?","@","A","B","C","D","E","F","G","H",
				"I","J","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","]","^","`","}","~","â‚¬","Æ’","â€¦","â€ ","â€¡","â€°","Å ","â€¹","Å’","â€˜","â€™","â€¢","â€”","â„¢","Å½","â€º","Å“","Å¸","Â¡","Â¢",
				"Â£","Â¤","Â¥","Â¦","Â§","Â¨","Â©","Âª","Â«","Â¬","Â®","Â¯","Â°","Â±","Â²","Â³","","Â´","Âµ","Â¶","Â·","Â¸","Â¹","Âº","Â»","Â¼","Â½","Â¾","Â¿","Ã€","Ã","Ã‚","Ãƒ","Ã„","Ã…","Ã†","Ã‡","Ãˆ","Ã‰","ÃŠ","Ã‹","ÃŒ","Ã",
				"ÃŽ","Ã","Ã","Ã‘","Ã’","Ã“","Ã”","Ã•","Ã–","Ã—","Ã˜","Ã™","Ãš","Ã›","Ãœ","Ã","Ãž","ÃŸ","Ã ","Ã¡","Ã¢","Ã£","Ã¤","Ã¥","Ã¦","Ã§","Ã¨","Ã©","Ãª","Ã«","Ã¬","Ã­","Ã®","Ã¯","Ã°","Ã±","Ã²","Ã³","Ã´","Ãµ","Ã¶","Ã·",
				"Ã¸","Ã¹","Ãº","Ã»","Ã¼","Ã½","Ã¾","Ã¿","Ä€","Ä","Ä‚","Ä„","Ä…","Ä†","Ä‡","Äˆ","Ä‰","ÄŠ","Ä‹","ÄŒ","Ä","ÄŽ","Ä","Ä","Ä‘","Ä’","Ä“","Ä”","Ä•","Ä–","Ä—","Ä˜","Ä™","Äš","Ä›","Äœ","Ä","Äž","ÄŸ","Ä ","Ä¡","Ä¢",
				"Ä£","Ä¤","Ä¥","Ä¦","Ä§","Ä¨","Ä©","Äª","Ä«","Ä¬","Ä­","Ä®","Ä¯","Ä°","Ä±","Ä²","Ä³","Ä´","Äµ","Ä¶","Ä·","Ä¸","Ä¹","Äº","Ä»","Ä¼","Ä½","Ä¾","Ä¿","Å€","Å","Å‚","Åƒ","Å„","Å…","Å†","Å‡","Åˆ","Å‰","ÅŠ","Å‹","ÅŒ",
				"Å","ÅŽ","Å","Å","Å‘","Å’","Å“","Å”","Å•","Å–","Å—","Å˜","Å™","Åš","Å›","Åœ","Å","Åž","ÅŸ","Å ","Å¡","Å¢","Å£","Å¤","Å¥","Å¦","Å§","Å¨","Å©","Åª","Å«","Å¬","Å­","Å®","Å¯","Å°","Å±","Å²","Å³","Å´","Åµ","Å¶",
				"Å·","Å¸","Å¹","Åº","Å»","Å¼","Å½","Å¾","Å¿","Æ€","Æ‰","ÆŠ","Æ‹","ÆŒ","Æ","ÆŽ","Æ","Æ","Æ‘","Æ’","Æ“","Æ”","Æ•","Æ–","Æ—","Æ˜","Æ™","Æš","Æ›","Æœ","Æ","Æž","ÆŸ","Æ ","Æ¡","Æ¢","Æ£","Æ¤","Æ¥","Æ¦","Æ§","Æ¨",
				"Æ©","Æª","Æ«","Æ¬","Æ­","Æ®","Æ¯","Æ°","Æ±","Æ²","Æ³","Æ´","Æµ","Æ¶","Æ·","Æ¸","Æ¹","Æº","Æ»","Æ¼","Æ½","Æ¾","Æ¿","Ç€","Ç","Ç‚","Çƒ","Ç„","Ç…","Ç†","Ç‡","Çˆ","Ç‰","ÇŠ","Ç‹","ÇŒ","Ç","ÇŽ","Ç","Ç","Ç‘","Ç’",
				"Ç“","Ç”","Ç•","Ç–","Ç—","Ç˜","Ç™","Çš","Ç›","Çœ","Ç","Çž","ÇŸ","Ç ","Ç¡","Ç¢","Ç£","Ç¤","Ç¥","Ç¦","Ç§","Ç¨","Ç©","Çª","Ç«","Ç¬","Ç­","Ç®","Ç¯","Ç°","Ç±","Ç²","Ç³","Ç´","Çµ","Ç¶","Ç·","Ç¸","Ç¹","Çº","Ç»","Ç¼",
				"Ç½","Ç¾","Ç¿","È€","È","È‚","Èƒ","È„","È…","È†","È‡","Èˆ","È‰","ÈŠ","È‹","ÈŒ","È","ÈŽ","È","È","È‘","È’","È“","È”","È•","È–","È—","È˜","È™","Èš","È›","Èœ","È","Èž","ÈŸ","È ","È¡","È¢","È£","È¤","È¥","È¦",
				"È§","È¨","È©","Èª","È«","È¬","È­","È®","È¯","È°","È±","È²","È³","È´","Èµ","È¶","È·","È¸","È¹","Èº","È»","È¼","È½","È¾","È¿","É€","É","É‚","Éƒ","É„","É…","É†","É‡","Éˆ","É‰","ÉŠ","É‹","ÉŒ","É","ÉŽ","É","É",
				"É‘","É’","É“","É”","É•","É–","É—","É˜","É™","Éš","É›","Éœ","É","Éž","ÉŸ","É ","É¡","É¢","É£","É¤","É¥","É¦","É§","É¨","É©","Éª","É«","É¬","É­","É®","É¯","É°","É±","É²","É³","É´","Éµ","É¶","É·","É¸","É¹","Éº",
				"É»","É¼","É½","É¾","É¿","Ê€","Ê","Ê‚","Êƒ","Ê„","Ê…","Ê†","Ê‡","Êˆ","Ê‰","ÊŠ","Ê‹","ÊŒ","Ê","ÊŽ","Ê","Ê","Ê‘","Ê’","Ê“","Ê”","Ê•","Ê–","Ê—","Ê˜","Ê™","Êš","Ê›","Êœ","Ê","Êž","ÊŸ","Ê ","Ê¡","Ê¢","Ê£","Ê¤",
				"Ê¥","Ê¦","Ê§","Ê¨","Ê©","Êª","Ê«","Ê¬","Ê­","Ê®","Ê¯","Ê°","Ê±","Ê²","Ê³","Ê´","Êµ","Ê¶","Ê·","Ê¸","Ê¹","Êº","Ê»","Ê¼","Ê½","Ê¾","Ê¿","Ë€","Ë","Ë‚","Ëƒ","Ë„","Ë…","Ë†","Ë‡","Ëˆ","Ë‰","ËŠ","Ë‹","ËŒ","Ë","ËŽ",
				"Ë","Ë","Ë‘","Ë’","Ë“","Ë”","Ë•","Ë–","Ë—","Ë˜","Ë™","Ëš","Ë›","Ë","Ëž","ËŸ","Ë ","Ë¡","Ë¢","Ë£","Ë¤","Ë¥","Ë¦","Ë§","Ë¨","Ë©","Ëª","Ë«","Ë¬","Ë­","Ë®","Ë¯","Ë°","Ë±","Ë²","Ë³","Ë´","Ëµ","Ë¶","Ë·","Ë¸","Ë¹","Ëº",
				"Ë»","Ë¼","Ë½","Ë¾","Ë¿","Ì‚","Ì„","Ì…","Ì†","Ì‡Ìˆ","ÌŠ","Ì‹","ÌŒ","ÌŽ","Ì","Ì","Ì‘","Ì’","Ì“","Ì”","Ì•","Ìš","Ì›","Ì¢","Ì§","Ì¨","Ì´","Ìµ","Ì¶","Ì·","Ì¸","Ì½","Ì¾","Ì¿","Í€","Í","Í‚","Íƒ","Í„","Í†","ÍŠ","Í‹",
				"ÍŒ","Í","Í‘","Í’","Í—","Í˜","Í›","Í","Íž","ÍŸ","Í ","Í¡","Í¢","Í£","Í¤","Í¦","Í§","Í¨","Í©","Íª","Í«","Í¬","Í­","Í®","Í¯","Í°","Í±","Í²","Í³","Í´","Íµ","Í¶","Í·","Íº","Í»","Í¼","Í½","Í¾","Í¿","Î„","Î…","Î†",
				"Î‡","Îˆ","Î‰","ÎŠ","ÎŒ","ÎŽ","Î","Î","Î‘","Î’","Î“","Î”","Î•","Î–","Î—","Î˜","Î™","Îš","Î›","Îœ","Î","Îž","ÎŸ","Î ","Î¡","Î£","Î¤","Î¥","Î¦","Î§","Î¨","Î©","Îª","Î«","Î¬","Î­","Î®","Î¯","Î°","Î±","Î²","Î³",
				"Î´","Îµ","Î¶","Î·","Î¸","Î¹","Îº","Î»","Î¼","Î½","Î¾","Î¿","Ï€","Ï","Ï‚","Ïƒ","Ï„","Ï…","Ï†","Ï‡","Ïˆ","Ï‰","ÏŠ","Ï‹","ÏŒ","Ï","ÏŽ","Ï","Ï","Ï‘","Ï’","Ï“","Ï”","Ï•","Ï–","Ï—","Ï˜","Ï™","Ïš","Ï›","Ïœ","Ï",
				"Ïž","ÏŸ","Ï ","Ï¡","Ï¢","Ï£","Ï¤","Ï¥","Ï¦","Ï§","Ï¨","Ï©","Ïª","Ï«","Ï¬","Ï­","Ï®","Ï¯","Ï°","Ï±","Ï²","Ï³","Ï´","Ïµ","Ï¶","Ï·","Ï¸","Ï¹","Ïº","Ï»","Ï¼","Ï¾","Ï¿","Ð€","Ð","Ð‚","Ðƒ","Ð„","Ð…","Ð†","Ð‡","Ðˆ",
				"Ð‰","ÐŠ","Ð‹","ÐŒ","Ð","ÐŽ","Ð","Ð","Ð‘","Ð’","Ð“","Ð”","Ð•","Ð–","Ð—","Ð˜","Ð™","Ðš","Ð›","Ðœ","Ð","Ðž","ÐŸ","Ð ","Ð¡","Ð¢","Ð£","Ð¤","Ð¥","Ð¦","Ð§","Ð¨","Ð©","Ðª","Ð«","Ð¬","Ð­","Ð®","Ð¯","Ð°","Ð±","Ð²",
				"Ð³","Ð´","Ðµ","Ð¶","Ð·","Ð¸","Ð¹","Ðº","Ð»","Ð¼","Ð½","Ð¾","Ð¿","Ñ€","Ñ","Ñ‚","Ñƒ","Ñ„","Ñ…","Ñ†","Ñ‡","Ñˆ","Ñ‰","ÑŠ","Ñ‹","ÑŒ","Ñ","ÑŽ","Ñ","Ñ","Ñ‘","Ñ’","Ñ“","Ñ”","Ñ•","Ñ–","Ñ—","â†•","â™«"};
		Collections.addAll(TheAlphabetAndNumerique, AlphabetAndNumerique);
		ArrayList<String> TheCode = new ArrayList<String>();
		for(int i = 0; i+2 < PartiellementDecrypte.size(); i=i+3){
			if(Integer.parseInt(PartiellementDecrypte.get(i)) == 0 && Integer.parseInt(PartiellementDecrypte.get(i+1)) == 0){
				TheCode.add(TheAlphabetAndNumerique.get(Integer.parseInt(PartiellementDecrypte.get(i+2))));
			}else if (Integer.parseInt(PartiellementDecrypte.get(i)) == 0){
				TheCode.add(TheAlphabetAndNumerique.get(Integer.parseInt(PartiellementDecrypte.get(i+1) + PartiellementDecrypte.get(i+2))));
			} else{
				TheCode.add(TheAlphabetAndNumerique.get(Integer.parseInt(PartiellementDecrypte.get(i) + PartiellementDecrypte.get(i+1) + PartiellementDecrypte.get(i+2))));
			}
			if(i + 3 == PartiellementDecrypte.size()){
				for(int f = 0; f < TheCode.size(); f++){
					Decrypte = Decrypte + TheCode.get(f);
				}
			}
		}
		return Decrypte;
	}
	
	public static String decrypt(String str) {
		return decryptagePass(moneyRead(str));
	}
	
}
