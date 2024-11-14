package week5;

public class BankAccount {

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(new Man(account), "Man1").start();
        new Thread(new Man(account), "Man2").start();
        new Thread(new Man(account), "Man3").start();


    }

    static class Man implements Runnable {
        Account account;

        public Man(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
            	account.deposit(1000);
                try {
                    account.withdraw(100);
                } catch (NotEnoughMoneyException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    static class Account {
        int totalMoney = 0;

        synchronized void deposit(int amount) {
            totalMoney += amount;
            System.out.println(Thread.currentThread().getName() + ": " + amount + "원 입금하여 잔액은 " + totalMoney);
        }

        synchronized void withdraw(int amount) throws NotEnoughMoneyException{
            if (totalMoney < amount) {
                throw new NotEnoughMoneyException("통장 잔액이 부족해요");
            }
            totalMoney -= amount;
            System.out.println(Thread.currentThread().getName() + ": " + amount + "원 출금하여 잔액은 " + totalMoney);
        }
    }

    static class NotEnoughMoneyException extends Exception {
        public NotEnoughMoneyException(String message) {
            super(message);
        }
    }
}
