
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFilename;
	private final double G = 6.67*1e-11;
	
	public Body (double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp; myYPos = yp; myXVel = xv;
		myYVel = yv; myMass = mass;
		myFilename = filename;
	}
	public Body(Body b) {
		myXPos = b.myXPos; myYPos = b.myYPos;
		myXVel = b.myXVel; myYVel = b.myYVel;
		myMass = b.myMass;
		myFilename = b.myFilename;
	}
	
	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFilename;
	}
	
	public double calcDistance(Body b) {
		double dx = myXPos - b.myXPos;
		double dy = myYPos - b.myYPos;
		return Math.sqrt((Math.pow(dx, 2)) + (Math.pow(dy, 2)));
	}
	public double calcForceExertedBy (Body p) {
		double r2 = Math.pow(calcDistance(p),2);
		return G*myMass*p.myMass/r2;
	}
	public double calcForceExertedByX (Body p) {
		double F = calcForceExertedBy(p);
		double dx = p.myXPos - myXPos;
		return F*dx/calcDistance(p);
	}
	public double calcForceExertedByY (Body p) {
		double F = calcForceExertedBy(p);
		double dy = p.myYPos - myYPos;
		return F*dy/calcDistance(p);
	}

	public double calcNetForceExertedByX(Body[] bodies) {
		double netForce = 0;
		for(Body b: bodies) {
			if(!b.equals(this)) {
				netForce += calcForceExertedByX(b);
			}
		}
		return netForce;
	}
	public double calcNetForceExertedByY(Body[] bodies) {
		double netForce = 0;
		for(Body b: bodies) {
			if(!b.equals(this)) {
				netForce += calcForceExertedByY(b);
			}
		}
		return netForce;
	}

	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
		
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos,"images/" +myFilename);
	}
}
