import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, map, Observable, of, startWith} from 'rxjs';
import { AppDataState, DataStateEnum } from 'src/app/model/dataStateEnum.model';
import { CustomerService } from 'src/app/services/customerService/customer.service';
import { LoginService } from 'src/app/services/loginService/login.service';
import Swal from 'sweetalert2';
import { Customer } from '../../model/customer.model';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  totalLength:number=0;
  page:number=1;
  customers: Customer | any;
  customer: Customer | any;
  idCustomer: number | any;
  submitted: boolean = false;
  search$: Observable<AppDataState<Customer[]>> | null = null;
  customerformGroupe!: FormGroup;
  customerformGroupeModifiaction!: FormGroup;
  private jwtToken!: string;

  readonly DataStateEnum = DataStateEnum;
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
    public loginService : LoginService) {

  }

  ngOnInit() {
    //this.authService.oui=true;
    this.search$ = this.customerService.getCustomers().pipe(
      map(data => {
        this.customers = data;
        this.totalLength = data.length;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
    );
    this.customerformGroupe = this.formBuilder.group({
      name : this.formBuilder.control(null, [Validators.required, Validators.minLength(2)]),
      email : this.formBuilder.control(null, [Validators.required, Validators.email])
    });


  }
  getValue(id: number) {
    if (id != null) {
      this.customerService.getCustomer(id).subscribe(data => {
        this.customer = data;
        this.customerformGroupeModifiaction = this.formBuilder.group({
          name : this.formBuilder.control(this.customer.name, [Validators.required, Validators.minLength(2)]),
          email : this.formBuilder.control(this.customer.email, [Validators.required, Validators.email]),
          id: this.customer.id
        })
      })
    }
  }
  onGetAllCustomers() {
    this.search$ = this.customerService.getCustomers().pipe(
      map(data => {
        this.customers = data;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
    );
  }
  onGestionCustomers() {
    this.router.navigateByUrl('customers');
  }
  onDeleteCustomer(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes. Go ahead',
      cancelButtonText: 'No. I am going to think.'
    }).then((result) => {
      if (result.value) {
        this.customerService.deleteCustomer(id)
          .subscribe(data => {
            Swal.fire(
              'Deleted!',
              'This customer has been deleted successfully',
              'success'
            )
            return this.ngOnInit();
          }, err => {
            this.loginService.refreshToken();
            Swal.fire(
              'Canceled',
              'The customer has not been deleted',
              'error'
            )
            catchError(err => of({ dataState: this.DataStateEnum.ERROR, errorMessage: err.message }));
          })

      } else if (result.dismiss === Swal.DismissReason.cancel) {
        this.loginService.refreshToken();
        Swal.fire(
          'Canceled',
          'The customer has not been deleted',
          'error'
        )
      }
    })
  }
  onSearch(dataForm: any) {
    this.search$ = this.customerService.searchCustomers(dataForm.keyword).pipe(
      map(data => {
        this.customers = data;
        return ({ dataState: DataStateEnum.LOADED, data: data })
      }),
      startWith({ dataState: DataStateEnum.LOADING }),
      catchError(err => of({ dataState: DataStateEnum.ERROR, errorMessage: err.message }))
    );
  }
  onSaveCustomer(customer: Customer) {
    this.submitted = true;
    if (this.customerformGroupe.invalid) return;
    this.customerService.saveCustomer(customer)
      .subscribe(resp => {
        Swal.fire('', 'Customer added', 'success');
        this.submitted = false;
        return this.ngOnInit();
      }, err => {
        this.loginService.refreshToken();
        Swal.fire('Oups!', 'an error has occurred .. Verify your information', 'error')
        this.submitted = false;
        return this.ngOnInit();
      })
  }

  onUpdate(customer: Customer) {
     this.submitted=true;
     if(this.customerformGroupeModifiaction?.invalid) return;
     this.customerService.updateCustomer(customer).subscribe({
      next : (data : any) => {
       Swal.fire('','Customer has been updated', 'success')
       this.submitted=false;
       return this.ngOnInit(); 
       },
      error : (err:any) => {
        this.loginService.refreshToken();
       Swal.fire('Oups!', 'an error has occurred', 'error')
       this.submitted=false;
       return this.ngOnInit(); 
     }
    });
  }
  onViewBankAccounts(id: number) {
    this.router.navigateByUrl("bank-account/"+id);
  }
  
}


