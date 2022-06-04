import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/loginService/login.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {
  title = 'dashboard';
  constructor(public loginService : LoginService){
   
  }
  ngOnInit(): void {
  }
  
  
}