import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;


public class Variable {
	static ArrayList<myNum>numbers = new ArrayList<>();      //used to store numbers input
	/**
	 * define variable and give it value
	 * @param x indicates the order that needs to be processed
	 */
	public static void pocessDefAndAssign(String x){
		myNum num1= new myNum();
		
		x.trim().replace("\n","").replaceAll("\\s{2,}", " ");    //preprocess the order replace space
		int indexOfKongGe = x.indexOf(' ');
		int indexOfEqual = x.indexOf('=');                       //find index of "=" and " ", which is the key to find variable name
		
		num1.cate = x.substring(0, indexOfKongGe);
		num1.name = x.substring(indexOfKongGe + 1, indexOfEqual);
		if(num1.cate.equals("int")){
			num1.isInt = true;
		}
		else if(num1.cate.equals("float")){
			num1.isFloat = true;
		}
		num1.value = new BigDecimal(x.substring(indexOfEqual + 1,x.length() - 1));   //init myNum
		
		numbers.add(num1);
	}
	
	/**define variable
	 * @param x indicates the order that needs to be processed
	 */
	public static void pocessDef(String x){
		myNum num1= new myNum();
		
		x.trim().replace("\n","").replaceAll("\\s{2,}", " ");
		
		int indexOfKongGe = x.indexOf(' ');
		
		num1.cate = x.substring(0, indexOfKongGe);
		num1.name = x.substring(indexOfKongGe + 1, x.length() - 1);
		
		if(num1.cate.equals("int")){
			num1.isInt = true;
		}
		else if(num1.cate.equals("float")){
			num1.isFloat = true;
		}
		numbers.add(num1);
	}
	
	/**
	 * assign variable value
	 * @param x
	 */
	public static void pocessAssign(String x){
		x = x.trim();
		int indexOfEquql = x.indexOf('=');
		
		String theVarName = x.substring(0, indexOfEquql);
		
		for(int i = 0; i < numbers.size(); i++){
			myNum numx = numbers.get(i);
			if(numx.name.equals(theVarName))
				numx.value = new BigDecimal(x.substring(indexOfEquql + 1, x.length() - 1));
		}
	}
	
	/**
	 * Find the value with the name x
	 * @param x indicates the variable name
	 * @return return myNum is the variable exist and assign with value, else return null and report error
	 */
	public static myNum FindValue(String x){
		
		for(int i = 0; i < numbers.size(); i++){
			if(numbers.get(i).name.equals(x)){
				//find the name
				BigDecimal tmp = new BigDecimal(-99999);
				if(!numbers.get(i).value.equals(tmp))
					return numbers.get(i);
				else{
					System.out.println("wrong - variable unassigned");
					return null;
				}
			}
		}
		System.out.println("wrong - variable undefined");
		return null;
	
	}
	
	/**
	 * Find if the numbers exist Float variable
	 * @return
	 */
	public static boolean existFloatNum(){
		for(int i = 0; i < numbers.size(); i++){
			if(numbers.get(i).cate.equals("float"))
				return true;
		}
		return false;
	}
	
