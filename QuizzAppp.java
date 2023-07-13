
import java.util.*;
class QuizzAppp{
	
	static Scanner scanner = new Scanner(System.in);
	final static String[] questions = {
		
		" \n1].Who opened the first school for girls in India?",
		" \n2].How many years did it take to build the Taj Mahal?",
		" \n3].How many bones does a human adult have?",
		" \n4].Who designed the periodic table?",
		" \n5].Who is the current Governor of the Reserve Bank of India?",
		" \n6].What does the ozone layer protect us from?",
		" \n7].At what temperature is the density of water at its maximum?",
		" \n8].Which gas is also known as laughing gas?",
		" \n9].Who wrote 'The Jungle Book'?",
		" \n10].When is National Farmer's Day celebrated in India?"
		 
	};
	final static String[] options ={
		"(A). Savitribai Phule(b). Captain Prem Mathu (c). Vijaya Lakshmi Pandit(d). Tessy Thomas ",
		"(A). 20 Years        (b). 10 Years           (c). 25 Years             (d). 30 Years  ",
		"(A). 195             (b). 206                (c). 300                  (d). 208 ",
		"(A). Adverd jener    (b). Robort Hoock       (c). Dmitri Mendeleev     (d). Robort kecin ",
		"(A). Y. V. Reddy     (b). Raghuram Rajan     (c). Urjit Patel          (d). Shaktikanta Das ",
		"(A). X-Rays          (b). I-R Radiation      (c). UV rays              (d). Nothig ",
		"(A). 6-degree celsius(b). 0-degree celsius   (c). 100-degree celsius   (d). 4-degree celsius ",
		"(A). Nitrous DiOxide (b). Nitrous Oxide      (c). Nitrous TriOxide     (d). Nitrous Sulphate ",
		"(A). Rudyard Vipling (b). Audyard Siplong    (c). Yard Kong            (d). Rudyard Kipling ",
		"(A). 23 December     (b). 23 Octomber        (c). 22 November          (d). 22 December "
	};
	final static String [] redusceOptions ={
		"A). Savitribai Phule  (C). Vijaya Lakshmi Pandit  ",
		"A). 20 Years          (D). 30 Years  ",
		"A). 195               (B). 206  ",
		"C). Dmitri Mendeleev  (D). Robort kecin  ",
		"A). Y. V. Reddy       (D). Shaktikanta Das  ",
		"B). I-R Radiation     (C). UV rays  ",
		"A). 6-degree celsius  (D). 4-degree celsius  ",
		"B). Nitrous Oxide     (D). Nitrous Sulphate  ",
		"C). Yard Kong         (D). Rudyard Kipling  ",
		"A). 23 December       (B). 23 Octomber  "
	};
	final static String[] answers ={"A","A","B","C","D","C","D","B","D","A"};
	final static String [] hints ={
		"Belonging from Maharashtra",
		"The artisans and laborers involved in the building of the iconic mausoleum, Taj Mahal, dedicated a significant portion of their lives to the project, which lasted for two score fortnights.",
		"The human skeletal system is a marvel of nature, comprising of a precise number of bones that is just over two hundred",
		"The individual who is credited with the design of the periodic table was a Russian chemist who lived during the 19th century.",
		"The current Governor of the Reserve Bank of India has previously held the position of Deputy Governor in the same institution and has also worked for the International Monetary Fund (IMF).",
		"The ozone layer serves as a shield against a type of electromagnetic radiation that has a wavelength shorter than that of visible light and is harmful to living organisms on Earth.",
		"The temperature at which water is most dense is not the same as its freezing or boiling point.",
		"This colorless and non-flammable gas, which has a slightly sweet odor and taste, is also used as a propellant in aerosol cans and whipped cream dispensers.",
		"The author who wrote the collection of stories known as 'The Jungle Book' was a British author who was born in India during the 19th century.",
		"National Farmer's Day is an annual observance in India that celebrates the contributions of farmers and agriculture to the country's economy and society, and it is typically celebrated during a harvest season.",
	};
	final static String [] descriptiveSolutions ={
		"soln1",
		"soln2",
		"soln3",
		"soln4",
		"soln5",
		"soln6",
		"soln7",
		"soln8",
		"soln9",
		"soln10",
	};
	static String [] userAnswer = new String[10];
	static int trueCount = 0;
	static int falseCount = 0;
	static  int questionCount  ;
	static int[] hLUseTime ={0 , 0 , 0 , 0};//0-reduceOptions,1-showCorrectAnswer,2-callAFriend,3-hint;
	static String helplineChoice;
	final static String [] helpline = { "reduceOptions" , "showCorrectAnswer" , "callAFriend" , "Hint" };
	static String line = "_____________________________________________________________________________________________________________________";
	
	
	public static void main(String [] args){
		while(true){
		System.out.println(line+"\nWelcome to the Quiz !\n");
		System.out.println(line+"\nRules : \n1]. 10 questions all questions are mendatory\n2]. Give answers in apropriate options \n3]. Use H for using helpline \n4]. You have only 4 helplines \n5]. One helpline can use only one time\n6] at the end of quiz you will get discriptive solutions\n"+line);
		System.out.println("Agree ? : (y/n)");
		String choice = scanner.next();
		if(choice.equalsIgnoreCase("Y")){
			QuizzAppp.attendingQuix();
		}else if(choice.equalsIgnoreCase("N")){
			System.out.println("Thank you visit again!");
			System.exit(0);
		}else{
			System.out.println("Invalid choice!");
		}
		System.exit(0);
	}	
}
	static void attendingQuix(){
		String temp;
		for( questionCount = 0 ; questionCount <questions.length ; questionCount++ ){
			quaAndOptPrint(questionCount);
			do{
				System.out.print("Answer :");
				userAnswer[questionCount] = new String();
				userAnswer[questionCount] = scanner.next();
				temp = userAnswer[questionCount];
			}while(!userAnswerCheck(temp));
			
			if(userAnswer[questionCount].equalsIgnoreCase(answers[questionCount])){
				trueCount++;
			}else if(userAnswer[questionCount].equalsIgnoreCase("H")){
				QuizzAppp.helplines(questionCount);
				questionCount--;
			}
		}
		
		
		QuizzAppp.resultPrint();
		trueCount=0;
	}
	static void quaAndOptPrint(int questionCount){
		// To print Question
		System.out.println(line);
		System.out.println(questions[questionCount]);
		// To print Options
		if(reduceOptionsMagic){                                                // to print reduceOptions
			System.out.print(line+"\nreduced Options :");
			System.out.println(redusceOptions[questionCount]+"\n"+line);
			reduceOptionsMagic = false;
		}else if(correctAnsMagic){                                             // to print correct answers
			System.out.print(line+"\n correct option :");
			System.out.println(answers[questionCount]+"\n"+line);
			correctAnsMagic = false;
		}else if(hintMagic){                                                  // to give a hint
			System.out.println("hint ");
			System.out.println(hints[questionCount]+"\n"+line);
			hintMagic = false;
		}else{                                                                // normal
			System.out.println(options[questionCount]);
		}
	}
	static boolean userAnswerCheck(String temp){
		boolean isValidInput = false;
		if(   temp.equalsIgnoreCase("A")||
		      temp.equalsIgnoreCase("B")||temp.equalsIgnoreCase("C")||
		      temp.equalsIgnoreCase("D")||temp.equalsIgnoreCase("H")){
			return !isValidInput;
		}
		return isValidInput;
	}
	
