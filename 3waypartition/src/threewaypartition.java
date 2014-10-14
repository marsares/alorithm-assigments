
public class threewaypartition {
	private int[] a;
	
	public threewaypartition(int[]a){
		this.a=a;
	}
	
	public void sort(int lo,int hi){
		int lt=lo,gt=hi;
		int i=lo;
		while(i<=gt){
			if(a[i]<a[lo])exch(a,lt++,i++);
			else if(a[i]>a[lo])exch(a,i,gt--);
			else i++;
		}
	}
	
	public void print(){
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args){
		int[]a={47,66,65,47,13,92,47,62,85,51};
		threewaypartition twp=new threewaypartition(a);
		twp.sort(0, a.length-1);
		twp.print();
	}
	
	private void exch(int[]a,int i,int h){
		int swap=a[h];
		a[h]=a[i];
		a[i]=swap;
	}
}
