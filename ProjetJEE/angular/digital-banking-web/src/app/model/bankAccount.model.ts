import { AccountOperation } from "./accountOperation.model";
import { Customer } from "./customer.model";

export interface BankAccount{
    id : string;
	balance : number;
	createdAt : Date;
	status : string;
	description : string;
	customerDTO : Customer;
	accountOperationsDTO : AccountOperation;
}