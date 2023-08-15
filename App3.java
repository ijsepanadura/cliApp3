import java.util.Scanner;

public class App3{
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String ADD_ACCOUNT ="Create New Account";
        final String MONEY_DIPOSIT = "Money Dipost";
        final String MONEY_WITHDRAW = "Money Withdraw";
        final String MONEY_TRASFER = "Money Transfer";
        final String CHECK_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete Account";

        final String ERROR_MSG = String.format("  %s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("  %s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);
    
        
        int[] idVal = new int[0];
        int id=0;
        int[] balance = new int[0];
        int inDepo=0;
        String[] customer = new String[0];
        

        String screen = DASHBOARD;


        do {
            final String APP_TITLE = String.format("%s%s%s",
            COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println(COLOR_GREEN_BOLD + "-".repeat(45)+RESET);
            System.out.println("\t" + APP_TITLE );
            System.out.println(COLOR_GREEN_BOLD + "-".repeat(45)+RESET);

            switch(screen){
                case DASHBOARD:
                    System.out.printf("\b %s[1]%s Create New Account %s\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);
                    System.out.printf("\b %s[2]%s Diposit Money %s\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);
                    System.out.printf("\b %s[3]%s Withdraw Money %s\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);
                    System.out.printf("\b %s[4]%s Transfer Money %s\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);
                    System.out.printf("\b %s[5]%s Check Account Balance %s\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);
                    System.out.printf("\b %s[6]%s Delete Account %s\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);
                    System.out.printf("\b %s[7]%s Exit %s\n\n",COLOR_RED_BOLD,COLOR_BLUE_BOLD,RESET);

                    int option;
                    System.out.print("Enter an Option to continue : ");
                    option = scanner.nextInt();
                    scanner.skip(System.lineSeparator());

                    switch(option){
                        case 1 : screen = ADD_ACCOUNT; break;
                        case 2 : screen = MONEY_DIPOSIT; break;
                        case 3 : screen = MONEY_WITHDRAW;break;
                        case 4 : screen = MONEY_TRASFER; break;
                        case 5 : screen = CHECK_BALANCE; break;
                        case 6 : screen = DELETE_ACCOUNT; break;
                        case 7 : System.out.println(CLEAR);System.exit(0);
                        default : continue;
                    }
                    continue;
                case ADD_ACCOUNT:
                    
                    String name;
                    boolean valid= true;

                    validationA:
                    do{
                        System.out.printf("%-22s: SDB-%05d \n","New Account ID",(customer.length + 1));
                        System.out.printf("%-22s: ","Account Holder Name");
                        name = scanner.nextLine().strip();
                        if(name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name cannot be empty..\n");
                            valid = false;
                            continue;
                        }
                        
                        for(int i=0; i<name.length(); i++){
                            if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                                System.out.printf(ERROR_MSG,"Entered Name is Invalid ");
                                valid =false;
                                continue validationA;
                            }
                        }
                        System.out.printf("%-22s: ","Inital Deposit");
                        inDepo = scanner.nextInt();
                        scanner.skip(System.lineSeparator());
                        if(inDepo<5000){
                            System.out.printf(ERROR_MSG,"Intial Deposit should be greater than Rs.5000.00");
                            valid = false;
                            continue;
                        }
                        break;

                    }while(!valid);

                    int[] newId = new int[idVal.length + 1];
                    String[] newCustomer = new String[customer.length + 1];
                    int[] newBalance = new int[balance.length + 1 ];
                    for (int i = 0; i < customer.length; i++) {
                        newId[i] = idVal[i];
                        newCustomer[i] = customer[i];
                        newBalance[i] = balance[i];
                    }
                    newId[newId.length - 1] = ++id;
                    newCustomer[newCustomer.length - 1] = name;
                    newBalance[newBalance.length -1] = inDepo;
                    customer = newCustomer;
                    idVal = newId;
                    balance= newBalance;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,String.format("SDB-%05d:%s Account has been created successfully", idVal[idVal.length-1], customer[customer.length-1]));
                    System.out.print("Do you want to continue adding (Y/n)? ");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                

            }


            break;
        } while(true);
        
    }
}