	/**
	 * Find if r belong to char[]s, 
	 * @param r
	 * @param s
	 * @param x indicates the length of s
	 * @return
	 */
	public static boolean belongTo(char r, char[] s, int x) {
		for (int i = 0; i < x; i++) {
			if (r == s[i])return true;
		}
		return false;
	}
	/**
	 * Change the expression to Suf style
	 * @param x indicate the expression
	 * @param finalans stand for the final suf expresssion
	 * @return
	 */
	public static boolean newChangeToSuf(String x, ArrayList<ans>finalans){
		Stack<Character>stack0 = new Stack<>();
		char[] s5 = { '(',')','+','-','*','/','^','%' };      //8
		char[] q9 = { '(',')','+','-','*','/','^','%','.' }; //9
		

		for (int i = 0; i < x.length(); i++) {                     //find if there is expression issues
			char ch = x.charAt(i);
			if (ch == '(') {
				stack0.push(ch);
			}
			else if (ch == ')') {
				if (stack0.empty()) {
					System.out.println("wrong - error expression");
					return false;
				}
				else 
					stack0.pop();
			}
			else
				continue;
		}
		
		if (!stack0.empty()) {									//if the stack is not empty, then there is a problem
			System.out.println("wrong - error expression");
			return false;
		}
		
		Stack<Character> sstack = new Stack<>();                //used to put char
		
		char[] s1 = { ')','*','/','%','^' };              		//characters that can't be in the first place
		if (belongTo(x.charAt(0), s1, 5)) {
			System.out.println("wrong - error expression");
			return false;
		}
		
		char[] s2 = { '(','+','-','*','/','%','^' };      		//characters that can't be in the first place
		if (belongTo(x.charAt(x.length() - 1), s2, 7)) {
			System.out.println("wrong - error expression");
			return false;
		}
		
		
		char[] s3 = { '+','-','*','/','%','^' };           //characters that can only followed by "("
		char[] s4 = { '+','-','*','/','%','^',')' };       //used with s3, characters except "("
		char[] s10 = { '*','/','^','%',')' };
		for (int i = 1; i < x.length(); i++) {
			if (x.charAt(i - 1) == '(' && belongTo(x.charAt(i), s10, 5))
			{
				System.out.println("wrong - error expression");
				return false;
			}

			if (belongTo(x.charAt(i - 1), s3, 6) && belongTo(x.charAt(i), s4, 7))
			{
				System.out.println("wrong - error expression");
				return false;
			}
			if (i < x.length() - 1) {
				if (x.charAt(i) == ')' && x.charAt(i+1) == '(')//")" can't followed directly by "("
				{
					System.out.println("wrong - error expression");
					return false;
				}
			}
		}
		
		for (int i = 0; i < x.length(); i++) {           //change to SufStyle
			char ch = x.charAt(i);

			if (i < x.length() - 1 && ch == '(' && x.charAt(i+1) == '+' || i < x.length() - 1 && ch == '(' && x.charAt(i+1) == '-') {
				sstack.push(ch);
				ans expx = new ans();
				expx.isDec = true;
				expx.in = new BigDecimal("0");
				finalans.add(expx);                     //expression start with"(-"
				
			}
			else if (belongTo(ch, s5, 8) && sstack.empty()) {                 //go to the stack
				sstack.push(ch);
				//cout << x[i] << "is pushed in" << endl;
				//cout << "stack top is"<<sstack.top() << endl;
			}
			else if (ch == '(' && belongTo(sstack.peek(), s5, 8))
				sstack.push(ch);
			else if (ch == ')') {
				while (sstack.peek() != '(') {
					ans exxp =new ans();
					exxp.ch = sstack.peek();
					exxp.isCh = true;
					finalans.add(exxp);
					sstack.pop();
					
				}	
				sstack.pop();                                      
				}
		
			else if (ch == '+' || ch == '-') {
				if (sstack.peek() == '(' || sstack.empty())
				{
					sstack.push(ch);
				}
				else {                                                    
					ans exp2 = new ans();
					exp2.ch = sstack.peek();
					exp2.isCh = true;
					finalans.add(exp2);
					sstack.pop();

					while (!sstack.empty() && sstack.peek() != '(')
					{
						ans exp1 = new ans();
						exp1.ch = sstack.peek();
						exp1.isCh = true;
						finalans.add(exp1);
						sstack.pop();
					}
					sstack.push(ch);
				}
			}

			else if (ch == '*' || ch == '/' || ch == '%') {
				if (sstack.peek() == '(' || sstack.empty() || sstack.peek() == '+' || sstack.peek() == '-')
					sstack.push(ch);
				else {
					ans exp3 = new ans();
					exp3.ch = sstack.peek();
					exp3.isCh = true;
					finalans.add(exp3);
					sstack.pop();
					while (!sstack.empty() && sstack.peek() != '(' && sstack.peek() != '+' && sstack.peek() != '-')
					{
						ans exp4 = new ans();
						exp4.ch = sstack.peek();
						exp4.isCh = true;
						finalans.add(exp4);
						sstack.pop();
					}
					sstack.push(ch);
				}
			}
			else if(!belongTo(ch, q9, 9)&&ch<=47 || !belongTo(ch, q9, 9) && ch>=58){ //encounter variable name
				int indexOfVar = i+1;
				for(; indexOfVar < x.length(); indexOfVar++){
					if(!belongTo(x.charAt(indexOfVar), q9, 9)
							&&x.charAt(indexOfVar)<=47 ||
							!belongTo(x.charAt(indexOfVar), q9, 9) &&
							x.charAt(indexOfVar)>=58){
								continue;
							}
					else break;
				}
				
				String theVar = x.substring(i, indexOfVar);
				myNum mynumhere  = FindValue(theVar);
				if(mynumhere == null){
					return false;
				}
				i=indexOfVar-1;
				ans exp7 = new ans();
				exp7.in = mynumhere.value;
				exp7.isDec = true;
				exp7.hasFloat = mynumhere.isFloat;
				finalans.add(exp7);
			}
			else if (ch > 47 && ch < 58 ) {                                 //encounter numbers
				int indexOfNum = i+1;
				boolean flag = false;
				for(;indexOfNum < x.length(); indexOfNum++){
					if(x.charAt(indexOfNum)>47&&x.charAt(indexOfNum)<58)continue;
					else if(x.charAt(indexOfNum) == '.'){
						flag = true;
						continue;
					}
					else break;
				}
				String theDouNum = x.substring(i, indexOfNum);
				BigDecimal newn= new BigDecimal(theDouNum);
				i=indexOfNum-1;
				ans exp7 = new ans();
				exp7.in = newn;
				exp7.isDec = true;
				exp7.hasFloat = flag;
				finalans.add(exp7);
			}

		}
		while (!sstack.empty()) {
			ans exxxp = new ans();
			exxxp.ch = sstack.peek();
			exxxp.isCh = true;
			finalans.add(exxxp);
			sstack.pop();
		}
		return true;
	}
	
	
	/**
	 * Calculate the final ans of the expression
	 * @param finalans the SufStyle expression
	 * @return
	 */
	static BigDecimal newcalculate(ArrayList <ans> finalans) {
		Stack<ans> newstack = new Stack<>();
		if (finalans.size() == 1) {
				return finalans.get(0).in;
		}
		
		for (int i = 0; i < finalans.size(); i++) {
			if (finalans.get(i).isDec) {              //encounter a number
				newstack.push(finalans.get(i));
			}
			else {                                    //encounter a operator
				ans myansX = newstack.peek();
				newstack.pop();
				ans myansY = newstack.peek();
				newstack.pop();
				ans myansZ = new ans();
				myansZ.isDec = true;
				myansZ.in = new BigDecimal("0");
				
				//if any of x,y is float
				if(myansX.hasFloat || myansY.hasFloat){
					myansZ.hasFloat = true;
					if (finalans.get(i).ch == '+') 
						myansZ.in = myansX.in.add(myansY.in);
					else if (finalans.get(i).ch == '-') 
						myansZ.in = myansY.in.subtract(myansX.in);
					else if (finalans.get(i).ch == '*') 
						myansZ.in = myansX.in.multiply(myansY.in);
					else if (finalans.get(i).ch == '/') 
							myansZ.in = myansY.in.divide(myansX.in, 10, RoundingMode.HALF_UP);
					else if (finalans.get(i).ch == '%') {
						BigDecimal[] aaa  = myansY.in.divideAndRemainder(myansX.in);
						myansZ.in = aaa[1];	
					}
				}
				else{                                            //both x,y is int
					myansZ.hasFloat = false;
					int ansx = myansX.in.intValue();
					int ansy = myansY.in.intValue();
					if (finalans.get(i).ch == '+') {
						int zz = ansx+ansy;
						myansZ.in = new BigDecimal(zz);
					}
					else if (finalans.get(i).ch == '-') {
						int zz = ansy - ansx;
						myansZ.in = new BigDecimal(zz);
					}
					else if (finalans.get(i).ch == '*') {
						myansZ.in = myansX.in.multiply(myansY.in);
					}
					else if (finalans.get(i).ch == '/') {
							//System.out.println("inside /");
							int vx = myansX.in.intValue();
							int vy = myansY.in.intValue();
							int vz = vy/vx;
							myansZ.in = new BigDecimal(vz);
					}
					else if (finalans.get(i).ch == '%') {
						BigDecimal[] aaa  = myansY.in.divideAndRemainder(myansX.in);
						myansZ.in = aaa[1];
					}
					
				}
					
				if (!newstack.empty() || i != finalans.size() - 1) 
					newstack.push(myansZ);
				else 
					return myansZ.in;
			}
		}
		return null;
	}
	
