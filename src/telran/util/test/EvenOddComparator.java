package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	//Переопределяем метод compare компаратора - определяем 
	//КАК (по какому принципу) будет происходить сравнение. 
	//В данном случае все четные по возрастанию
	//в начале массива. Это значит, что любое четное меньше нечетного
	//А в самом компараторе - ЧТО будет происходить при сравнении, т.е.
	//меняем значения местами. Это и является ОБЩИМ для всех видов сортировок - 
	//переставляем соседние элементы. А вот когда (в каком случае) мы их переставлем 
	//это имплементация компаратора.
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


 
 