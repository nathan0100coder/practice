package practice.topics.maths.calculations.complex;

public class ComplexNumber
{
	private int realPart;
	private int imagPart;
	
	public ComplexNumber()
	{
		this.realPart=0;
		this.imagPart=0;
	}
	
	public ComplexNumber(int a,int b)
	{
		this.realPart=a;
		this.imagPart=b;
	}
	
	public ComplexNumber add(ComplexNumber com1,ComplexNumber com2)
	{  
		this.realPart = com1.realPart+com2.realPart;
		this.imagPart = com1.imagPart+com2.imagPart;
		return this;
	}
	
	public ComplexNumber sub(ComplexNumber com1,ComplexNumber com2)
	{	
		this.realPart = com1.realPart - com2.realPart;
		this.imagPart = com1.imagPart - com2.imagPart;
		return this;
	}
	
	public static void main(String args[])
	{
		ComplexNumber com1 = new ComplexNumber(3,5);
		ComplexNumber com2 = new ComplexNumber(2,3);
		ComplexNumber sum = new ComplexNumber();
		ComplexNumber diff = new ComplexNumber();
		System.out.println("两个复数为" + com1.realPart 
							+ "+" + com1.imagPart + "i"
							+ " " + com2.realPart 
							+ "+" + com2.imagPart + "i");
		
		sum.add(com1,com2);
		System.out.println("复数和: " + sum.realPart 
				+ "+" + sum.imagPart + "i");
		diff.sub(com1,com2);
		System.out.println("复数差: " + diff.realPart 
				+ "+" + diff.imagPart + "i");
	}
}

