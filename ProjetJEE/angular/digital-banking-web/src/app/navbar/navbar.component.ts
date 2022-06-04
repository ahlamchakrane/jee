import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/loginService/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(private router : Router, public loginService : LoginService
    ) { }

  ngOnInit(): void {
  }
  onGoHome(){
  this.router.navigateByUrl('Home');
  }
  onLogout(){
    this.loginService.logout();
  }
}
