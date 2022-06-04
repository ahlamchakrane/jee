import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/loginService/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginFormGroup!: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {
  }

  ngOnInit(): void {
    let roles = localStorage.getItem('roles')!;
    if (roles != undefined) {
      if (roles.toString().includes('ADMIN')) {
        this.router.navigateByUrl('/customers');
      }
      else if (roles.toString().includes('USER')) {
        this.router.navigateByUrl('/customers');
      } else {
        this.router.navigateByUrl('/login');
      }
    }
    this.loginFormGroup = this.fb.group({
      username: this.fb.control(null, [Validators.required, Validators.minLength(4)]),
      password: this.fb.control(null, [Validators.required, Validators.minLength(4)])
    });
  }

  login() {
    this.loginService.login(this.loginFormGroup.controls['username'].value, this.loginFormGroup.controls['password'].value).subscribe({
      next: (data: any) => {
        localStorage.setItem("accessToken", data.accessToken);
        localStorage.setItem("refreshToken", data.refreshToken);
        localStorage.setItem("roles", data.roles);
        if (data.roles.toString().includes('ADMIN')) {
          this.router.navigateByUrl('/customers');
        } else if (data.roles.toString().includes('USER')) {
          this.router.navigateByUrl('/customers');
        }

      },
      error: (err: any) => {
        console.log(err);
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: err.message,
          showConfirmButton: false,
          timer: 1500
        })
      }
    });
  }
}
