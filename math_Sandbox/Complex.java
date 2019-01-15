package math_Sandbox;
import java.util.Random;
import java.lang.Math;
public class Complex {
	public double rPart;
	public double iPart;
	
	public Complex(double rPart,double iPart) {
		this.rPart=rPart;
		this.iPart=iPart;
	}
	public boolean equals(Complex c) {
		return (this.rPart==c.rPart&&this.iPart==c.iPart);
	}
	public Complex(int seed) {
		Random rng = new Random(seed);
		this.rPart=rng.nextDouble();
		this.iPart=rng.nextDouble();
	}
	public Complex() {
		Random rng = new Random();
		this.rPart=1-2*rng.nextDouble();
		this.iPart =Math.sqrt(1-this.rPart*this.rPart)*(1-2*rng.nextDouble());
	}
	public String toString() {
		String real=Double.toString(this.rPart);
		String im=Double.toString(this.iPart);
		if(this.iPart>0) {
			return (real+"+"+im+"i");
		}
		if(this.iPart==0) {
			return (real);
		}
		return (real+""+im+"i");
	}
	public Complex Set(double rPart,double iPart) {
		this.rPart=rPart;
		this.iPart=iPart;
		return this;
	}
	public Complex Add(Complex c) {
		this.rPart+=c.rPart;
		this.iPart+=c.iPart;
		return this;
	}
	public Complex Sub(Complex c) {
		this.rPart-=c.rPart;
		this.iPart-=c.iPart;
		return this;
	}
	public Complex Conj() {
		Complex c = new Complex(this.rPart,-this.iPart);
		return c;
	}
	public Complex Div(double r) {
		this.rPart=this.rPart/r;
		this.iPart=this.iPart/r;
		return this;
	}
	public Complex mult(double r) {
		this.rPart*=r;
		this.iPart*=r;
		return this;
	}
	public Complex Mult(Complex z) {
		double a=this.rPart;
		double b=this.iPart;
		double c=z.rPart;
		double d=z.iPart;
		this.rPart=a*c-b*d;
		this.iPart=b*c+a*d;
		return this;
	}
	public Complex Div(Complex c) {
		Complex d= c.Conj();
		this.Mult(d);
		this.Div(c.Abs());
		return this;
	}
	public double Abs() {
		return Math.hypot(this.rPart, this.iPart);
	}
	public Complex Exp(int n) {
		Complex z= new Complex(this.rPart,this.iPart);
		if(n>1) {
			for (int i=0;i<n-1;i++) {
				z.Mult(this);
			}
		}
		this.rPart=z.rPart;
		this.iPart=z.iPart;
		return this;
	}
	public static double Fact(int n) {
		double result=1;
		for(int i=1;i<n+1;i++) {
			result*= (double)i;
		}
		return result;
	}
	public Complex Exp() {
		double error=1;
		Complex x =new Complex(1.0,0.0);
		Complex z= new Complex(this.rPart,this.iPart);
		for(int n=1;error>0.000000000000001; n++) {
			z.Set(this.rPart,this.iPart);
			z.Exp(n).Div(Fact(n));
			//System.out.println(z+" "+n);
			x.Add(z);
			error=z.Abs();
			//System.out.println(x);
		}
		this.rPart=x.rPart;
		this.iPart=x.iPart;
		return this;
	}
	
}
