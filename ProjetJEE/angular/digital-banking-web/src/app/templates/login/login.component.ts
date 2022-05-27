import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentificationService/authentification';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  mode:number=0;
  constructor(private authService:AuthenticationService, private router:Router) { }

  ngOnInit(): void {
  }
  
  onLogin(user : any ){
    this.authService.login(user)
    .subscribe( resp=>{
      let jwt=resp.headers.get('Authorization');
      this.authService.saveToken(jwt!);
      this.router.navigateByUrl('/Home');
    },
      err=>{  
        this.mode=1;
      })
  }

}


