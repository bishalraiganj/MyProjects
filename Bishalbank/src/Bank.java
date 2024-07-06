import java.util.ArrayList;

public class Bank {
    public String managerName="Bishal";
    public String password="bishalbank";
    private String name;
    public String transactionStatus="c";
    private ArrayList<Customer> aList=new ArrayList<> ();
    public ArrayList<Customer> getaList()
    {

        return aList;
    }
    public Bank(String name,ArrayList<Customer> aList)
    {
        this.name=name;
        this.aList=aList;
    }
    public void addNewCustomer(Customer customer)
    {
        if(findCustomer(customer)!=null)
        {
            aList.add(customer);
        }
    }
    private Customer findCustomer(Customer customer)
    {for(Customer i:aList)
    {
        if(i.getAccountNumber()==customer.getAccountNumber())
        {
            return null;
        }
    }
        return customer;


    }
    public void addNewTransaction(int accountNumber,double transaction)
    {     if(transactionStatus.equalsIgnoreCase("C"))
    {
        for (Customer i : aList)
        {
            if (i.getAccountNumber() == accountNumber)
            {
                i.credit(transaction);
            }

        }
    }
         if(transactionStatus.equalsIgnoreCase("d"))
         {

             for(Customer i:aList)
             {
                 if(i.getAccountNumber()==accountNumber)
                 {
                     i.debit(transaction);


                 }


             }

         }
    }

    @Override
    public String toString() {
        return
                "name= " + name + '\n' +
                " transactionStatus= " + transactionStatus+"\n" ;
    }

}
