public class Bonuses {
	public int[] getDivision(int[] points) {
		int pointPool = 0;
		int[] employeeBonus = new int[points.length];
		int leftOverBonus = 0;
		int bonusPercentage = 0;
		
		for(int p: points) pointPool+=p; //add up all points
		
		for(int k = 0; k < employeeBonus.length; k++){
			employeeBonus[k] = (int) (((double) points[k]/ (double)pointPool)*100); //employee bonus = percentage of each employee's contribution to point pool
			bonusPercentage += employeeBonus[k];								// which is equal to (employee points/total points)*100
		}																												
		leftOverBonus = 100 - bonusPercentage;
		boolean[] maxChecked = new boolean[points.length];

        while (leftOverBonus > 0) {
            int nextIndex = -1;
            int maxPoints = 0;
            for (int i = points.length-1; i >=0; i--) {
                if (!maxChecked[i] && points[i] >= maxPoints) {
                    nextIndex = i;
                    maxPoints = points[i];
                }
            }
            employeeBonus[nextIndex]++;							//add 1% of left over bonus to top employees
            maxChecked[nextIndex] = true;
            leftOverBonus--;
        }
		return employeeBonus;	
	}
	
}
