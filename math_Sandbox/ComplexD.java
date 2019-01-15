package math_Sandbox;
import java.util.Random;
import java.math.BigDecimal;
import java.math.MathContext;

public class ComplexD {
		public BigDecimal rPart;
		public BigDecimal iPart;
		
		public ComplexD(double rPart,double iPart) {
			this.rPart=new BigDecimal(rPart);
			this.iPart=new BigDecimal(iPart);
		}
		public ComplexD(BigDecimal rPart,BigDecimal iPart) {
			this.rPart=rPart;
			this.iPart=iPart;
		}
		public ComplexD(int seed) {
			Random rng = new Random(seed);
			this.rPart=new BigDecimal(rng.nextDouble());
			this.iPart=new BigDecimal(rng.nextDouble());
		}
		public ComplexD() {
			Random rng = new Random();
			this.rPart=new BigDecimal(1-2*rng.nextDouble());
			this.iPart=this.rPart.multiply(this.rPart);
			BigDecimal y=new BigDecimal(1-2*rng.nextDouble());
			this.iPart=(BigDecimal.ONE.subtract(this.iPart)).sqrt(MathContext.DECIMAL128).multiply(y);
		}
		public String toString() {
			String real=this.rPart.toString();
			String im=this.iPart.toString();
			if(this.iPart.compareTo(BigDecimal.ZERO)==1) {
				return (real+"+"+im+"i");
			}
			if(this.iPart.compareTo(BigDecimal.ZERO)==0) {
				return (real);
			}
			return (real+""+im+"i");
		}
		public ComplexD Set(double rPart,double iPart) {
			this.rPart=new BigDecimal(rPart);
			this.iPart=new BigDecimal(iPart);
			return this;
		}
		public ComplexD Set(BigDecimal rPart,BigDecimal iPart) {
			this.rPart=rPart;
			this.iPart=iPart;
			return this;
		}
		public ComplexD Add(ComplexD c) {
			this.rPart.add(c.rPart);
			this.iPart.add(c.iPart);
			return this;
		}
		public ComplexD Sub(ComplexD c) {
			this.rPart.subtract(c.rPart);
			this.iPart.subtract(c.iPart);
			return this;
		}
		public ComplexD Conj() {
			ComplexD c = new ComplexD(this.rPart,BigDecimal.ZERO.subtract(this.iPart));
			return c;
		}
		public ComplexD Div(BigDecimal r) {
			this.rPart=this.rPart.divide(r);
			this.iPart=this.iPart.divide(r);
			return this;
		}
		public ComplexD Mult(BigDecimal r) {
			this.rPart=r.multiply(this.rPart);
			this.iPart=r.multiply(this.rPart);
			return this;
		}
		public ComplexD Mult(ComplexD z) {
			BigDecimal a=this.rPart;
			BigDecimal b=this.iPart;
			BigDecimal c=z.rPart;
			BigDecimal d=z.iPart;
			this.rPart=(a.multiply(c)).subtract(b.multiply(d));
			this.iPart=(b.multiply(c)).add(a.multiply(d));
			return this;
		}
		public ComplexD Div(ComplexD c) {
			ComplexD d= c.Conj();
			this.Mult(d);
			this.Div(c);
			return this.Div(c.Abs());
		}
		public BigDecimal Abs() {
			return ((this.rPart.multiply(this.rPart)).add(this.iPart.multiply(this.iPart))).sqrt(MathContext.DECIMAL128);
		}
		public ComplexD Exp(int n) {
			ComplexD z= new ComplexD(this.rPart,this.iPart);
			if(n>1) {
				for (int i=0;i<n-1;i++) {
					z.Mult(this);
				}
			}
			this.rPart=z.rPart;
			this.iPart=z.iPart;
			return this;
		}
		public static BigDecimal Fact(int n) {
			BigDecimal result=BigDecimal.ONE;
			BigDecimal num= new BigDecimal(0);
			for(int i=1;i<n+1;i++) {
				num= new BigDecimal(i);
				result=result.multiply(num);
			}
			return result;
		}
		public ComplexD Exp() {
			BigDecimal error=BigDecimal.ONE;
			ComplexD x =new ComplexD(1.0,0.0);
			ComplexD z= new ComplexD(this.rPart,this.iPart);
			BigDecimal i= new BigDecimal(0.000000000000001);
			for(int n=1;error.compareTo(i)==1; n++) {
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
