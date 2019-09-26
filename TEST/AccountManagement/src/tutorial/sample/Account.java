package tutorial.sample;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
	private Customer customer;
	private long accountNumber;
	private double balance;
	private boolean active;
	private Date startDate;
	private Date endDate;
	
	private String errorMessage = "";
	private String baseError = "Account:";
	
	public Account(Customer customer, long accountNumber, double balance, boolean active, Date startDate,
			Date endDate) {
		super();
		this.customer = customer;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		baseError+=Long.toString(accountNumber);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void deposit(double amount){
		if(this.active)
			this.balance+=amount;
		else{
			errorMessage = baseError+" is not active";
		}
	}
	
	public boolean withdraw(double amount){
		if(canWithdraw(amount) && active){
			balance-=amount;
			return true;
		}
		else {
			if(!active)
				errorMessage = baseError+" is not active";
			else
				errorMessage = baseError+" has insufficient balance";
		}
		return false;
	}

	private boolean canWithdraw(double amount) {
		return this.active && this.balance>=amount ;
	}
}
