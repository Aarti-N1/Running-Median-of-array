import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindRunningMedian {

    /*
     * Complete the runningMedian function below.
     */
    static double[] runningMedian(int[] a) {
        //min heap = higher side
    	PriorityQueue<Integer> higher = new PriorityQueue<Integer>();
    	// max heap = lower side
    	PriorityQueue<Integer> lower = new PriorityQueue<Integer>(new Comparator<Integer>() {
    		@Override
    		public int compare(Integer o1, Integer o2) {
    			if(o1<o2)
					return 1;
				else if(o1==o2)
					return 0;
				else
					return -1;
    		}
    	});
    	//for each element added
    	double[] result = new double[a.length];
    	for(int i=0; i<a.length;i++) {
    		//add to appropriate heap
    		addToHeap(higher, lower, a[i]);
    		//balance the sizes of heap
    		balanceHeaps(higher, lower);
    		//calculate median and insert in double array
    		result[i]= calculateMedian(higher, lower);
    	}
    	return result;
    }
    public static void addToHeap(PriorityQueue<Integer> higher, PriorityQueue<Integer> lower, int num ) {
    	if(lower.size()==0 || num<lower.peek()) {
    		lower.add(num);
    	}else {
    		higher.add(num);
    	}
    }
    public static void balanceHeaps(PriorityQueue<Integer> higher, PriorityQueue<Integer> lower) {
    	if((higher.size()-lower.size())>=2 ) { //higher is loaded
    		lower.add(higher.poll());
    	}
    	if((lower.size()-higher.size())>=2 ) { //lower overloaded
    		higher.add(lower.poll());
    	}
    }
    public static double calculateMedian(PriorityQueue<Integer> higher, PriorityQueue<Integer> lower) {
    	if(higher.size() > lower.size()) {
    		System.out.println((double)higher.peek());
    		return higher.peek();
    	}
    	else if(higher.size()== lower.size()) {
    		System.out.println(((double)(higher.peek()+lower.peek())/2));
    		return ((double)(higher.peek()+lower.peek()))/2;
    	}else {
    		System.out.println((double)lower.peek());
    		return lower.peek();
    	}
    }


    public static void main(String[] args) throws IOException {
        int[] a = {12,4,5,3,8,7};

        double[] result = runningMedian(a);
        System.out.println("*****************************");
        for(int i=0;i<result.length;i++) {
        	System.out.println(result[i]);
        }
    }
}
