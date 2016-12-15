package Gaussian;

public class GaussianParam {
	private double[] mean;
	private double[][] variance;
	
	/* The constructor calculate the medium and variance from the sample*/
	public GaussianParam(Sample s){
		mean = new double[3];
		variance = new double[3][3];
		/* Calculate mean */
		ColorRGB sum= new ColorRGB(0,0,0);
		for (int i = 0; i < s.size(); i++) {	
			ColorRGB c = s.get(i);
			sum = sum.addColor(c);

		}
		mean[0] = sum.getRed()/s.size();
		mean[1] = sum.getGreen()/s.size();
		mean[2] = sum.getBlue()/s.size();
		
		/* Calculate variance */
		for (int i = 0; i < s.size(); i++) {		
			double[] temp = new double[3];
			ColorRGB c = s.get(i);
			temp[0] = c.getRed() - mean[0];
			temp[1] = c.getGreen()- mean[1];
			temp[2] = c.getBlue()- mean[2];
			
			variance[0][0] = variance[0][0]+temp[0]*temp[0];
			variance[0][1] = variance[0][1]+temp[0]*temp[1];
			variance[0][2] = variance[0][2]+temp[0]*temp[2];
			variance[1][0] = variance[1][0]+temp[1]*temp[0];
			variance[1][1] = variance[1][1]+temp[1]*temp[1];
			variance[1][2] = variance[1][2]+temp[1]*temp[2];
			variance[2][0] = variance[2][0]+temp[2]*temp[0];
			variance[2][1] = variance[2][1]+temp[2]*temp[1];
			variance[2][2] = variance[2][2]+temp[2]*temp[2];
		}

		for(int i=0; i<3;i++){
			for(int j=0;j <3; j++){
				variance[i][j] = variance[i][j]/s.size();
			}
		}
	}
	
	public double detMatrix(double[][] d){
		return d[0][0]*(d[1][1]*d[2][2]- d[1][2]*d[2][1])- d[0][1]*(d[1][0]*d[2][2]- d[1][2]*d[2][0])+ d[0][2]*(d[1][0]*d[2][1] - d[1][1]*d[2][0]);
	}
	
	public double[][] inverseMatrix(double[][] A){
		double[][] result = new double[3][3];
		if(detMatrix(A) == 0){
			//System.out.println("SAME VALUE TAKEN");
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(i==j) result[i][j]=1;
					else result[i][j]=0;
				}
			}
			return result;
		}
		double invdet = 1/detMatrix(A);
		result[0][0] =  (A[1][1]*A[2][2]-A[1][2]*A[2][1])*invdet;
		result[1][0] =  (A[1][2]*A[2][0]-A[1][0]*A[2][2])*invdet;
		result[2][0] =  (A[1][0]*A[2][1]-A[1][1]*A[2][0])*invdet;
		result[0][1] =  (A[0][2]*A[2][1]-A[0][1]*A[2][2])*invdet;
		result[1][1] =  (A[0][0]*A[2][2]-A[0][2]*A[2][0])*invdet;
		result[2][1] =  (A[0][1]*A[2][0]-A[0][0]*A[2][1])*invdet;
		result[0][2] =  (A[0][1]*A[1][2]-A[0][2]*A[1][1])*invdet;
		result[1][2] =  (A[0][2]*A[1][0]-A[0][0]*A[1][2])*invdet;
		result[2][2] =  (A[0][0]*A[1][1]-A[0][1]*A[1][0])*invdet;
		return result;
	}
	
	public double mahalanobis(ColorRGB c){
		double[] t = new double[3];
		t[0] = c.getRed() - mean[0];
		t[1] = c.getGreen()- mean[1];
		t[2] = c.getBlue()- mean[2];
		double[][] d = inverseMatrix(variance);
		double v1=0, v2=0, v3=0;
		
		v1 = t[0]*(t[0]*d[0][0] + t[1]*d[1][0]+t[2]*d[2][0]);
		v2 = t[1]*(t[0]*d[0][1] + t[1]*d[1][1]+t[2]*d[2][1]);
		v3 = t[2]*(t[0]*d[0][2] + t[1]*d[1][2]+t[2]*d[2][2]);

		return Math.sqrt(v1+v2+v3);
	}
	

}
