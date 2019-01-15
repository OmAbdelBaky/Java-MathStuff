package math_Sandbox;
//import java.util.Random;
//import java.util.Arrays;
public class Mandelbrot3 {
	public static void main(String Args[]) {
		int y =10000000;
		double tot=0;
		int x = 0;
		for (int i=1;i<=x;i++) {
			tot+=MbrotArea(y);
			System.out.println(tot/i);
		}
		System.out.println(MbrotSearch());
	}
	public static double MbrotArea(int num) {
		Complex[] z =Zgen(num);
		double y=0;
		//System.out.println(Arrays.deepToString(z));	
		for(int i=0;i<z.length;i++) {
			if(!Double.isNaN(Mbrot(z[i]).rPart)&&!Double.isNaN(Mbrot(z[i]).iPart)) {
				y+=1;
			}
		}
		y/=num;
		return y;
	}
	public static double MbrotSearch() {
		double max=1;
		for(int i=0;i<1000;i++) {
			Complex z= new Complex(max,0);
			if(Double.isNaN(Mbrot(z).rPart)) {
				max=(max-1.0)/2.0;	
			}
			else{
				max=(max+1.0)/2.0;
			}
		}
		return max;
		
	}
	public static double hhh(int num) {
		Complex[] z =Zgen(num);
		int[] x= new int[z.length];
		double y=0;
		//System.out.println(Arrays.deepToString(z));	
		for(int i=0;i<z.length;i++) {
			
			if(Double.isNaN(Mbrot(z[i]).rPart)||Double.isNaN(Mbrot(z[i]).iPart)) {
				x[i]=0;
			}
			else {
				x[i]=1;
				y+=1;
			}
		}
		y/=x.length;
		//System.out.println(Arrays.toString(x));
		return y;
	}
	public static Complex[] Zgen(int num,int seed) {
		Complex[] res=new Complex[num];
		for(int i=0;i<num;i++) {
			res[i]= new Complex(seed);
		}
		return res;
	}
	public static Complex[] Zgen(int num) {
		Complex[] res=new Complex[num];
		for(int i=0;i<num;i++) {
			res[i]= new Complex();	
		}
		return res;
	}
	
	public static Complex Mbrot(Complex c) {
		Complex z= new Complex(c.rPart,c.iPart);
		for(int i=0;i<100;i++) {
			z.Mult(z).Add(c);
			if(Double.isNaN(z.rPart)||Double.isNaN(z.iPart)) {
				break;
			}
		}
		return z;
	}
}
