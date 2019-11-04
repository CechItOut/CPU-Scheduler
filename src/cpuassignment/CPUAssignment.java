//Daniel Rossano
package cpuassignment;

import javax.swing.JOptionPane;

public class CPUAssignment 
{
    //use a variable to track how many processes have been created
int processNumber = 0;
    //another variable to track the arrival time of each process
int arrivalTime = 0;   
    public static void main(String[] args) 
    {
       new CPUInterface().setVisible(true);
    }
    //the randomProcess and customProcess methods will create processes and
    //output them to a table from oldest creation to newest creation
    public Object[] randomProcess(Object[] p)
    {
        processNumber++;
        
        //give the process a random burstTime between 1 and 1000ms
        int burstTime = (int) (Math.random() * 999) + 1;
        
        //name the process
        p[0] = "P" + processNumber;
        
        //next, record the burstTime of the process
        p[1] = burstTime;  
        
        //now give the process its order. This changes depending on algorithm,
        //but until then, just assume we take the processes from first to last.
        p[2] = processNumber;
        
        //finally, give the process its arrival time
        p[3] = arrivalTime;
        //prepare the next process' arrival time
        arrivalTime = arrivalTime + (int) p[1];
        
        
        
        
        return p;
    }
    
    public Object[] customProcess(Object[] p)
    {
        processNumber++;
        
        //set burst time to unacceptable value
        int burstTime = -1;
        //use burst time to establish loop
        //this way users can retry if they enter unacceptable values
        while (burstTime == -1)
        {
            try
            {
            String s = JOptionPane.showInputDialog("How many "
                + "milliseconds should the process be? Please enter an integer"
                + " between 1 and 1000." + "\nOr type 'exit' to cancel");
            
                if (s.equalsIgnoreCase("exit") == true)
                {
                processNumber--;
                return null;
                }
                
            burstTime = Integer.parseInt(s);
            }
            catch (NumberFormatException ex)
            {
         JOptionPane.showMessageDialog(null,"Error: a noninteger was entered.");
            }
        }
        
        
        //name the process
        p[0] = "P" + processNumber;
        
        //next, record the burstTime of the process
        p[1] = burstTime;  
        
        //now give the process its order. This changes depending on algorithm,
        //but until then, just assume we take the processes from first to last.
        p[2] = processNumber;
        
        //finally, give the process an arrival time
        p[3] = arrivalTime;
        //prepare the next process' arrival time
        arrivalTime = arrivalTime + (int) p[1];
        
        //don't let the user enter integers less than 1 or greater than 1000
        if (burstTime < 1 || burstTime > 1000)
        {
            JOptionPane.showMessageDialog(null,"Error: the given burstTime "
                    + "for the process was either too long or too short.");
            //let the user make another entry
            processNumber--;
            arrivalTime = arrivalTime - (int) p[1];
            burstTime = 0;
            customProcess(p);
        }
        
        return p;
    }
    
    public void removeProcess(Object[] p, Object[] q)
    {
        processNumber--;
        //if there is more than one process
        if (p != null)
        {
        arrivalTime = arrivalTime - (int) q[1];
        System.out.println(arrivalTime);
        System.out.println("time to take off arrival time is " + p[1]);
        }
        //if there is only one process
        if (p == null)
        {
            System.out.println("one item");
            arrivalTime = arrivalTime - (int) q[1];
            System.out.println(arrivalTime);
        }
        
        System.out.println(processNumber);
        //get rid of any data stored inside q, the array to be removed
        q = null;
    }
    
}