	static void helplines(int questionCount){
		System.out.println("Available Helplines are :- ");
		for(int i = 0 ; i < helpline.length ; i++ ){
			if(hLUseTime[i]==0){
				System.out.println("-->["+(i+1)+"]. " + helpline[i] );
			}else{
				if(hLUseTime[0]!=0&&hLUseTime[1]!=0&&hLUseTime[2]!=0&&hLUseTime[3]!=0){
					System.out.println("All Helplines Were already used!");
					break;
				}else{
					System.out.println("   [x]. " + helpline[i] +" Used.");
				}
			}
		}QuizzAppp.helplineSelection(questionCount);
	}
	
	static void helplineSelection(int questionCount){
		do{
			do{
				System.out.print("helpline choice : ");
				helplineChoice = scanner.next();
				if(helplineChoice.equalsIgnoreCase("Back")){
					return;
				}
			}while(helplineChoiceValidation());
			if(hLUseTime[Integer.parseInt(helplineChoice)-1]==0){
				helplineExicution(questionCount);
				break;
			}else{
				System.out.println("This helpline is Already Used sir!");
				System.out.println("Enter other Helpline ! or Write back to return !");
			}
		}while(true);
	}
	
	static boolean helplineChoiceValidation(){
		boolean isValidHelpline = helplineChoice.equals("1")||helplineChoice.equals("2")||
		                          helplineChoice.equals("3")||helplineChoice.equals("4");
								 
		return !isValidHelpline;
	}
	static boolean reduceOptionsMagic = false;
	static boolean correctAnsMagic = false;
	static boolean hintMagic = false;
	
	static void helplineExicution(int questionCount){
		int temp = Integer.parseInt(helplineChoice);
		switch(helplineChoice){
			 case "1" : {
				hLUseTime[0]++;
				reduceOptionsMagic = true;
				break;
			}case "2" : {
				hLUseTime[1]++;
				correctAnsMagic = true;
				break;
			}case "3" : {
				hLUseTime[2]++;
				System.out.println(line+"\nCalling the friend.......");
				System.out.println(".........................");
				System.out.println("Friend : Aatm Nirbhar bano \n khatam TaTa Bye..Bye..... !\n"+line);
				break;
			}case "4" : {
				System.out.println("Ask the Audience");
				hLUseTime[3]++;
				hintMagic = true;
				break;
			}
		}
	}
	
	static void resultPrint(){
		System.out.println(line);
		if(trueCount>7){
			System.out.println("Your score : "+ trueCount +"\n Excelent performance !\n You should chcek your tini mistakes.");
		}else if (trueCount<=7&& trueCount >= 4){
			System.out.println("Your score : "+ trueCount +"\n Average performance !\n You should go throungh the solutions.");
		}else{
			System.out.println("Your score : "+ trueCount +"\n Very poor performance !\n You have to lern all the solutions.");
		}
		QuizzAppp.descriptiveSolutions();
	}
	static void descriptiveSolutions(){
		for(int a = 0 ; a < questions.length ; a++){
			
			System.out.println(line+"\n");
			System.out.println(line+"\n");
			System.out.println(questions[a]);
			System.out.println("Your Answer : "+userAnswer[a] + "\nCorrect Answer : "+answers[a] +"\nDescriptive Solution " +descriptiveSolutions[a]);
		}
	}
	
}// end

	
	
