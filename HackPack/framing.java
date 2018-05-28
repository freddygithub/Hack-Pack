import java.util.*;

public class framing {
	
	public static void main (String [] args)
	{
		Scanner in = new Scanner(System.in);
		int numGroups = in.nextInt();
		double v = 0;
		double totalV = 0;


		for (int loop = 0; loop < numGroups; loop++) {

			int people = in.nextInt();

			
			for (int groupLoop = 0; groupLoop < people; groupLoop++) {
				int frames = in.nextInt();

				
				for(int i = 0; i < frames; i++) {
					double areaW = 0, areaL = 0;
					int l = in.nextInt();
					int w = in.nextInt();
					int d = in.nextInt();
					double width, length, height;

					width = w + d * Math.sqrt(2);
					length = l + d * Math.sqrt(2);
					height = d / Math.sqrt(2);

					v = ((width + w) * height) + ((length + l) * height);



					


					//v *= 100;
					v = Math.round(v);
					//v /= 100;
					totalV += v;
				}
				

			}
		
		System.out.printf("Group #%d: %.2f cubic inches", (loop + 1), totalV );
		System.out.println();	

						
		totalV = 0;

		}

			
	}
}