	/**
	 * used to find if I have to keep the decimal part at 2
	 * @param x
	 * @return
	 */
	public static boolean FindIfFormat(String x){
		boolean x1 = existFloatNum();
		return !x1&&!x.contains(".");
	}
	
	/**
	 * calculate the ? orders
	 * @param x indicates orders with ?
	 */
	public static void jisuan(String x){
		int indexOfEqual = x.indexOf('=');
		x = x.substring(0, indexOfEqual);
		
		ArrayList<ans>anst = new ArrayList<>();
		boolean can = newChangeToSuf(x,anst);
		if(can){
			BigDecimal ff = newcalculate(anst);
			if(FindIfFormat(x))
				System.out.println(ff.toString());
			else{
				float ffans = ff.floatValue();
				String result = String.format("%.2f", ffans);
				System.out.println(result);			
			}
		}
		
	}
	
	public static void main(String[]args){
		String orders = new String();
		Scanner sc = new Scanner(System.in);
		orders = sc.nextLine();
		if(orders.contains("?")){
			String[]allOrder = orders.split(";");
			for(int i = 0; i < allOrder.length - 1; i++){
				String od = allOrder[i] + ";";
				if(od.contains("int") || od.contains("float")){
					if(od.contains("=")){
						//define and assign
						pocessDefAndAssign(od);
					}
					else{
						//just define
						pocessDef(od);
					}
				}
			}
			allOrder[allOrder.length - 1] = allOrder[allOrder.length - 1].trim();
			jisuan(allOrder[allOrder.length - 1]);
		}
		else{
			while(!orders.contains("?")){
				if(orders.contains("int") || orders.contains("float")){
					if(orders.contains(";")){
						if(orders.contains("=")){
							//define and assign
							pocessDefAndAssign(orders);
						}
						else{
							//just define
							pocessDef(orders);
						}
					}
					else{
						orders = orders + ";";
						if(orders.contains("=")){
							//define and assign
							pocessDefAndAssign(orders);
						}
						else{
							//just define
							pocessDef(orders);
						}
					}
				}
				else{
					//just assign
					if(orders.contains(";"))
						pocessAssign(orders);
					else{
						orders = orders+";";
						pocessAssign(orders);
					}
				}
				orders = sc.nextLine();
			}
			orders = orders.trim();
			jisuan(orders);
		}
		sc.close();
	}
}

class ans{
	public BigDecimal in;
	public boolean isDec;
	public Character ch;
	public boolean isCh;
	
	public boolean hasFloat;
	public ans(){
		isDec = false;
		isCh = false;
		hasFloat = false;
	}
	void setNum(BigDecimal inn){
		in = inn;
		isDec = true;
	}
	
	void setChar(Character cc){
		ch = cc;
		isCh = true;
	}
	public String toString(){
		if(isDec)return in.toString();
		else return ch.toString();
	}
}

class myNum{
	public String cate;
	public String name;
	public BigDecimal value;
	public boolean isFloat;
	public boolean isInt;
	public myNum(){
		cate = "no";
		name = "****";
		isFloat = false;
		isInt = false;
		value = new BigDecimal(-99999);
	}
}