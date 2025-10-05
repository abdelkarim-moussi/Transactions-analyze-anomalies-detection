package main.java.com.presentation;

import main.java.com.entity.account.Account;
import main.java.com.entity.client.Client;
import main.java.com.entity.enums.AccountType;
import main.java.com.entity.enums.TransactionType;
import main.java.com.entity.transaction.Transaction;
import main.java.com.service.BankAccountService;
import main.java.com.service.ClientService;
import main.java.com.service.TransactionService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class Menu {

    static ClientService clientService = new ClientService();
    static BankAccountService bankAccountService = new BankAccountService();
    static TransactionService transactionService = new TransactionService();

    static Scanner scanner = new Scanner(System.in);

    public static void menu() throws SQLException {
        try {

            int choice;

            do {
                System.out.println("1-GESTION CLIENTS");
                System.out.println("2-GESTION COMPTES");
                System.out.println("3-GESTION TRANSACTIONS");
                System.out.println("0-QUITTER");
                System.out.print("Votre Choix : ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Entrer un choix valide (nembre) : ");
                    scanner.next();
                }
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        clientsManagement();
                        break;
                    case 2:
                        bankAccountManagement();
                        break;
                    case 3:
                        transactionsManagement();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix Invalide!");
                }

            } while (true);
        } catch (InputMismatchException e) {
            e.getMessage();
        }

    }

    private static void clientsManagement() throws SQLException {
        do {

            System.out.println("--------GESTION CLIENT--------");
            System.out.println("1- Créer Client");
            System.out.println("2- Modifier CLient");
            System.out.println("3- Supprimer CLient");
            System.out.println("4- Consulter List Clients");
            System.out.println("5- retour au menu principal");

            int Choice;
            while (!scanner.hasNextInt()) {
                System.out.print("Choisir un choix valide : ");
                scanner.next();
            }
            Choice = scanner.nextInt();

            scanner.nextLine();
            switch (Choice) {
                case 1:
                    createClient();
                    break;
                case 2:
                    updateCLient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    displayClients();
                    break;
                case 5:
                    menu();
                default:
                    System.out.println("Choix Invalide!");

            }

        } while (true);
    }


    private static void createClient() throws SQLException {
        String number = "";
        String email = "";

        System.out.println("Entrer votre numero : ");
        number = scanner.nextLine();
        System.out.println("Entrer votre email");
        email = scanner.nextLine();

        try{
            if(clientService.createClientAccount(number,email) == 1){
                System.out.println("client created succefully");
            }else System.out.println("couldn't create account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private static void updateCLient() {

        String number = "";
        String email = "";
        String id = "";
        System.out.println("Entrer votre Id : ");
        id = scanner.nextLine();
        System.out.println("Entrer nouveau numero : ");
        number = scanner.nextLine();
        System.out.println("Entrer nouveau email");
        email = scanner.nextLine();

        try{
            if(clientService.updateClientAccount(id,number,email) == 1){
                System.out.println("client updated succefully");
            }else System.out.println("couldn't update account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private static void deleteClient() {
        System.out.println("entrer Id de client a supprimer :");
        String id = scanner.nextLine();

        if (!id.trim().isEmpty()) {
            clientService.deleteClientAccount(id);
        } else System.out.println("id ne peut pas être vide!");
    }

    private static void displayClients() {

        List<Client> clients = clientService.getAllClients();

        clients.forEach(c -> System.out.println(c));

    }


    //Accounts Management

    private static void bankAccountManagement() throws SQLException {
        do {

            System.out.println("--------GESTION COMPTES BANCAIRES--------");
            System.out.println("1- Créer Compte bancaire");
            System.out.println("2- Modifier Compte bancaire");
            System.out.println("3- Supprimer Compte bancaire");
            System.out.println("4- Afficher Les Compte d'un Client");
            System.out.println("5- recherche un compte");
            System.out.println("6- Retour au menu principal");

            int choice;
            while (!scanner.hasNextInt()) {
                System.out.print("Choisir un choix valide : ");
                scanner.next();
            }
            choice = scanner.nextInt();

            scanner.nextLine();
            switch (choice) {
                case 1:
                    createBankAccount();
                    break;
                case 2:
                    updateBankAccount();
                    break;
                case 3:
                    deleteBankAccount();
                    break;
                case 4:
                    displayClientBankAccounts();
                    break;
                case 5:
                    searchForAccountByNumberOrClient();
                case 6:
                    menu();
                default:
                    System.out.println("Choix Invalide!");

            }

        } while (true);
    }


    private static void createBankAccount() {

        System.out.println("Choisir le type de Compte :\n1:Compte Courant\n2:Compte Epargne");
        while(!scanner.hasNextInt()){
            System.out.println("Saisir un choix valid");
            scanner.next();
        }
        int choice = scanner.nextInt();

        System.out.println("Saisir Id de client");
        String clientId = scanner.nextLine();
        System.out.println("Saisir le solde");
        while(!scanner.hasNextBigDecimal()){
            System.out.println("saisir un solde valide ex : 2000.0");
            scanner.next();
        }
        BigDecimal balance = scanner.nextBigDecimal();
        BigDecimal overdraft = BigDecimal.valueOf(0);
        float interestRate = 0;
        AccountType accountType = null;

        if(choice == 1) {
            System.out.println("Saisir decouvert");
            while(!scanner.hasNextBigDecimal()){
                System.out.println("saisir une valeur valide");
                scanner.next();
            }
            overdraft = scanner.nextBigDecimal();
            accountType = AccountType.current_account;
        }else if(choice == 2) {
            System.out.println("Saisir taux d'Interet");
            while(!scanner.hasNextBigDecimal()){
                System.out.println("saisir une valeur valide");
                scanner.next();
            }
            interestRate = scanner.nextFloat();
            accountType = AccountType.saving_account;

        }

        try {
            int res = bankAccountService.createBankAccount(clientId, balance,
                    overdraft,interestRate, accountType);
            if(res > 0){
                System.out.println("bank account created successfully");
            }
            else System.out.println("couldn't create account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private static void updateBankAccount() {

        System.out.println("Sasir compte id");
        String accountId = scanner.nextLine();
        System.out.println("Sasir client id");
        String clientId = scanner.nextLine();

        System.out.println("Saisir le solde");
        while(!scanner.hasNextBigDecimal()){
            System.out.println("saisir un solde valide ex : 2000.0");
            scanner.next();
        }
        BigDecimal balance = scanner.nextBigDecimal();
        BigDecimal overdraft = BigDecimal.valueOf(0);
        float interestRate = 0;
        AccountType accountType = null;

        System.out.println("Choisir le type de Compte :\n1:Compte Courant\n2:Compte Epargne");
        while (scanner.hasNextInt()){
            System.out.println("choisir un choix valide");
            scanner.next();
        }
        int choice = scanner.nextInt();

        if(choice == 1) {
            System.out.println("Saisir decouvert");
            while(!scanner.hasNextBigDecimal()){
                System.out.println("saisir une valeur valide");
                scanner.next();
            }
            overdraft = scanner.nextBigDecimal();
            accountType = AccountType.current_account;
        }else if(choice == 2) {
            System.out.println("Saisir taux d'Interet");
            while(!scanner.hasNextBigDecimal()){
                System.out.println("saisir une valeur valide");
                scanner.next();
            }
            interestRate = scanner.nextFloat();
            accountType = AccountType.saving_account;

        }

        try {
            int res = bankAccountService.updateBankAccount(accountId,clientId,balance,
                    overdraft,interestRate, accountType);
            if(res > 0){
                System.out.println("account updated successfully");
            }
            else System.out.println("couldn't update account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private static void deleteBankAccount(){

        System.out.println("Entrer le Id de ompte a supprimer : ");
        String id = scanner.nextLine();
        if(!id.trim().isEmpty()){
            int res = bankAccountService.deleteBankAccount(id);
            if(res > 1) {
                System.out.println("compte suprimer avev succé");
            }else System.out.println("il ya un problem");
        }

    }

    private static void searchForAccountByNumberOrClient(){
        Map<String,Account> accountMap = new HashMap<>();
        System.out.println("choisi le terme de recherche :\n1-nombre de compte \n2-client id");
        while(!scanner.hasNextInt()){
            System.out.println("choisir un choix valid");
        }
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.println("Saisir le nombre de compte");
            String accountNum = scanner.nextLine();
            accountMap = bankAccountService.getAccountsByNumberOrClient(accountNum,"");
        }else if(choice == 2){
            System.out.println("Saisir id de client");
            String clientId = scanner.nextLine();
            accountMap = bankAccountService.getAccountsByNumberOrClient("",clientId);
        }


        if(!accountMap.isEmpty()){
            System.out.println(accountMap);
        }else System.out.println("there is no accounts with this search terms");
    }

    private static void displayClientBankAccounts(){

    }

    //Transactions

    private static void transactionsManagement()throws SQLException{
        do{
        System.out.println("1-Effectué une Transaction");
        System.out.println("2-Consulté les Transactions d'un compte");
        System.out.println("3-retour au menu principal");
        while(!scanner.hasNextInt()){
            System.out.println("choix invalid");
            scanner.next();
        }
        int choice = scanner.nextInt();

        switch (choice){
            case 1: makeTransaction();
            break;
            case 2 : consultAccountTransactions();
            break;
            case 3 : menu();
            default:
                System.out.println("choix invalide");
        }

        }while(true);
    }

    private static void makeTransaction(){

        System.out.println("Saisir id compte");
        String id = scanner.nextLine();

        if(!id.trim().isEmpty()){
            System.out.println("saisir le montant");
            while (!scanner.hasNextBigDecimal()){
                System.out.println("sairir un montant valide");
            }
            BigDecimal amount = scanner.nextBigDecimal();

            System.out.println("choisir le type de transaction : \n1-VERSEMENT\n2-RETRAIT\n3-VIREMENT");
            while (!scanner.hasNextInt()){
                System.out.println("choisir un choix valide");
                scanner.next();
            }
            int typeChoice = scanner.nextInt();
            TransactionType transactionType = null;

            if(typeChoice == 1) {
                transactionType = TransactionType.deposit;
            }else if(typeChoice == 2){
                transactionType = TransactionType.withdraw;
            }else if(typeChoice == 3){
                transactionType = TransactionType.transfer;
            }

            System.out.println("saisir le lieur de transaction");
            String place = scanner.nextLine();

            var res = transactionService.makeTransaction(amount,
                    transactionType,place,id);
            if(res > 0){
                System.out.println("transaction successful");
            } else System.out.println("failed");

        }
    }

    private static void consultAccountTransactions(){
        System.out.println("Saisir id de compte");
        String accountId = scanner.nextLine();
        if(accountId.trim().isEmpty()) System.out.println("id ne peut pas être vide");
        else {
        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);

        if(transactions.isEmpty()) System.out.println("there is no transactions with the provided data");
        else System.out.println(transactions);
        }
    }

}

