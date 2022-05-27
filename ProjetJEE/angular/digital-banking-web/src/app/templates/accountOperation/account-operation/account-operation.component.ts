import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { AccountOperation } from 'src/app/model/accountOperation.model';
import { BankAccount } from 'src/app/model/bankAccount.model';
import { AppDataState, DataStateEnum } from 'src/app/model/dataStateEnum.model';
import { AccountOperationService } from 'src/app/services/accountOperationService/account-operation.service';
import { BankAccountService } from 'src/app/services/bankAccontService/bank-account.service';
import { CustomerService } from 'src/app/services/customerService/customer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-account-operation',
  templateUrl: './account-operation.component.html',
  styleUrls: ['./account-operation.component.css']
})
export class AccountOperationComponent implements OnInit {
  totalLength:number=0;
  page:number=1;
  accountOperations: AccountOperation | any;
  accountOperation: AccountOperation | any;
  idAccountOperation: number | any;
  submitted: boolean = false;
  idBankAccount: string | any;
  bankAccounts: BankAccount | any;
  idDestination: string | any;
  amount: number = 0;
  search$: Observable<AppDataState<AccountOperation[]>> | null = null;
  accountOperationformGroupe!: FormGroup;
  accountOperationformGroupeModifiaction!: FormGroup;
  private jwtToken!: string;

  readonly DataStateEnum = DataStateEnum;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private accountOperationService: AccountOperationService,
    private bankAccountService: BankAccountService,
    private activatedRoute: ActivatedRoute) {
    this.idBankAccount = this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.onGetAllAccountOperations();
    this.accountOperationformGroupe = this.formBuilder.group({
      amount: this.formBuilder.control("", [Validators.required]),
      idDestination: this.formBuilder.control("", [Validators.required]),
    })
    this.bankAccountService.getBankAccounts().subscribe(data => {
      this.bankAccounts = data;
    }, err => {
      Swal.fire('Oups!', 'an error has occurred', 'error')
      this.submitted = false;
      return this.ngOnInit();
    })
  }
  getValue(id: number) {
    if (id != null) {
      this.accountOperationService.getAccountOperation(id).subscribe(data => {
        this.accountOperation = data;
        this.accountOperationformGroupeModifiaction = this.formBuilder.group({
          operationDate: this.formBuilder.control(this.accountOperation.operationDate, [Validators.required]),
          amount: this.formBuilder.control(this.accountOperation.amount, [Validators.required]),
          type: this.formBuilder.control(this.accountOperation.type, [Validators.required]),
          description: this.formBuilder.control(this.accountOperation.description, [Validators.required]),
          id: id
        })
      })
    }
  }
  onGetAllAccountOperations() {
    this.search$ = this.accountOperationService.getAccountOperations(this.idBankAccount).pipe(
      map(data => {
        this.accountOperations = data;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
    );
  }
  onGestionAccountOperations() {
    this.router.navigateByUrl('account-operation');
  }
  onDeleteAccountOperation(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes. Go ahead',
      cancelButtonText: 'No. I am going to think.'
    }).then((result) => {
      if (result.value) {
        this.accountOperationService.deleteAccountOperation(id)
          .subscribe(data => {
            Swal.fire(
              'Deleted!',
              'This accountOperation has been deleted successfully',
              'success'
            )
            return this.ngOnInit();
          }, err => {
            Swal.fire(
              'Canceled',
              'The accountOperation has not been deleted',
              'error'
            )
            catchError(err => of({ dataState: this.DataStateEnum.ERROR, errorMessage: err.message }));
          })

      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Canceled',
          'The accountOperation has not been deleted',
          'error'
        )
      }
    })
  }
  onSearch(dataForm: any) {
    this.search$ = this.accountOperationService.searchAccountOperations(dataForm.keyword).pipe(
      map(data => {
        this.accountOperations = data;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR
      }))
    );
  }
  onSaveAccountOperation(accountOperation: AccountOperation) {
    this.submitted = true;
    if (this.accountOperationformGroupe.invalid){
      Swal.fire('Oups!', 'an error has occurred .. you can not send the amount the same account you are in', 'error')
          this.submitted = false;
          return this.ngOnInit();
    }
    this.idDestination = this.accountOperationformGroupe.value.idDestination;
    if (this.idDestination != this.idBankAccount) {
      accountOperation.description = this.idDestination;
      this.accountOperationService.transfer(this.idBankAccount, accountOperation)
        .subscribe(resp => {
          Swal.fire('', 'BankAccount added', 'success');
          this.submitted = false;
          return this.ngOnInit();
        }, err => {
          Swal.fire('Oups!', 'an error has occurred .. Verify your information', 'error')
          this.submitted = false;
          return this.ngOnInit();
        })
    } else {
      Swal.fire(
        'Canceled',
        'The destination should be different',
        'error'
      )
      catchError(err => of({ dataState: this.DataStateEnum.ERROR, errorMessage: err.message }));
    };
  }
  onUpdate(accountOperation: AccountOperation) {
    this.submitted = true;
    if (this.accountOperationformGroupeModifiaction?.invalid) return;
    this.bankAccountService.getBankAccount(this.idBankAccount).subscribe(data => {
      accountOperation.bankAccountDTO = data;
      this.accountOperationService.updateAccountOperation(accountOperation.id, accountOperation)
        .subscribe(data => {
          Swal.fire('', 'AccountOperation has been updated', 'success')
          this.submitted = false;
          return this.ngOnInit();
        }, err => {
          Swal.fire('Oups!', 'an error has occurred', 'error')
          this.submitted = false;
          return this.ngOnInit();
        })
    }, err => {
      Swal.fire('Oups!', 'an error has occurred', 'error')
      this.submitted = false;
      return this.ngOnInit();
    })

  }

}

