/** 
 * Portfolio Project - Java Concurrency Application 
 * 
 * Demonstrates two threads working in sequence: 
 * Thread 1 counts from 0 through 20. 
 * After Thread 1 completes, Thread 2 counts from 20 down to 0. 
 */ 
public class PortfolioConcurrency { 
  
	// Thread 1 counts upward from 0 to 20. 
	static class CountUp implements Runnable { 
    	@Override 
    	public void run() { 
        	for (int i = 0; i <= 20; i++) { 
            	System.out.println("Thread 1 - Count Up: " + i); 
            	try { 
                	Thread.sleep(50); 
            	} catch (InterruptedException e) { 
                	Thread.currentThread().interrupt(); 
                	System.out.println("Thread 1 was interrupted."); 
                	return; 
            	} 
        	} 
    	} 
	} 
  
	// Thread 2 counts downward from 20 to 0. 
	static class CountDown implements Runnable { 
    	@Override 
    	public void run() { 
        	for (int i = 20; i >= 0; i--) { 
            	System.out.println("Thread 2 - Count Down: " + i); 
            	try { 
                	Thread.sleep(50); 
            	} catch (InterruptedException e) { 
                	Thread.currentThread().interrupt(); 
                	System.out.println("Thread 2 was interrupted."); 
                	return; 
            	} 
        	} 
    	} 
	} 
  
	public static void main(String[] args) { 
    	System.out.println("Starting Java concurrency demonstration...\n"); 
  
    	Thread thread1 = new Thread(new CountUp(), "Counter-Up"); 
    	Thread thread2 = new Thread(new CountDown(), "Counter-Down"); 
  
    	thread1.start(); 
  
    	try { 
        	// Thread 2 cannot begin until Thread 1 has completed. 
        	thread1.join(); 
  
        	System.out.println("\nThread 1 reached 20. Starting Thread 2...\n"); 
  
        	thread2.start(); 
        	thread2.join(); 
  
    	} catch (InterruptedException e) { 
        	Thread.currentThread().interrupt(); 
        	System.out.println("Main thread was interrupted."); 
    	} 
  
    	System.out.println("\nBoth counter threads have completed."); 
	} 
} 
