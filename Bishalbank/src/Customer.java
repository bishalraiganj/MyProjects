import java.util.ArrayList;

public class Customer{
    private String name;
    private int accountNumber;
    private double currentBalance;
    private ArrayList<Double> transactions;
    public void setCurrentBalance(double currentBalance)
    {  if(currentBalance>0) {
        this.currentBalance +=currentBalance;
    }
  if(currentBalance<0)
  {

        this.currentBalance+=currentBalance;
    }
    }
public String getName()
{
    return name;
}
public int getAccountNumber()
{
    return accountNumber;
}
public ArrayList<Double> getTransactions()
{

    return transactions;
}
public double getCurrentBalance()
{


    return currentBalance;
}
    public Customer(String name,ArrayList<Double> transactions1,int accountNumber,
                    double currentBalance)
    {       this.accountNumber=accountNumber;
        this.currentBalance=currentBalance;
        this.name=name;
        this.transactions=transactions1;
    }
    public void debit(double transaction)
    {   double debit;

        if(transaction>0)
        {
            debit=-(transaction);
        }
        else
        {
            debit =transaction;
        }
        this.transactions.add(debit);
        currentBalance-=debit;
    }
    public void credit (double transaction)
    {double credit;
        if(transaction<0)
        {
            credit=-(transaction);

        }
        else {
            credit=transaction;
        }
        this.transactions.add(credit);
        currentBalance+=credit;

    }


    @Override
    public String toString() {
        return "\n Customer " +
                "name= " + name +
                " accountNumber= " + accountNumber +
                " currentBalance= " + currentBalance +
                " transactions= " + transactions ;
    }
}


