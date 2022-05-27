import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataStateEnum } from 'src/app/model/dataStateEnum.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  readonly DataStateEnum=DataStateEnum;
  constructor(private router : Router
              ) { }

  ngOnInit(): void {
  }
  onGoHome(){
    this.router.navigateByUrl('Home');
  }
}
