import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(public router:Router) { }

ngOnInit(): void {
  
  }
onGestionCustomers(){
    this.router.navigateByUrl('customers');
}
onGoHome(){
    this.router.navigateByUrl('Home');
}
onLogout(){
  //this.authService.logout();
}
}
