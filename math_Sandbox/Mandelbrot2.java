package math_Sandbox;
//import java.util.Random;
import java.math.BigDecimal;
public class Mandelbrot2 {
	public static void main(String Args[]) {
		//double y=MbrotArea(1);
		//System.out.println(y);
		///BigDecimal x= new BigDecimal(10);
		//x.sqrt(MathContext.DECIMAL128);
		ComplexD z= new ComplexD();
		System.out.println(Mbrot(z));
	}
	public static double MbrotArea(int num) {
		ComplexD[] z =Zgen(num);
		double y=0;
		//System.out.println(Arrays.deepToString(z));	
		for(int i=0;i<z.length;i++) {
			Mbrot(z[i]);
			if(z[i].Abs().compareTo(BigDecimal.ONE)==1) {
				y+=1;
			}
			System.out.println(z[i]);
		}
		y/=num;
		return y;
	}
	public static ComplexD[] Zgen(int num) {
		ComplexD[] res=new ComplexD[num];
		for(int i=0;i<num;i++) {
			res[i]= new ComplexD();	
		}
		return res;
	}
	
	public static ComplexD Mbrot(ComplexD c) {
		ComplexD z= new ComplexD(c.rPart,c.iPart);
		for(int i=0;i<5;i++) {
			z.Mult(z).Add(c);
			System.out.println(z);
		}
		return z;
	}
}
