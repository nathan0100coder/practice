package practice.topics.maths.calculations.polynomial;

public class PloyItemList  {
	private int length;
	Lnode first;
	public PloyItemList(int length)
	{
		first=null;
		this.length=length;
	}
	public PloyItemList(){
		first=null;
		length=0;
	}
	public int size()//获取链表的长度
	{
		Lnode p=first;
		int count=0;
		while(p!=null){
			count++;
			p=p.next;
		}
		return count;
	}
	public boolean add(float coef,int exp)//向链表当中添加元素的方法
	{
		
		Lnode p=first,s;
		s=new Lnode(coef,exp,null);
		
		if(first==null){
			first=s;
			s.next=null;
			return true;
		}
		else {
			while(p.next!=null){
				p=p.next;
			}
			p.next=s;
			s.next=null;
			return true;
		}
		
	}
	public boolean add(float coef)//想链表当中添加元素的方法
	{
		
		Lnode p=first,s;
		s=new Lnode(coef,0,null);
		if(first==null){
			first=s;
			s.next=null;
			return true;
		}
		else {
			while(p.next!=null){
				p=p.next;
			}
			p.next=s;
			s.next=null;
			return true;
		}
	}
	public void sort(){//对多项式进行降序排列的方法
		Lnode p,q;int i,j,m;float n;
		for( i=0,p=first;i<this.size()-1;i++,p=p.next)
			for(j=i+1,q=p.next;j<this.size();j++,q=q.next)
				if(p.exp<q.exp)
				{
					m=p.exp;p.exp=q.exp;q.exp=m;
					n=p.coef;p.coef=q.coef;q.coef=n;
				}
	}
	public void union()//对多项式进行合并同类项的方法
	{
		Lnode p,q,r;
		sort();
		p=first;
		q=p.next;
		while(p!=null&& q!=null){
			if(p.exp==q.exp)
			{	r=q;
				p.coef=p.coef+q.coef;
				remove(q.coef,q.exp);
				p=r;
				q=r.next;
			} 
			else {
				p=q;
				q=q.next;
			}
		}
	}
	public void remove (float coef,int exp)//删除链表当中的某一个节点的方法
	{
		Lnode p=first,q=p;
		for(q=p;q!=null;q=q.next)
			if(q.next.coef==coef && q.next.exp==exp)
				break;
		q.next=q.next.next;
	}
	public String toString()//将链表转化为一个字符串输出的方法
	{
		String s="p(x)=";
		Lnode p=first;
		sort();
		union();
		while(p!=null){
			 if(p.coef==0)
				s=s+"+";
			else if(p.exp==0)
				s=s+p.coef+"+";
			else if(p.exp==1)
				s=s+p.coef+"x"+"+";
			else
			s=s+p.coef+"x^"+p.exp+"+";
			p=p.next;
		}
		return s.substring(0, s.length()-1)+"\n";
	}
	public void addPloyItem(PloyItemList p2)//多项式想家的而方法
	{
		this.sort();p2.sort();
		Lnode p=this.first,q=p2.first;
		while(p!=null || q!=null)
		{
			if(p!=null && q!=null)
			{
				if(p.exp==q.exp){
					p.coef+=q.coef;
					p=p.next;q=q.next;
				}
				else if(p.exp<q.exp){
					this.add(q.coef, q.exp);
					q=q.next;
				}
				else {
					this.add(q.coef, q.exp);
					q=q.next;
				}
			}
			else if(p==null && q!=null)
				this.add(q.coef, q.exp);
			else if(p!=null && q==null)
				p=p.next;
		}
	}
	
}