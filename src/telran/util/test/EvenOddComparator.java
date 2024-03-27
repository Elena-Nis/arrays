package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
	int res=1;
	
//if(o1%2==0&&o2%2==0)res=o1.compareTo(o2);
//if (o1%2!=0&&o2%2!=0) res=(o1.compareTo(o2))*(-1);	
//if(o1%2==0&&o2%2!=0)res=-1; 
//return res;
		
//		if((o1%2==0&&o2%2==0)||(o1%2!=0&&o2%2!=0)) 
//		{ res=o1.compareTo(o2);
//	      if (o1%2!=0) res=-res;}	
//	if(o1%2==0&&o2%2!=0)res=-1;
//	return res;	
	
	if((o1%2==0&&o2%2==0)||(o1%2!=0&&o2%2!=0)) 
	   { 
		res=o1.compareTo(o2);
        if (o1%2!=0) res=-res;
       }
	else 
	   {
		if(o1%2==0)res=-1;
	   }
return res;	
  }
}


 
 