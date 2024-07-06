import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> transactionsForBishal = new ArrayList<>(Arrays.asList(+450.00, -900.00, 987.00, -89.00));
        ArrayList<Double> listSubhankar = new ArrayList<>();
        ArrayList<Customer> listForBishal = new ArrayList<>(Arrays.asList(
                new Customer("Bishal", transactionsForBishal, 223456, 5600)
                , new Customer("Subhankar", listSubhankar, 445678, 67000)));
        Bank bishal = new Bank("Bishal", listForBishal);
//System.out.printf("""
//        Bank :
//        """+bishal+
//        """
//        Customers List:
//
//        """+bishal.getaList());
//bishal.addNewTransaction(223456,670);
//System.out.println("\n"+bishal.getaList());
//ArrayList<Double> sList=new ArrayList<>();
//bishal.addNewCustomer(new Customer("Suparna",sList,4458787,8900 ));
//System.out.println(bishal.getaList());
        Scanner s = new Scanner(System.in);
        String managerName;
        String password;
        boolean loginStatus = true;
        while (loginStatus) {
            System.out.printf("""   
                                                                       
                                                                       
                                                                       
                                                                       
                                                                       
                                                                       
                                                                       
                                                                       
                                                                            **** Welcome To the Manager's  Desk ****  
                                                                                      ~ THE BANK OF BISHAL ~
                               
                               
                    """);

            for (long i = 0; i < 999999999; i++) {
                for (long i1 = 0; i1 <= 0; i1++) {
                    for (long i2 = 0; i2 <= 9; i2++) {

                    }
                }
            }
            System.out.printf("""       
                                                                                

                                                                                  Enter Your Manager ID :   
                                     
                                                                  
                    """);


            managerName = s.nextLine().trim();

            for (long i = 0; i < 999999999; i++) {
                for (long i1 = 0; i1 <= 0; i1++) {
                    for (long i2 = 0; i2 <= 4; i2++) {

                    }
                }
            }

            System.out.printf("""
                                                                                   Enter Your Password :      
                                                                                                   
                    """);
            password = s.nextLine().trim();

            for (long i = 0; i < 999999999; i++) {
                for (long i1 = 0; i1 <= 0; i1++) {
                    for (long i2 = 0; i2 <= 19; i2++) {

                    }
                }
            }
            if (managerName.equalsIgnoreCase(bishal.managerName) && password.equalsIgnoreCase(bishal.password)) {
                System.out.printf("""
                                                                          
                                                                                  
                                                                                      : SERVER ACCESS GRANTED :
                                                                                      
                                                                                      
                                                                                      
                                                                                      
                                                                                      
                                                                                      
                                                                                      
                                                                                      
                                                             
                        """);
                for (long i = 0; i < 999999999; i++) {
                    for (long i1 = 0; i1 <= 0; i1++) {
                        for (long i2 = 0; i2 <= 19; i2++) {

                        }
                    }
                }


                showMenu(bishal);
                loginStatus = false;
            } else {
                System.out.printf("""
                                                                
                                                             
                                                                                         ! SERVER ACCESS DENIED 
                                                             
                                                                
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                                                
                                                                             
                                                                             
                                                                             
                                                                       
                        """);


                for (long i = 0; i < 999999999; i++) {
                    for (long i1 = 0; i1 <= 0; i1++) {
                        for (long i2 = 0; i2 <= 19; i2++) {

                        }
                    }
                }

                System.out.printf("""
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                       REESTABLISHING CONNECTION ------ 
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                        """);
                for (long i = 0; i < 999999999; i++) {
                    for (long i1 = 0; i1 <= 0; i1++) {
                        for (long i2 = 0; i2 <= 12; i2++) {

                        }
                    }
                }


            }


        }
    }

    public static void showMenu(Bank bishal) {
        int input;
        Scanner s = new Scanner(System.in);
        System.out.printf("""
                                
                                
                                
                                
                                                               **** ACTIONS AVAILABLE ****
                                
                                
                                                  ENTER :
                                                        1 ( TO SHOW LIST OF CUSTOMERS)
                                                        
                                                        2 (CHECK CUSTOMERS DETAILS)
                                                        
                                                        3 (ADD NEW CUSTOMER)
                                                        
                                                        4 (INITIALIZE CREDIT OR DEBIT OF A CUSTOMER)
                                                      
                                                        5 (FIND CUSTOMERS BALANCE)
                                                         
                                                        6  LOG OUT !
                                
                                
                """);
        input = s.nextInt();

        switch (input) {
            case 1 -> showCustomers(bishal);
            case 6 -> logOut();
            case 2 -> findCustomer(bishal);
            case 3 -> addNewCustomer(bishal);
            case 4->addTransaction(bishal);
//            case  5->findCustomer();
//            case 6->seeBalance();

        }


    }

    public static void logOut() {
        for (long i = 0; i < 999999999; i++) {
            for (long i1 = 0; i1 <= 0; i1++) {
                for (long i2 = 0; i2 <= 12; i2++) {

                }
            }
        }
        System.out.printf("""
                             
                                                                      LOGGING OUT------------ !            
                             
                             
                             
                """);

        for (long i = 0; i < 999999999; i++) {
            for (long i1 = 0; i1 <= 0; i1++) {
                for (long i2 = 0; i2 <= 4; i2++) {

                }
            }
        }

        return;
    }


    public static void findCustomer(Bank bishal) {
        Scanner s = new Scanner(System.in);
        String n;
        System.out.printf("""
                                 
                                                              LOOKING FOR THE CUSTOMER !
                                                              
                                                              ENTER CUSTOMER NAME OR A/C NO._____________                 
                """);

        n = s.nextLine();
        for (long i = 0; i < 999999999; i++) {
            for (long i1 = 0; i1 <= 0; i1++) {
                for (long i2 = 0; i2 <= 8; i2++) {

                }
            }
        }

        for (Customer i : bishal.getaList()) {
            try {
                if (i.getName().equalsIgnoreCase(n) || i.getAccountNumber() == Integer.parseInt(n)) {

                    for (long i34 = 0; i34 < 999999999; i34++) {
                        for (long i1 = 0; i1 <= 0; i1++) {
                            for (long i2 = 0; i2 <= 0; i2++) {

                            }
                        }
                    }


                    System.out.printf("""
                                                                           MATCH FOUND :
                                                                           
                            """);

                    for (long i24 = 0; i24 < 999999999; i24++) {
                        for (long i1 = 0; i1 <= 0; i1++) {
                            for (long i2 = 0; i2 <= 4; i2++) {

                            }
                        }
                    }

                    System.out.printf("""
                              NO:%d              
                                  CUSTOMER NAME :%s
                                  ACCOUNT NUMBER:%s
                                         BALANCE:%f INR
                                ALL TRANSACTIONS:  
                            """, bishal.getaList().indexOf(i) + 1, bishal.getaList().get(bishal.getaList().indexOf(i)).getName(), i.getAccountNumber(), i.getCurrentBalance(), bishal.getaList().get(bishal.getaList().indexOf(i)).getTransactions());

                    System.out.printf("""
                                                             CREDIT                          DEBIT
                                                 -------------------------------------------------------------------  
                            """);

                    for (Double c : bishal.getaList().get(bishal.getaList().indexOf(i)).getTransactions()) {
                        if (c > 0) {
                            System.out.printf("""
                                                                    +%f                     
                                    """, c);
                        }
                        if ((c < 0)) {
                            System.out.printf("""
                                                                                                  %f                            
                                    """, c);
                        }

                    }
                    for (long i12 = 0; i12 < 999999999; i12++) {
                        for (long i1 = 0; i1 <= 0; i1++) {
                            for (long i2 = 0; i2 <= 14; i2++) {

                            }
                        }
                    }


                    showMenu(bishal);
                    return;

                }
            } catch (NumberFormatException one) {
                if (i.getName().equalsIgnoreCase(n)) {

                    for (long i34 = 0; i34 < 999999999; i34++) {
                        for (long i1 = 0; i1 <= 0; i1++) {
                            for (long i2 = 0; i2 <= 0; i2++) {

                            }
                        }
                    }


                    System.out.printf("""
                                                                           MATCH FOUND :
                                                                           
                            """);

                    for (long i24 = 0; i24 < 999999999; i24++) {
                        for (long i1 = 0; i1 <= 0; i1++) {
                            for (long i2 = 0; i2 <= 4; i2++) {

                            }
                        }
                    }

                    System.out.printf("""
                              NO:%d              
                                  CUSTOMER NAME :%s
                                  ACCOUNT NUMBER:%s
                                         BALANCE:%f INR
                                ALL TRANSACTIONS:  
                            """, bishal.getaList().indexOf(i) + 1, bishal.getaList().get(bishal.getaList().indexOf(i)).getName(), i.getAccountNumber(), i.getCurrentBalance(), bishal.getaList().get(bishal.getaList().indexOf(i)).getTransactions());

                    for (long i12 = 0; i12 < 999999999; i12++) {
                        for (long i1 = 0; i1 <= 0; i1++) {
                            for (long i2 = 0; i2 <= 14; i2++) {

                            }
                        }
                    }


                    showMenu(bishal);
                    return;
                }

            }


        }
        System.out.printf("""
                                
                                               NOT FOUND !
                """);
        for (long i = 0; i < 999999999; i++) {
            for (long i1 = 0; i1 <= 0; i1++) {
                for (long i2 = 0; i2 <= 4; i2++) {

                }
            }
        }
        showMenu(bishal);

    }

    public static void showCustomers(Bank bishal) {
        for (Customer i : bishal.getaList()) {
            System.out.printf("""
                      NO:%d              
                          CUSTOMER NAME :%s
                          ACCOUNT NUMBER:%s
                                 BALANCE:%f INR
                        ALL TRANSACTIONS:  
                    """, bishal.getaList().indexOf(i) + 1, bishal.getaList().get(bishal.getaList().indexOf(i)).getName(), i.getAccountNumber(), i.getCurrentBalance(), bishal.getaList().get(bishal.getaList().indexOf(i)).getTransactions());
            for (long i1 = 0; i1 < 999999999; i1++) {
                for (long i11 = 0; i11 <= 0; i11++) {
                    for (long i2 = 0; i2 <= 10; i2++) {

                    }
                }
            }
        }
        showMenu(bishal);

    }


    public static void addNewCustomer(Bank bishal) {
        Scanner s = new Scanner(System.in);
        String name;
        int accountNumber = 0;
        double currentBalance = 0;
        ArrayList<Double> al = new ArrayList<>();
        System.out.printf("""
                                                               Enter the customer name :
                """);
        name = s.nextLine();
        boolean status = true;
        while (status) {
            System.out.printf("""
                                                                   Enter the customer A/C number :
                    """);
            accountNumber = s.nextInt();
            for (Customer i : bishal.getaList()) {
                if (i.getAccountNumber() == accountNumber) {
                    System.out.printf(""" 
                                                                           Customer with the same A/C number Exist :
                            """);

                    break;
                }
                if (bishal.getaList().indexOf(i) + 1 == bishal.getaList().size()) {

                    System.out.printf("""
                                
                                                                   -----    A/C Number Generated -----    
                                
                            """);
                    status = false;
                }
            }
        }
        System.out.printf("""
                                                            Add Credit / Enter Amount :
                """);
        currentBalance = s.nextDouble();
        Customer c = new Customer(name, al, accountNumber, 0);
        if(currentBalance>0)
        {
            c.credit(currentBalance);

        }
        if(currentBalance<0)
        {
            c.debit(currentBalance);
        }
        bishal.getaList().add(c);

        showMenu(bishal);

    }

    public static void addTransaction(Bank bishal) {
        Scanner s = new Scanner(System.in);
        double amount;
        boolean status = true;
        boolean matchStatus = true;
        int accountNumber;



        do
        { System.out.printf("""
                                                       Enter Customer A/C Number to add Transactions:
                """);

            accountNumber = s.nextInt();
            for (Customer i : bishal.getaList()) {
                if (i.getAccountNumber() == accountNumber) {



                    while (status) {
                        System.out.printf("""
                                                                    -----ENTER TRSANSACTION AMOUNT---
                                  
                                                               ( positive amount credits / negative debits )
                                """);

                        try {
                            amount = s.nextDouble();
                            if (amount > 0) {
                                i.getTransactions().add(amount);
                                status = false;
                                matchStatus=false;

                                i.setCurrentBalance(amount);
                                showMenu(bishal);
                                return;
                            }
                           if(amount<0){
                                i.getTransactions().add(amount);
                               i.setCurrentBalance(amount);
                               showMenu(bishal);
                               return;
                            }
                        } catch (InputMismatchException e) {
                            status = true;

                        }
                    }
                }


            }
            matchStatus=true;
    }
        while (matchStatus);


        }




        }

