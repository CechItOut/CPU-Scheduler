//Daniel Rossano
package cpuassignment;

import javax.swing.JOptionPane;

public class CPUAssignment 
{
    //use a variable to track how many processes have been created
int processNumber = 0;
    //another variable to track the wait time of each process
int waitTime = 0;   
    //and a variable to track the turnaround time of each process
int turnaround = 0;
//now two more variables to track the averages of the wait and turnaround times
Double avgWait = 0.0;
Double avgTurn = 0.0;
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
        
        //obscure wait time from viewer, since nothing has been sorted yet
        p[2] = "N/A";
        
        //obscure turnaround time from viewer, since nothing is executed yet
        p[3] = "N/A";
        
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
           + " between 1 and 1000." + "\nOr hit the 'cancel' button to cancel");
            //if the user hits the cancel button, do nothing
            if (s == null)
                {
                    processNumber--;
                    return null;
                }

            burstTime = Integer.parseInt(s);
           //don't let the user enter integers less than 1 or greater than 1000
                if (burstTime < 1 || burstTime > 1000)
                {
              JOptionPane.showMessageDialog(null,"Error: the given burst time "
                    + "for the process was either too long or too short.");
                    //let the user make another entry keeping the loop running
                    burstTime = -1;
                }
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
        
        //obscure wait time from view, since nothing has been sorted yet
        p[2] = "N/A";
        
        //obscure turnaround time from viewer, since nothing is executed yet
        p[3] = "N/A";
        
        
        return p;
    }
    
    public Object[] removeProcess(Object[] q)
    {
 //decremenet process counter so the next process 'replaces' the removed process
        processNumber--;
        //get rid of any data stored inside the array to be removed
        q = null;
        return q;
    }
    
    //first come first serve algorithm
    //note that processes are already entered in this order
    //so all we have to do is calculate the wait and turnaround times
    
    public Object[][] firstComeFirstServe(Object[][] table)
    {
        //if there are no processes to work with
        if (table.length == 0)
        {
  JOptionPane.showMessageDialog(null, "Error: There are no processes to sort.");
            return table;
        }
        //give the turnaround time for the first process
        table[0][2] = 0;
  //go through the table, setting the wait and turnaround times for each process
        for (int i = 0; i<table.length - 1; i++)
        {
            turnaround = waitTime + (int) (table[i][1]);
            waitTime = waitTime + (int) (table[i][1]);
            table[i+1][2] = waitTime;
            table[i][3] = turnaround;
        }
  //the loop will not cover the last index's turnaround time, so do it manually
     table[table.length - 1][3] = waitTime + (int) (table[table.length - 1][1]);
     
     
     //now we find the average wait time and average turnaround time
     for (int i=0; i<table.length;i++)
     {
         avgWait = avgWait + (int) table[i][2];
         avgTurn = avgTurn + (int) table[i][3];
     }
     avgWait = Math.round(avgWait / processNumber * 100.0) / 100.0;
     avgTurn = Math.round(avgTurn / processNumber * 100.0) / 100.0;
     //we will need to access these average times separately so we can output
     //them onto the interface
     
     //lastly, set the wait and turnaround times back to 0 so they are
     //ready for the next algorithm
     waitTime = 0;
     turnaround = 0;

        return table;
    }
    
    public Object[][] shortestJobFirst(Object[][] table)
    {
        if (table.length == 0)
        {
   JOptionPane.showMessageDialog(null,"Error: There are no processes to sort.");
            return table;
        }
        
        
     //the first step is to sort the processes by burst time
     //(arrival time is assumed to be uniform)
     //while not the most efficient method, bubble sort will do the trick
     Object[] temp;
     boolean swapped = false;
     for (int i=0; i<table.length - 1; i++)
     {
         swapped = false;
         for (int j=0; j<table.length - 1 - i; j++)
         {
            if ((int) table[j][1] > (int) table[j+1][1]) 
            {
                temp = table[j];
                table[j] = table[j+1];
                table[j+1] = temp;
                swapped = true;
            }
         }
         
         if (swapped == false)
         {
             break;
         }
     }
     //now we can run the first come first serve algorithm, since
     //the table has now been sorted from shortest process to longest process
             //give the turnaround time for the first process
        table[0][2] = 0;
  //go through the table, setting the wait and turnaround times for each process
        for (int i = 0; i<table.length - 1; i++)
        {
            turnaround = waitTime + (int) (table[i][1]);
            waitTime = waitTime + (int) (table[i][1]);
            table[i+1][2] = waitTime;
            table[i][3] = turnaround;
        }
  //the loop will not cover the last index's turnaround time, so do it manually
     table[table.length - 1][3] = waitTime + (int) (table[table.length - 1][1]);
     
     
     //now we find the average wait time and average turnaround time
     for (int i=0; i<table.length;i++)
     {
         avgWait = avgWait + (int) table[i][2];
         avgTurn = avgTurn + (int) table[i][3];
     }
     avgWait = Math.round(avgWait / processNumber * 100.0) / 100.0;
     avgTurn = Math.round(avgTurn / processNumber * 100.0) / 100.0;
     //we will need to access these average times separately so we can output
     //them onto the interface
     
     //lastly, set the wait and turnaround times back to 0 so they are
     //ready for the next algorithm
     waitTime = 0;
     turnaround = 0;
     
     
        return table;
    }
    
    //need get methods for the averages so we can output them in the interface
    public Double getAvgWait(double a)
    {
        a = avgWait;
   //set average wait back to 0 so it is ready for the next set of calculations
        avgWait = 0.0;
        return a;
    }
    
    public Double getAvgTurn(double b)
    {
        b = avgTurn;
     //set average turnaround back to 0 for the same reason we set avgWait to 0
        avgTurn = 0.0;
        return b;
    }
    
}
