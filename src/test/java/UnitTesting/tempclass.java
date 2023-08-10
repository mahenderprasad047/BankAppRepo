package UnitTesting;

public class tempclass {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int expctedDate = 10;
		String actualDate = "10";
		if(expctedDate == Integer.parseInt(actualDate))
		{
			System.out.println("Parse is working");
		}
		
		String expctedDate1 = "10";
		Object actualDate1 = 10;
		
		if(expctedDate1.equals(actualDate1.toString()))
		{
			System.out.println("toString for object type variable is working");
		}
		
	}

}
