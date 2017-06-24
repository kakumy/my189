package first.test;

public class Test01
{

	public static void main(String[] args)
	{
		String[] strArray = {"1","2","3","4","5"};
		for (int i = 0; i < strArray.length; i++)
		{
			System.out.print(strArray[i]+"\t");
		}
		System.out.println();
		for (int i = strArray.length-1; i>=0; i--)
		{
			System.out.print(strArray[i]+"\t");			
		}
		
		
	}

}
