import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {
  constructor( private router : Router) {
  
  }
  onLogout(){
   // this.authService.logout();
    this.router.navigateByUrl("/login");
  }

}