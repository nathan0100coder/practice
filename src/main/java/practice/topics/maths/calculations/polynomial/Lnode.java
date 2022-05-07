package practice.topics.maths.calculations.polynomial;
 
 
public class Lnode implements Comparable<Lnode>{
	public float coef;
	public int exp;
	public Lnode next;
	public Lnode (float coef,int exp){
		this.exp=exp;
		this.coef=coef;
		next=null;
	}
	public Lnode(float coef,int exp,Lnode next){
		this.exp=exp;
		this.coef=coef;
		this.next=next;
	}
	public boolean equals(Object e){
		Lnode node=(Lnode)e;
		return (exp==node.exp);
	}
	@Override
	public int compareTo(Lnode o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
 