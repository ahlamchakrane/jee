import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { BankAccount } from 'src/app/model/bankAccount.model';
import { AppDataState, DataStateEnum } from 'src/app/model/dataStateEnum.model';
import { BankAccountService } from 'src/app/services/bankAccontService/bank-account.service';
import { CustomerService } from 'src/app/services/customerService/customer.service';
import { LoginService } from 'src/app/services/loginService/login.service';
import Swal from 'sweetalert2';
import { CustomersComponent } from '../customers/customers.component';

@Component({
  selector: 'app-bank-account',
  templateUrl: './bank-account.component.html',
  styleUrls: ['./bank-account.component.css']
})
export class BankAccountComponent implements OnInit {
  totalLength:number=0;
  page:number=1;
  bankAccounts: BankAccount | any;
  bankAccount: BankAccount | any;
  selectedOption: string | any;
  idBankAccount: number | any;
  submitted: boolean = false;
  idCustomer: number | any;
  search$: Observable<AppDataState<BankAccount[]>> | null = null;
  bankAccountformGroupe!: FormGroup;
  currentAccountformGroupe!:FormGroup;
  savingAccountformGroupe!: FormGroup;
  bankAccountformGroupeModifiaction!: FormGroup;
  private jwtToken!: string;

  readonly DataStateEnum = DataStateEnum;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private bankAccountService: BankAccountService,
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute,
    public loginService : LoginService) {
    this.idCustomer = this.activatedRoute.snapshot.params['id'];

  }

  ngOnInit() {
    //this.authService.oui=true;
    this.search$ = this.bankAccountService.getCustomerBankAccounts(this.idCustomer).pipe(
      map(data => {
        this.bankAccounts = data;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
    );
    this.currentAccountformGroupe = this.formBuilder.group({
      balance: this.formBuilder.control(0, [Validators.required, Validators.minLength(2)]),
      createdAt: this.formBuilder.control("", [Validators.required]),
      status: this.formBuilder.control("", [Validators.required]),
      description: this.formBuilder.control("", [Validators.required]),
      overDraft: this.formBuilder.control(""),
      type: this.formBuilder.control("CurrentAccount", [Validators.required]),
    });
    this.savingAccountformGroupe = this.formBuilder.group({
      balance: this.formBuilder.control(0, [Validators.required, Validators.minLength(2)]),
      createdAt: this.formBuilder.control("", [Validators.required]),
      status: this.formBuilder.control("", [Validators.required]),
      description: this.formBuilder.control("", [Validators.required]),
      interestRate: this.formBuilder.control(""),
      type: this.formBuilder.control("SavingAccount", [Validators.required]),
    });


  }
  getValue(id: number) {
    if (id != null) {
      this.bankAccountService.getBankAccount(id).subscribe(data => {
        this.bankAccount = data;
        this.bankAccountformGroupeModifiaction = this.formBuilder.group({
          balance: this.formBuilder.control(this.bankAccount.balance, [Validators.required]),
          createdAt: this.formBuilder.control(this.bankAccount.createdAt, [Validators.required]),
          status: this.formBuilder.control(this.bankAccount.status, [Validators.required]),
          description: this.formBuilder.control(this.bankAccount.description, [Validators.required]),
          overDraft: this.formBuilder.control(this.bankAccount.overDraft),
          interestRate: this.formBuilder.control(this.bankAccount.interestRate),
          type: this.formBuilder.control(this.bankAccount.type),
          id: this.bankAccount.id
        })
      })
    }
  }
  onGetAllBankAccounts() {
    this.search$ = this.bankAccountService.getCustomerBankAccounts(this.idCustomer).pipe(
      map(data => {
        this.bankAccounts = data;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
    );
  }
  onGestionBankAccounts() {
    this.router.navigateByUrl('bank-account/' + this.idCustomer);
  }
  onDeleteBankAccount(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes. Go ahead',
      cancelButtonText: 'No. I am going to think.'
    }).then((result) => {
      if (result.value) {
        this.bankAccountService.deleteBankAccount(id)
          .subscribe(data => {
            Swal.fire(
              'Deleted!',
              'This bank Account has been deleted successfully',
              'success'
            )
            return this.ngOnInit();
          }, err => {
            this.loginService.refreshToken();
            Swal.fire(
              'Canceled',
              'The bank Account has not been deleted',
              'error'
            )
            catchError(err => of({ dataState: this.DataStateEnum.ERROR, errorMessage: err.message }));
          })

      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Canceled',
          'The bankAccount has not been deleted',
          'error'
        )
      }
    })
  }

  onSaveCurrentAccount(bankAccount: BankAccount) {
    this.submitted = true;
    if (this.currentAccountformGroupe.invalid) return;
    this.customerService.getCustomer(this.idCustomer).subscribe(resp => {
      bankAccount.customerDTO=resp;
      this.bankAccountService.saveBankAccount(bankAccount)
      .subscribe(resp => {
        Swal.fire('', 'BankAccount added', 'success');
        this.submitted = false;
        return this.ngOnInit();
      }, err => {
        Swal.fire('Oups!', 'an error has occurred .. Verify your information', 'error')
        this.submitted = false;
        return this.ngOnInit();
      })
    }, err => {
      this.loginService.refreshToken();
      Swal.fire('Oups!', 'an error has occurred .. Verify your information', 'error')
      this.submitted = false;
      return this.ngOnInit();
    })
  }

  onSaveSavingAccount(bankAccount: BankAccount) {
    this.submitted = true;
    if (this.savingAccountformGroupe.invalid) return;
    this.customerService.getCustomer(this.idCustomer).subscribe(resp => {
      bankAccount.customerDTO=resp;
      this.bankAccountService.saveBankAccount(bankAccount)
      .subscribe(resp => {
        Swal.fire('', 'BankAccount added', 'success');
        this.submitted = false;
        return this.ngOnInit();
      }, err => {
        this.loginService.refreshToken();
        Swal.fire('Oups!', 'an error has occurred .. Verify your information', 'error')
        this.submitted = false;
        return this.ngOnInit();
      })
    }, err => {
      this.loginService.refreshToken();
      Swal.fire('Oups!', 'an error has occurred .. Verify your information', 'error')
      this.submitted = false;
      return this.ngOnInit();
    })
  }
  onSearch(dataForm: any) {
     this.search$ = this.bankAccountService.searchBankAccounts(dataForm.keyword).pipe(
       map(data => {
         this.bankAccounts = data;
         return ({ dataState: DataStateEnum.LOADED, data: data })
       }),
       startWith({ dataState: DataStateEnum.LOADING }),
       catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
     );
  }
  onUpdate(bankAccount: BankAccount) {
    this.submitted = true;
    if (this.bankAccountformGroupeModifiaction?.invalid) return;
    this.bankAccount = this.bankAccountformGroupeModifiaction.value;
    this.customerService.getCustomer(this.idCustomer).subscribe(data => {
      this.bankAccount.customerDTO = data;
      this.bankAccountService.updateBankAccount(this.bankAccount.id, this.bankAccount)
        .subscribe(data => {
          Swal.fire('', 'BankAccount has been updated', 'success')
          this.submitted = false;
          return this.ngOnInit();
        }, err => {
          this.loginService.refreshToken();
          Swal.fire('Oups!', 'an error has occurred', 'error')
          this.submitted = false;
          return this.ngOnInit();
        })
    }, err => {
      this.loginService.refreshToken();
      Swal.fire('Oups!', 'an error has occurred', 'error')
      this.submitted = false;
      return this.ngOnInit();
    });
  }
  onViewAccountOperations(id: String) {
    this.router.navigateByUrl("account-operation/" + id);
  }
}

