package hu.kg.descriptor;

public class DescribedTriangle {

	String name;
	DescribedFace verticeNames=new DescribedFace();
	
	public DescribedTriangle copy() {
		DescribedTriangle result=new DescribedTriangle();
		result.name = this.name;
		result.verticeNames = this.verticeNames.copy();
		return result;
	}
	
	
	
	
	

}
