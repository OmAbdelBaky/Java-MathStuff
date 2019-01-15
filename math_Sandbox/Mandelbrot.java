package math_Sandbox;
//import java.util.Random;
import java.util.ArrayList;
public class Mandelbrot {
	public static void main(String Args[]) {
		int y =1000000;
		System.out.println(MbrotArea(y));
	}
	public static double MbrotArea(int num) {
		Complex[] z =Zgen(num);
		ArrayList<Complex> x= new ArrayList<Complex>(); 
		double y=0;
		for(int i=0;i<z.length;i++) {
			if(Mbrot(z[i]).iPart%1!=0) {
				y+=1;
			}
			else {
				x.add(z[i]);
			}
		}
		y/=num;
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
	
	public static Complex Mbrot1(Complex c) {
		Complex z= new Complex(c.rPart,c.iPart);
		for(int i=0;i<100;i++) {
			z.Mult(z).Add(c);
			if(Double.isNaN(z.rPart)||Double.isNaN(z.iPart)) {
				break;
			}
		}
		return z;
	}
	public static Complex Mbrot(Complex c) {
		Complex z= new Complex(c.rPart,c.iPart);
		for(int i=0;i<10000;i++) {
			z.Mult(z).Add(c);
			if(z.Abs()>2) {
				z.Set(z.Abs(), i);
				break;
			}
		}
		return z;
	}
}
