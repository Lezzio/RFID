package fr.pag.pay.cypher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @see PAG Pay
 */
public class Encrypter {
	
	public static String cryptagePassionButJustForPass(String Password){
		String Alors = null;
		ArrayList<String> TheAlphabetAndNumerique = new ArrayList<String>();
		String[] AlphabetAndNumerique = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9",
				" ","&","_","é","\"","#","'","{","(","[","-","|","è","'\'","ç","�","!","$","%",")","*","+",",","-",".","/",":",";","<","=",">","?","@","A","B","C","D","E","F","G","H",
				"I","J","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","]","^","`","}","~","€","ƒ","…","†","‡","‰","Š","‹","Œ","‘","’","•","—","™","Ž","›","œ","Ÿ","¡","¢",
				"£","¤","¥","¦","§","¨","©","ª","«","¬","®","¯","°","±","²","³","","´","µ","¶","·","¸","¹","º","»","¼","½","¾","¿","À","Á","Â","Ã","Ä","Å","Æ","Ç","È","É","Ê","Ë","Ì","Í",
				"Î","Ï","Ð","Ñ","Ò","Ó","Ô","Õ","Ö","×","Ø","Ù","Ú","Û","Ü","Ý","Þ","ß","à","á","â","ã","ä","å","æ","ç","è","é","ê","ë","ì","í","î","ï","ð","ñ","ò","ó","ô","õ","ö","÷",
				"ø","ù","ú","û","ü","ý","þ","ÿ","Ā","ā","Ă","Ą","ą","Ć","ć","Ĉ","ĉ","Ċ","ċ","Č","č","Ď","ď","Đ","đ","Ē","ē","Ĕ","ĕ","Ė","ė","Ę","ę","Ě","ě","Ĝ","ĝ","Ğ","ğ","Ġ","ġ","Ģ",
				"ģ","Ĥ","ĥ","Ħ","ħ","Ĩ","ĩ","Ī","ī","Ĭ","ĭ","Į","į","İ","ı","Ĳ","ĳ","Ĵ","ĵ","Ķ","ķ","ĸ","Ĺ","ĺ","Ļ","ļ","Ľ","ľ","Ŀ","ŀ","Ł","ł","Ń","ń","Ņ","ņ","Ň","ň","ŉ","Ŋ","ŋ","Ō",
				"ō","Ŏ","ŏ","Ő","ő","Œ","œ","Ŕ","ŕ","Ŗ","ŗ","Ř","ř","Ś","ś","Ŝ","ŝ","Ş","ş","Š","š","Ţ","ţ","Ť","ť","Ŧ","ŧ","Ũ","ũ","Ū","ū","Ŭ","ŭ","Ů","ů","Ű","ű","Ų","ų","Ŵ","ŵ","Ŷ",
				"ŷ","Ÿ","Ź","ź","Ż","ż","Ž","ž","ſ","ƀ","Ɖ","Ɗ","Ƌ","ƌ","ƍ","Ǝ","Ə","Ɛ","Ƒ","ƒ","Ɠ","Ɣ","ƕ","Ɩ","Ɨ","Ƙ","ƙ","ƚ","ƛ","Ɯ","Ɲ","ƞ","Ɵ","Ơ","ơ","Ƣ","ƣ","Ƥ","ƥ","Ʀ","Ƨ","ƨ",
				"Ʃ","ƪ","ƫ","Ƭ","ƭ","Ʈ","Ư","ư","Ʊ","Ʋ","Ƴ","ƴ","Ƶ","ƶ","Ʒ","Ƹ","ƹ","ƺ","ƻ","Ƽ","ƽ","ƾ","ƿ","ǀ","ǁ","ǂ","ǃ","Ǆ","ǅ","ǆ","Ǉ","ǈ","ǉ","Ǌ","ǋ","ǌ","Ǎ","ǎ","Ǐ","ǐ","Ǒ","ǒ",
				"Ǔ","ǔ","Ǖ","ǖ","Ǘ","ǘ","Ǚ","ǚ","Ǜ","ǜ","ǝ","Ǟ","ǟ","Ǡ","ǡ","Ǣ","ǣ","Ǥ","ǥ","Ǧ","ǧ","Ǩ","ǩ","Ǫ","ǫ","Ǭ","ǭ","Ǯ","ǯ","ǰ","Ǳ","ǲ","ǳ","Ǵ","ǵ","Ƕ","Ƿ","Ǹ","ǹ","Ǻ","ǻ","Ǽ",
				"ǽ","Ǿ","ǿ","Ȁ","ȁ","Ȃ","ȃ","Ȅ","ȅ","Ȇ","ȇ","Ȉ","ȉ","Ȋ","ȋ","Ȍ","ȍ","Ȏ","ȏ","Ȑ","ȑ","Ȓ","ȓ","Ȕ","ȕ","Ȗ","ȗ","Ș","ș","Ț","ț","Ȝ","ȝ","Ȟ","ȟ","Ƞ","ȡ","Ȣ","ȣ","Ȥ","ȥ","Ȧ",
				"ȧ","Ȩ","ȩ","Ȫ","ȫ","Ȭ","ȭ","Ȯ","ȯ","Ȱ","ȱ","Ȳ","ȳ","ȴ","ȵ","ȶ","ȷ","ȸ","ȹ","Ⱥ","Ȼ","ȼ","Ƚ","Ⱦ","ȿ","ɀ","Ɂ","ɂ","Ƀ","Ʉ","Ʌ","Ɇ","ɇ","Ɉ","ɉ","Ɋ","ɋ","Ɍ","ɍ","Ɏ","ɏ","ɐ",
				"ɑ","ɒ","ɓ","ɔ","ɕ","ɖ","ɗ","ɘ","ə","ɚ","ɛ","ɜ","ɝ","ɞ","ɟ","ɠ","ɡ","ɢ","ɣ","ɤ","ɥ","ɦ","ɧ","ɨ","ɩ","ɪ","ɫ","ɬ","ɭ","ɮ","ɯ","ɰ","ɱ","ɲ","ɳ","ɴ","ɵ","ɶ","ɷ","ɸ","ɹ","ɺ",
				"ɻ","ɼ","ɽ","ɾ","ɿ","ʀ","ʁ","ʂ","ʃ","ʄ","ʅ","ʆ","ʇ","ʈ","ʉ","ʊ","ʋ","ʌ","ʍ","ʎ","ʏ","ʐ","ʑ","ʒ","ʓ","ʔ","ʕ","ʖ","ʗ","ʘ","ʙ","ʚ","ʛ","ʜ","ʝ","ʞ","ʟ","ʠ","ʡ","ʢ","ʣ","ʤ",
				"ʥ","ʦ","ʧ","ʨ","ʩ","ʪ","ʫ","ʬ","ʭ","ʮ","ʯ","ʰ","ʱ","ʲ","ʳ","ʴ","ʵ","ʶ","ʷ","ʸ","ʹ","ʺ","ʻ","ʼ","ʽ","ʾ","ʿ","ˀ","ˁ","˂","˃","˄","˅","ˆ","ˇ","ˈ","ˉ","ˊ","ˋ","ˌ","ˍ","ˎ",
				"ˏ","ː","ˑ","˒","˓","˔","˕","˖","˗","˘","˙","˚","˛","˝","˞","˟","ˠ","ˡ","ˢ","ˣ","ˤ","˥","˦","˧","˨","˩","˪","˫","ˬ","˭","ˮ","˯","˰","˱","˲","˳","˴","˵","˶","˷","˸","˹","˺",
				"˻","˼","˽","˾","˿","̂","̄","̅","̆","̇̈","̊","̋","̌","̎","̏","̐","̑","̒","̓","̔","̕","̚","̛","̢","̧","̨","̴","̵","̶","̷","̸","̽","̾","̿","̀","́","͂","̓","̈́","͆","͊","͋",
				"͌","͐","͑","͒","͗","͘","͛","͝","͞","͟","͠","͡","͢","ͣ","ͤ","ͦ","ͧ","ͨ","ͩ","ͪ","ͫ","ͬ","ͭ","ͮ","ͯ","Ͱ","ͱ","Ͳ","ͳ","ʹ","͵","Ͷ","ͷ","ͺ","ͻ","ͼ","ͽ",";","Ϳ","΄","΅","Ά",
				"·","Έ","Ή","Ί","Ό","Ύ","Ώ","ΐ","Α","Β","Γ","Δ","Ε","Ζ","Η","Θ","Ι","Κ","Λ","Μ","Ν","Ξ","Ο","Π","Ρ","Σ","Τ","Υ","Φ","Χ","Ψ","Ω","Ϊ","Ϋ","ά","έ","ή","ί","ΰ","α","β","γ",
				"δ","ε","ζ","η","θ","ι","κ","λ","μ","ν","ξ","ο","π","ρ","ς","σ","τ","υ","φ","χ","ψ","ω","ϊ","ϋ","ό","ύ","ώ","Ϗ","ϐ","ϑ","ϒ","ϓ","ϔ","ϕ","ϖ","ϗ","Ϙ","ϙ","Ϛ","ϛ","Ϝ","ϝ",
				"Ϟ","ϟ","Ϡ","ϡ","Ϣ","ϣ","Ϥ","ϥ","Ϧ","ϧ","Ϩ","ϩ","Ϫ","ϫ","Ϭ","ϭ","Ϯ","ϯ","ϰ","ϱ","ϲ","ϳ","ϴ","ϵ","϶","Ϸ","ϸ","Ϲ","Ϻ","ϻ","ϼ","Ͼ","Ͽ","Ѐ","Ё","Ђ","Ѓ","Є","Ѕ","І","Ї","Ј",
				"Љ","Њ","Ћ","Ќ","Ѝ","Ў","Џ","А","Б","В","Г","Д","Е","Ж","З","И","Й","К","Л","М","Н","О","П","Р","С","Т","У","Ф","Х","Ц","Ч","Ш","Щ","Ъ","Ы","Ь","Э","Ю","Я","а","б","в",
				"г","д","е","ж","з","и","й","к","л","м","н","о","п","р","с","т","у","ф","х","ц","ч","ш","щ","ъ","ы","ь","э","ю","я","ѐ","ё","ђ","ѓ","є","ѕ","і","ї","↕","♫"};
		Collections.addAll(TheAlphabetAndNumerique, AlphabetAndNumerique);
		ArrayList<String> TheCode = new ArrayList<String>();
		for(int i = 0; i < Password.length(); i++){
			if(TheAlphabetAndNumerique.indexOf(String.valueOf(Password.charAt(i))) < 10){
				TheCode.add("00" + String.valueOf(TheAlphabetAndNumerique.indexOf(String.valueOf(Password.charAt(i)))));
			} else if(TheAlphabetAndNumerique.indexOf(String.valueOf(Password.charAt(i))) < 100){
				TheCode.add("0" + String.valueOf(TheAlphabetAndNumerique.indexOf(String.valueOf(Password.charAt(i)))));
			}
			else if (TheAlphabetAndNumerique.indexOf(String.valueOf(Password.charAt(i))) > 99){
				TheCode.add(String.valueOf(TheAlphabetAndNumerique.indexOf(String.valueOf(Password.charAt(i)))));
			}
			if(i + 1 == Password.length()){
				String CrypteUnPeu = "";
				for(int f = 0; f < TheCode.size(); f++){
					CrypteUnPeu = CrypteUnPeu + TheCode.get(f);
					if(f + 1 == TheCode.size()){
						Alors = CrypteUnPeu;
					}
				}
			}
		}
		return Alors;
	}
	
	public static String moneyRegister(String Money){
		String Alors = "Echec";
		String Argent = Money;
		final ArrayList<String> MoneyCaracteres = new ArrayList<String>();
		for(int i = 0; i < Argent.length(); i++){
			String Caracteren = String.valueOf(Argent.charAt(i));
			MoneyCaracteres.add(Caracteren);
			if(i+1 == Argent.length()){
			  	Random rand = new Random();
			  	int LeHasard = rand.nextInt(26);
				ArrayList<String> TheCode = new ArrayList<String>();
			  	int LeCalcul = MoneyCaracteres.size() - LeHasard;
			  	if(LeCalcul > 25){
					int lowerZ = LeCalcul - 25;
					int higherZ = LeCalcul + 25;
					int HasardeuxLeHasard = (int)(Math.random() * (higherZ-lowerZ)) + lowerZ;
					LeCalcul = LeCalcul - HasardeuxLeHasard;
			  		TheCode.add(String.valueOf(HasardeuxLeHasard));
			  	}else{
			  		TheCode.add("0");
			  	}
				ArrayList<String> TheAlphabet = new ArrayList<String>();
				ArrayList<String> TheAlphabetPair = new ArrayList<String>();
				ArrayList<String> TheAlphabetImpair = new ArrayList<String>();
				ArrayList<String> TheAlphabetMagique = new ArrayList<String>();
				ArrayList<String> TheAlphabetFantasmabolique = new ArrayList<String>();
				String[] AlphabetWooow = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
				String[] AlphabetImPair = {"b","d","f","h","j","l","n","p","r","t","v","x","z"};
				String[] AlphabetPair = {"a","c","e","g","i","k","m","o","q","s","u","w","y"};
				String[] AlphabetMagique = {"a","j","p","g","m","s","r","f","k","d","t","q","z"};
				String[] AlphabetFantasmabolique = {"z","y","w","a","d","f","g","p","s","x","h","l","r"};
				Collections.addAll(TheAlphabet, AlphabetWooow);
				Collections.addAll(TheAlphabetPair, AlphabetPair);
				Collections.addAll(TheAlphabetImpair, AlphabetImPair);
				Collections.addAll(TheAlphabetMagique, AlphabetMagique);
				Collections.addAll(TheAlphabetFantasmabolique, AlphabetFantasmabolique);
	/**/		TheCode.add(TheAlphabet.get(LeHasard));
				int LeHasard5 = rand.nextInt(13);
				if(LeCalcul < 0){
	/**/			TheCode.add(TheAlphabetPair.get(LeHasard5));
	/**/			TheCode.add(TheAlphabet.get(-LeCalcul));
				}
				else if(LeCalcul >= 0){
/**/				TheCode.add(TheAlphabetImpair.get(LeHasard5));
/**/				TheCode.add(TheAlphabet.get(LeCalcul));
				}
				
			  	int HowMuch = rand.nextInt(500);
			  	int HowMuchBis1 = rand.nextInt(500);
			  	int HowMuchBis2 = HowMuch - HowMuchBis1;
			  	int LePostierOuLeFacteurJeSaisPlusTrop = 42; // 42 = Le Sens De La Vie
			  	String HowMuchBis1String = String.valueOf(HowMuchBis1 * LePostierOuLeFacteurJeSaisPlusTrop);
			  	String HowMuchBis2String = String.valueOf(HowMuchBis2 * LePostierOuLeFacteurJeSaisPlusTrop);
			  	int Taille1 = HowMuchBis1String.length();
	/**/	  	TheCode.add(TheAlphabetMagique.get(Taille1));
			  	int Taille2 = HowMuchBis2String.length();
	/**/  	    TheCode.add(TheAlphabetFantasmabolique.get(Taille2));
				HowMuch = Taille1 * 42 + Taille2 * 42;
				for(int f = 0; f < HowMuch; f++){
					int WichOne = rand.nextInt(26);
/*InutileLikeRamay*/TheCode.add(TheAlphabet.get(WichOne));

					if(f+1 == HowMuch){
						for(int e = 0; e <= MoneyCaracteres.size(); e++){
							
							if(e == MoneyCaracteres.size()){ //DerniereEtapeAttention
								e = MoneyCaracteres.size()+1;
								String MoneyCrypte = "";
								for(int y = 0; y < TheCode.size(); y++){
									MoneyCrypte = MoneyCrypte + TheCode.get(y);
									if(y+1 == TheCode.size()){
										Alors = MoneyCrypte;
									}
								}
							}
							else{
								int WichNumber = Integer.parseInt(MoneyCaracteres.get(e));
								int LeHasard2 = rand.nextInt(26);
								int LeHasard3 = rand.nextInt(13);
								if(WichNumber > LeHasard2+LeHasard3){
									int LeCalcul2 = WichNumber - (LeHasard2+LeHasard3);
	/**/							TheCode.add(TheAlphabetImpair.get(LeHasard3));
	/**/							TheCode.add(TheAlphabet.get(LeHasard2));
	/**/							TheCode.add(TheAlphabet.get(LeCalcul2));
								}
								else if(WichNumber <= LeHasard2+LeHasard3){
									int LeCalcul2 = WichNumber - (LeHasard2+LeHasard3);
									if(-LeCalcul2 >= 26){
										int lower = 26;
										int higher = -LeCalcul2;
										int LeHasard4 = (int)(Math.random() * (higher-lower)) + lower;
										LeCalcul2 = -LeCalcul2 - LeHasard4; 
					/**/            	TheCode.add(TheAlphabetPair.get(LeHasard3));
					/**/				TheCode.add(String.valueOf(LeHasard4));
					/**/				TheCode.add(TheAlphabet.get(LeHasard2));
					/**/				TheCode.add(TheAlphabet.get(LeCalcul2));
									}
									else if (-LeCalcul2 < 26){
										LeCalcul2 = -LeCalcul2;
				/**/					TheCode.add(TheAlphabetPair.get(LeHasard3));
				/**/					TheCode.add(TheAlphabet.get(LeHasard2));
				/**/					TheCode.add(TheAlphabet.get(LeCalcul2));
									}
								}
							}
						}
					}
				}
			}
		}
		return Alors;
	}
	
	public static String encrypt(String str) {
		return moneyRegister(cryptagePassionButJustForPass(str));
	}

}
